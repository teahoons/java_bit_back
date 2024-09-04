package com.example.java_bit_back.controller;

import com.example.java_bit_back.service.CoinListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/coinlist")
@Slf4j
public class CoinListController {

    private final CoinListService coinListService;

    @Autowired
    public CoinListController(CoinListService coinListService) {
        this.coinListService = coinListService;
    }

    @GetMapping("/check")
    public ResponseEntity<?> getCoinList() {
        try {
            // CoinList를 단순히 외부 API로부터 받아옴
            Object coinInfo = coinListService.getCoinList();
            return ResponseEntity.ok(coinInfo);
        } catch (IOException e) {
            log.error("Error retrieving coin information: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve coin information");
        }
    }
//    public String coinListCheck() {
//        try {
//            coinListService.CoinListCheck();
//            return "Coin market data fetched and saved successfully!";
//        } catch (Exception e) {
//            log.error(e.toString());
//            return "Failed to fetch and save coin market data: " + e.getMessage();
//        }
//    }

    // API를 호출하고 데이터를 저장하는 엔드포인트
//    @GetMapping("/fetch")
//    public String fetchAndSaveMarkets() {
//        try {
//            coinListService.fetchAndSaveMarkets();
//            return "Coin market data fetched and saved successfully!";
//        } catch (Exception e) {
//            log.error(e.toString());
//            return "Failed to fetch and save coin market data: " + e.getMessage();
//        }
//    }
}
