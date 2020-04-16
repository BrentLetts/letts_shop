package com.example.letts_shop.controllers;

import com.example.letts_shop.models.Product;
import com.example.letts_shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public String displayIndex(Model model){
        model.addAttribute("title", "Product Page");
        model.addAttribute("products", productRepository.findAll());
        return "products/index";
    }

    @GetMapping("/addProduct")
    public String displayAddProduct(Model model){
        model.addAttribute("title", "Add a New Product");
        model.addAttribute(new Product());
        return "products/addProduct";
    }

    @PostMapping("/addProduct")
    public String processAddProduct(@ModelAttribute @Valid Product product,
                                    Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Add a New Product");
            return "products/addProduct";
        }

        for(Product p : productRepository.findAll()){
            if(product.getProductName() == p.getProductName()){
                errors.rejectValue("productName", "productName.alreadyexists", "This product already exists");
                model.addAttribute("title", "Add a New Product");
                break;
            }
            return "products/add";
        }

        productRepository.save(product);

        return "redirect:";
    }

}
