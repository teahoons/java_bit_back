package com.example.java_bit_back.controller;

import com.example.java_bit_back.service.CoinInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/coin")
public class CoinInfoController {

    private final CoinInfoService coinInfoService;

    public CoinInfoController(CoinInfoService coinInfoService) {
        this.coinInfoService = coinInfoService;
    }

    @GetMapping("/{coinSymbol}")
    public ResponseEntity<Map<String, Object>> getCoinInfo(@PathVariable String coinSymbol) {
        try {
            // BTC_KRW -> KRW-BTC로 변환
            String marketCode = new StringBuilder(coinSymbol)
                    .replace(0, 3, coinSymbol.substring(4))
                    .replace(4, 7, coinSymbol.substring(0, 3))
                    .toString()
                    .replace("_", "-");

            Map<String, Object> coinInfo = coinInfoService.getCoinInfo(marketCode);
            return ResponseEntity.ok(coinInfo);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
