package com.example.java_bit_back.service;

import com.example.java_bit_back.entity.CoinList;
import com.example.java_bit_back.repository.CoinListRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class CoinListService {

    @Autowired
    private CoinListRepository coinListRepository;

    private static final String API_URL = "https://api.bithumb.com/v1/market/all?isDetails=false";

    public void fetchAndSaveMarkets() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .get()
                .url(API_URL)
                .addHeader("content-type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();

            if (response.body() != null) {
                String responseString = response.body().string();
//                log.info("API Response: {}", responseString);

                // JSON 파싱을 위해 ObjectMapper 사용
                ObjectMapper objectMapper = new ObjectMapper();
                // JSON 응답을 배열로 변환
                CoinList[] coinListArray = objectMapper.readValue(responseString, CoinList[].class);

                // 각 코인 정보를 DB에 저장
                for (CoinList coinList : coinListArray) {
                    coinListRepository.save(coinList);
                }
            } else {
                log.error("API 응답이 null입니다.");
            }
        } catch (IOException e) {
            log.error("API 호출 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException(e);
        }
//        log.info("coin list service 완료");
    }
}
