package ru.kpfu.itis.javalab.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = {"ru.kpfu.itis.javalab.controllers"})
@EnableWebMvc
@EnableAspectJAutoProxy
public class WebConfiguration extends WebMvcConfigurerAdapter {

}
