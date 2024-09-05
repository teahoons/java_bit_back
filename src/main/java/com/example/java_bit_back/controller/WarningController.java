package com.example.java_bit_back.controller;


import com.example.java_bit_back.service.CoinListService;
import com.example.java_bit_back.service.WarningService;
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
@RequestMapping("/api/Warning")
@Slf4j
public class WarningController {
    private final WarningService warningService;

    @Autowired
    public WarningController(WarningService warningService) {
        this.warningService = warningService;
    }

    @GetMapping
    public ResponseEntity<?> getWarning() {
        try {
            // CoinList를 단순히 외부 API로부터 받아옴
            Object coinInfo = warningService.getWarning();
            return ResponseEntity.ok(coinInfo);
        } catch (IOException e) {
            log.error("Error retrieving coin information: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve coin information");
        }
    }
}
