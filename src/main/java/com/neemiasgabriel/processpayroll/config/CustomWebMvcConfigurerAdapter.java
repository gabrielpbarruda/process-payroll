package com.neemiasgabriel.processpayroll.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableCaching
@EntityScan(basePackages = {"com.neemiasgabriel.processpayroll.model"})
@EnableJpaRepositories(basePackages = {"com.neemiasgabriel.processpayroll.repository"})
@ComponentScan(basePackages = {"com.neemiasgabriel.processpayroll.utility", "com.neemiasgabriel.processpayroll.service"})
public class CustomWebMvcConfigurerAdapter implements WebMvcConfigurer {
}
