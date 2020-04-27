package com.example.letts_shop.dto;

import com.example.letts_shop.models.DBFile;
import com.example.letts_shop.models.Product;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ProductDto {

    @NotNull
    private Product product;

    @NotNull
    private List<DBFile> files = new ArrayList<>();

    public ProductDto() { }

    public ProductDto(@NotNull Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<DBFile> getFiles() {
        return files;
    }

    public void setFiles(List<DBFile> files) {
        this.files = files;
    }
}
