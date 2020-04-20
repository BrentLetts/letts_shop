package com.example.letts_shop.models;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product extends AbstractEntity {

    @NotNull(message = "*Product name is required")
    @NotEmpty(message = "Product name is required")
    private String productName;

    private String productDescription;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "*Price has to be a non-negative number")
    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    @DecimalMin(value = "0.00", message = "*Quantity has to be a non-negative number")
    private int quantity;

    @ManyToOne
    private ShoppingCart shoppingCart;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<DBFile> dbFiles = new ArrayList<>();

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

    public List<DBFile> getDbFiles() {
        return dbFiles;
    }

    public void setDbFiles(List<DBFile> dbFiles) {
        this.dbFiles = dbFiles;
    }

    public void addFile(DBFile file){ this.dbFiles.add(file); }
}
