package com.example.letts_shop.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class ApplicationContextConfig {

    @Bean
    public CommonsMultipartResolver getCommonsMultipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1073741824);
        return multipartResolver;
    }

}
