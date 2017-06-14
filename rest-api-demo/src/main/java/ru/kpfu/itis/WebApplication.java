package ru.kpfu.itis;


import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {


    public static void main(String[] args) {
        SpringApplication webApplication = new SpringApplication(WebApplication.class);
        webApplication.setBannerMode(Banner.Mode.OFF);
        webApplication.run(args);
    }
}
