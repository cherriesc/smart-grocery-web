package com.grocery.backend.price;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreatePriceRequest {

    private Long productId;
    private Long storeId;
    private BigDecimal price;
    private LocalDate date;

    public Long getProductId() { return productId; }
    public Long getStoreId() { return storeId; }
    public BigDecimal getPrice() { return price; }
    public LocalDate getDate() { return date; }

    public void setProductId(Long productId) { this.productId = productId; }
    public void setStoreId(Long storeId) { this.storeId = storeId; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setDate(LocalDate date) { this.date = date; }
}

