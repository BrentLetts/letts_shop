package com.example.letts_shop.controllers;

import com.example.letts_shop.dto.ProductDto;
import com.example.letts_shop.models.DBFile;
import com.example.letts_shop.models.Product;
import com.example.letts_shop.repositories.DBFileRepository;
import com.example.letts_shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;
import java.util.*;


@Controller
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DBFileRepository dbFileRepository;

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
                                    Errors errors, Model model,
                                    @RequestParam(value="files", required = false) CommonsMultipartFile[] images){
        if(errors.hasErrors()){
            model.addAttribute("title", "Add a New Product");
            model.addAttribute("errors", errors);
            return "/products/addProduct";
        }

        for(Product p : productRepository.findAll()){
            if(product.getProductName() == p.getProductName()){
                errors.rejectValue("productName", "productName.alreadyexists", "This product already exists");
                model.addAttribute("title", "Add a New Product");
                return "products/addProduct";
            }

        }

        // Check if there are any files to upload
        if(images != null && images.length > 0){
            // Loop and set the files to the DBFile fields
            for (CommonsMultipartFile aFile : images){
                DBFile uploadFile = new DBFile();
                uploadFile.setData(Base64.getEncoder().encodeToString(aFile.getBytes()));
                uploadFile.setProduct(product);
                product.addFile(uploadFile);
            }
        }

        productRepository.save(product);

        return "redirect:";
    }

    @GetMapping("/viewProductDetail")
    public String viewProductDetail(@RequestParam("id") int id, Model model){
            Optional<Product> productOpt = productRepository.findById(id);
            if(productOpt.isEmpty()){
                model.addAttribute("title", "No product with the id of " + id);
                return "products/viewProductDetail";
            }
            Product product = productOpt.get();
            model.addAttribute("title", "Viewing Product Details for " + product.getProductName());

            model.addAttribute(product);
            return "products/viewProductDetail";
    }

    @GetMapping("/editProduct")
    public String displayEditProduct(@RequestParam("id") int id, Model model){
        Optional<Product> productOpt = productRepository.findById(id);
        if(productOpt.isEmpty()){
            model.addAttribute("title", "No product with the id of " + id);
            return "products/viewProductDetail";
        }
        Product product = productOpt.get();
        model.addAttribute("title", "Viewing Product Details for " + product.getProductName());
        model.addAttribute("product", product);

        return "products/editProduct";
    }


    @PostMapping("/editProduct")
    public String processEdit(@ModelAttribute @Valid Product productEdit,
            @RequestParam("id") int productId
            , Errors errors, Model model
            , @RequestParam(value="imageIds", required = false) int[] imageIds
            , @RequestParam(value="files", required = false) CommonsMultipartFile[] images ){
        Product originalProduct = productRepository.findById(productId).get();

        if(errors.hasErrors()){
            model.addAttribute("title", "Viewing Product Details for " + productEdit.getProductName());
            return "products/editProduct";
        }

        // TODO: Fix the upload so it saves
        // Check if there are any files to upload
        if(images != null && images.length > 0){

            // Loop and set the files to the DBFile fields
            for (CommonsMultipartFile aFile : images){
                DBFile uploadFile = new DBFile();
                uploadFile.setData(Base64.getEncoder().encodeToString(aFile.getBytes()));
                uploadFile.setProduct(originalProduct);
                productEdit.addFile(uploadFile);
            }
        }

        // Check for files to delete
        if(imageIds != null){
            for(int id : imageIds){

                // Delete from database
                dbFileRepository.deleteById(id);
                productEdit.removeFile(id);
            }
        }
        originalProduct.getDbFiles();

        originalProduct.setPrice(productEdit.getPrice());
        originalProduct.setProductDescription(productEdit.getProductDescription());
        originalProduct.setProductName(productEdit.getProductName());
        originalProduct.setQuantity(productEdit.getQuantity());
        originalProduct.setDbFiles(productEdit.getDbFiles());

        productRepository.save(originalProduct);

        return "/products/editProduct?id=" + productId;
    }


    @GetMapping("/deleteProduct")
    public String displayDelete(Model model){
        model.addAttribute("title", "Select Products to delete");
        model.addAttribute("products", productRepository.findAll());
        return "/products/delete";
    }

    @PostMapping("/deleteProduct")
    public String processDelete(@RequestParam("productIds") int[] ids, Model model){
        for(int i : ids){
            productRepository.deleteById(i);
        }

        return "redirect:";
    }

}
