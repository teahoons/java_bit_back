package com.example.java_bit_back.controller;

import com.example.java_bit_back.service.CoinInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/coin")
public class CoinInfoController {

    private final CoinInfoService coinInfoService;

    public CoinInfoController(CoinInfoService coinInfoService) {
        this.coinInfoService = coinInfoService;
    }

    @GetMapping("/{coinSymbol}")
    public Map<String, Object> getCoinInfo(@PathVariable String coinSymbol) {
        return coinInfoService.getCoinInfo(coinSymbol);
    }
}
