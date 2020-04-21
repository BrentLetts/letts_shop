package com.example.letts_shop.controllers;

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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


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
                                    @RequestParam("files") CommonsMultipartFile[] images){
        if(errors.hasErrors()){
            model.addAttribute("title", "Add a New Product");
            // TODO: remove this when done
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
                uploadFile.setFileName(aFile.getOriginalFilename());
                uploadFile.setFileType(aFile.getContentType());
                uploadFile.setData(aFile.getBytes());
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
            List<DBFile> files = product.getDbFiles();
            List<String> base64Strings = new ArrayList<>();
            for(DBFile file : files){
                String encodeBase64 = Base64.getEncoder().encodeToString(file.getData());
                base64Strings.add("data:" + file.getFileType() + ";base64," + encodeBase64);
            }

            model.addAttribute("images", base64Strings);
            return "products/viewProductDetail";
    }
}
