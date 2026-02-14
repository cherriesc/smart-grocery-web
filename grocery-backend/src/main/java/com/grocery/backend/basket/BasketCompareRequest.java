package com.grocery.backend.basket;

import java.util.List;

public class BasketCompareRequest {
    private List<Item> items;

    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }

    public static class Item {
        private Long productId;
        private int quantity;

        public Long getProductId() { return productId; }
        public int getQuantity() { return quantity; }

        public void setProductId(Long productId) { this.productId = productId; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }
}
