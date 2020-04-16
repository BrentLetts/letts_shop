package com.example.letts_shop.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Product extends AbstractEntity {

    @NotNull(message = "*Product name is required")
    private String productName;

    private String productDescription;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "*Price has to be a non-negative number")
    private BigDecimal price;

    @DecimalMin(value = "0.00", message = "*Quantity has to be a non-negative number")
    private int quantity;

    @ManyToOne
    private ShoppingCart shoppingCart;

    public Product() { }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
