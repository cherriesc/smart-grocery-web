package com.grocery.backend.basket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BasketCompareResponse {

    private LocalDate dateUsed;

    // Старое поле (оставляем, чтобы ничего не сломать)
    private List<StoreTotal> results;

    // Старые поля (оставляем)
    private Long bestStoreId;
    private BigDecimal bestTotal;

    // НОВОЕ: детальная разбивка по продуктам для каждого магазина
    private List<StoreBreakdown> storeBreakdowns;

    // НОВОЕ: детальная информация о самом дешевом магазине
    private StoreBreakdown cheapestStore;

    public BasketCompareResponse(
            LocalDate dateUsed,
            List<StoreTotal> results,
            Long bestStoreId,
            BigDecimal bestTotal,
            List<StoreBreakdown> storeBreakdowns,
            StoreBreakdown cheapestStore
    ) {
        this.dateUsed = dateUsed;
        this.results = results;
        this.bestStoreId = bestStoreId;
        this.bestTotal = bestTotal;
        this.storeBreakdowns = storeBreakdowns;
        this.cheapestStore = cheapestStore;
    }

    public LocalDate getDateUsed() { return dateUsed; }
    public List<StoreTotal> getResults() { return results; }
    public Long getBestStoreId() { return bestStoreId; }
    public BigDecimal getBestTotal() { return bestTotal; }

    public List<StoreBreakdown> getStoreBreakdowns() { return storeBreakdowns; }
    public StoreBreakdown getCheapestStore() { return cheapestStore; }

    // ---------- Старый DTO, который ты уже использовал ----------
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

    // ---------- НОВЫЕ DTO для детального вывода ----------
    public static class StoreBreakdown {
        private Long storeId;
        private String storeName;
        private BigDecimal storeTotal;
        private List<LineItem> items;

        public StoreBreakdown(Long storeId, String storeName, BigDecimal storeTotal, List<LineItem> items) {
            this.storeId = storeId;
            this.storeName = storeName;
            this.storeTotal = storeTotal;
            this.items = items;
        }

        public Long getStoreId() { return storeId; }
        public String getStoreName() { return storeName; }
        public BigDecimal getStoreTotal() { return storeTotal; }
        public List<LineItem> getItems() { return items; }
    }

    public static class LineItem {
        private Long productId;
        private String productName;
        private Integer quantity;
        private BigDecimal unitPrice;
        private BigDecimal lineTotal;

        public LineItem(Long productId, String productName, Integer quantity, BigDecimal unitPrice, BigDecimal lineTotal) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.lineTotal = lineTotal;
        }

        public Long getProductId() { return productId; }
        public String getProductName() { return productName; }
        public Integer getQuantity() { return quantity; }
        public BigDecimal getUnitPrice() { return unitPrice; }
        public BigDecimal getLineTotal() { return lineTotal; }
    }
}
