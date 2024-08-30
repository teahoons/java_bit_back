package com.example.java_bit_back.controller;

import com.example.java_bit_back.service.CoinInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/coin")
public class CoinInfoController {

    private final CoinInfoService coinInfoService;

    public CoinInfoController(CoinInfoService coinInfoService) {
        this.coinInfoService = coinInfoService;
    }

    @GetMapping("/{coinSymbol}")

    public ResponseEntity<Map<String, Object>> getCoinInfo(@PathVariable String coinSymbol) {

    }
}
