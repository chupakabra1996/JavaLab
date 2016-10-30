package ru.kpfu.itis.javalab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = {"ru.kpfu.itis.javalab.controllers"})
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter {




    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setCache(false);
        viewResolver.setContentType("text/html; charset=utf-8");
        viewResolver.setPrefix("/WEB-INF");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }
}
