package com.example.letts_shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String displayError(Exception ex, Model model){
        model.addAttribute("message", ex);
        return "error";
    }

}
