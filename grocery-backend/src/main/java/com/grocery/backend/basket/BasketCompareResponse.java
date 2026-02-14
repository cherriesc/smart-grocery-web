package com.grocery.backend.basket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BasketCompareResponse {

    private LocalDate dateUsed;
    private List<StoreTotal> results;
    private Long bestStoreId;
    private BigDecimal bestTotal;

    public BasketCompareResponse(LocalDate dateUsed, List<StoreTotal> results, Long bestStoreId, BigDecimal bestTotal) {
        this.dateUsed = dateUsed;
        this.results = results;
        this.bestStoreId = bestStoreId;
        this.bestTotal = bestTotal;
    }

    public LocalDate getDateUsed() { return dateUsed; }
    public List<StoreTotal> getResults() { return results; }
    public Long getBestStoreId() { return bestStoreId; }
    public BigDecimal getBestTotal() { return bestTotal; }

    public static class StoreTotal {
        private Long storeId;
        private String storeName;
        private BigDecimal total;

        public StoreTotal(Long storeId, String storeName, BigDecimal total) {
            this.storeId = storeId;
            this.storeName = storeName;
            this.total = total;
        }

        public Long getStoreId() { return storeId; }
        public String getStoreName() { return storeName; }
        public BigDecimal getTotal() { return total; }
    }
}
