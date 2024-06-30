package com.orioninc.ProjectRestaurants.config.webclient;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

//    @Bean
//    public WebClient.Builder getWebClientBuilder() {
////        return WebClient.builder().baseUrl("http://localhost:8080/api/restaurants/2/products/get").build();
//        return WebClient.builder();
//    }

    public WebClient webClient() {

//        return WebClient.builder().baseUrl("http://localhost:8080").build();

        return WebClient.builder().baseUrl("http://10.1.11.26:8080").build();
    }
}
