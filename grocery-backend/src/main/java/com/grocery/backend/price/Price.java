package com.grocery.backend.price;

import com.grocery.backend.product.Product;
import com.grocery.backend.store.Store;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "prices")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private LocalDate date;

    public Price() {}

    public Price(Product product, Store store, BigDecimal price, LocalDate date) {
        this.product = product;
        this.store = store;
        this.price = price;
        this.date = date;
    }

    public Long getId() { return id; }
    public Product getProduct() { return product; }
    public Store getStore() { return store; }
    public BigDecimal getPrice() { return price; }
    public LocalDate getDate() { return date; }

    public void setId(Long id) { this.id = id; }
    public void setProduct(Product product) { this.product = product; }
    public void setStore(Store store) { this.store = store; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setDate(LocalDate date) { this.date = date; }
}

