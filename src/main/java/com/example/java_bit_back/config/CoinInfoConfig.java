package com.example.java_bit_back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CoinInfoConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}