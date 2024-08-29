package com.example.java_bit_back.controller;

import com.example.java_bit_back.service.CoinListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coinlist")
public class CoinListController {

    private final CoinListService coinListService;

    @Autowired
    public CoinListController(CoinListService coinListService) {
        this.coinListService = coinListService;
    }

    // API를 호출하고 데이터를 저장하는 엔드포인트
    @GetMapping("/fetch")
    public String fetchAndSaveMarkets() {
        try {
            coinListService.fetchAndSaveMarkets();
            return "Coin market data fetched and saved successfully!";
        } catch (Exception e) {
            return "Failed to fetch and save coin market data: " + e.getMessage();
        }
    }
}
