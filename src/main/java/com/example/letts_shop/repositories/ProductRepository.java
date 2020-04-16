package com.example.letts_shop.repositories;

import com.example.letts_shop.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
