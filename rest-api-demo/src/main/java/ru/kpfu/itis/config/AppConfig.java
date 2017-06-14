package ru.kpfu.itis.config;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by Safin Ramil on 24.04.17
 * Safin.Ramil@ordotrans.ru
 */

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate gitHubSearchReposRestTemplate() {

        return new RestTemplateBuilder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create()
                        .setConnectionTimeToLive(30, TimeUnit.SECONDS).build()))
                .build();
    }
}
