package com.grocery.backend.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    // Пример: gpt-5-mini-2025-08-07 или то, что у тебя сейчас указано
    @Value("${openai.model}")
    private String model;

    private final RestTemplate restTemplate = new RestTemplate();

    public String generateRecipes(String productsString) {
        try {
            String prompt = """
                    Generate 2 simple recipes using these products:
                    %s

                    Return ONLY valid JSON in this format (no markdown, no extra text):
                    {
                      "recipes": [
                        {
                          "name": "string",
                          "ingredients": [
                            { "item": "string", "quantity": "string" }
                          ],
                          "steps": ["string"]
                        }
                      ]
                    }
                    """.formatted(productsString);

            Map<String, Object> body = new HashMap<>();
            body.put("model", model);
            body.put("input", prompt);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(
                    "https://api.openai.com/v1/responses",
                    entity,
                    Map.class
            );

            Map responseBody = response.getBody();
            if (responseBody == null) {
                return "{\"error\":\"Empty response from OpenAI\"}";
            }

            Object outputObj = responseBody.get("output");
            if (!(outputObj instanceof List)) {
                return "{\"error\":\"OpenAI response has no output list\"}";
            }

            List output = (List) outputObj;

            // Ищем message -> content[] -> type=output_text -> text
            for (Object item : output) {
                if (!(item instanceof Map)) continue;
                Map itemMap = (Map) item;

                if (!"message".equals(itemMap.get("type"))) continue;

                Object contentObj = itemMap.get("content");
                if (!(contentObj instanceof List)) continue;

                List content = (List) contentObj;
                for (Object c : content) {
                    if (!(c instanceof Map)) continue;
                    Map cMap = (Map) c;

                    if ("output_text".equals(cMap.get("type")) && cMap.get("text") != null) {
                        // ВАЖНО: тут возвращается строка, которая уже является JSON (как мы попросили в prompt)
                        return cMap.get("text").toString();
                    }
                }
            }

            return "{\"error\":\"No output_text found in OpenAI response\"}";

        } catch (Exception e) {
            e.printStackTrace();
            // Лучше не возвращать огромный stacktrace наружу — но для дебага ок
            return "{\"error\":\"Internal AI error: " + escapeForJson(e.getMessage()) + "\"}";
        }
    }

    private String escapeForJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
