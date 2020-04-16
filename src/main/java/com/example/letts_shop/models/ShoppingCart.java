package com.example.letts_shop.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart extends AbstractEntity {

    @OneToMany(mappedBy = "shoppingCart")
    private List<Product> products = new ArrayList<>();

    public ShoppingCart() { }

    public ShoppingCart(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){ this.products.add(product); }

}
