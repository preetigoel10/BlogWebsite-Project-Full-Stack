package com.blog.backend.configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CORSconfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**").allowedOrigins("http://localhost:4200");
//        registry.addMapping("/*").allowedHeaders("*").allowedOrigins("*").allowedMethods("*")
//                .allowCredentials(true);
    }
}
