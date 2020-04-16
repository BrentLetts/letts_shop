package com.example.letts_shop.dto;

import com.example.letts_shop.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDto {

    private List<Product> product = new ArrayList<>();

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

}
