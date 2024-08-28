package com.example.java_bit_back.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class CoinInfoService {

    private final RestTemplate restTemplate;

    public CoinInfoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> getCoinInfo(String coinSymbol) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.bithumb.com/public/ticker/" + coinSymbol + "_KRW")
                .toUriString();

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        return (Map<String, Object>) response.get("data");
    }
}