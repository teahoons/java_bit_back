package com.example.java_bit_back.controller;

import com.example.java_bit_back.service.CoinInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/coin")
public class CoinInfoController {

    private final CoinInfoService coinInfoService;

    public CoinInfoController(CoinInfoService coinInfoService) {
        this.coinInfoService = coinInfoService;
    }

    @GetMapping("/market/{koreanName}")
    public ResponseEntity<?> getCoinInfoByKoreanName(@PathVariable String koreanName) {
        try {
            // 한글 이름으로 market 조회
            Optional<String> marketOpt = coinInfoService.getMarketByKoreanName(koreanName);

            if (marketOpt.isPresent()) {
                String market = marketOpt.get();
                Object coinInfo = coinInfoService.getCoinInfo(market);
                return ResponseEntity.ok(coinInfo);
            } else {
                System.out.println("No market found for Korean name: " + koreanName);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Coin not found for the given Korean name"));
            }
        } catch (IOException e) {
            System.err.println("Error retrieving coin information for Korean name: " + koreanName);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to retrieve coin information"));
        }
    }

    @GetMapping("/symbol/{coinSymbol}")
    public ResponseEntity<Object> getCoinInfoBySymbol(@PathVariable String coinSymbol) {
        try {
            Object coinInfo = coinInfoService.getCoinInfo(coinSymbol);

            // 반환된 객체의 타입에 따라 처리
            if (coinInfo instanceof Map) {
                return new ResponseEntity<>((Map<String, Object>) coinInfo, HttpStatus.OK);
            } else if (coinInfo instanceof List) {
                return new ResponseEntity<>((List<Map<String, Object>>) coinInfo, HttpStatus.OK);
            } else {
                // 예상하지 못한 형식의 응답이 올 경우
                return new ResponseEntity<>(Map.of("error", "Unexpected response format"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("error", "Failed to retrieve coin information"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
