package com.example.java_bit_back.service;

import com.example.java_bit_back.dto.CoinListResponse;
import com.example.java_bit_back.entity.CoinList;
import com.example.java_bit_back.repository.CoinListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CoinListService {

    @Autowired
    private CoinListRepository coinListRepository;

    private static final String API_URL = "https://api.bithumb.com/v1/market/all?isDetails=false";

    public void fetchAndSaveMarkets() {
        RestTemplate restTemplate = new RestTemplate();

        try {
            // API 응답을 직접 문자열로 출력해 확인해보세요
            String responseString = restTemplate.getForObject(API_URL, String.class);
            System.out.println("API Response: " + responseString);

            // DTO로 변환
            CoinListResponse response = restTemplate.getForObject(API_URL, CoinListResponse.class);

            if (response != null && response.getData() != null) {
                for (Map.Entry<String, CoinListResponse.CoinInfo> entry : response.getData().entrySet()) {
                    String coinlist = entry.getKey();
                    CoinListResponse.CoinInfo marketInfo = entry.getValue();

                    CoinList newCoinList = new CoinList();
                    newCoinList.setMarket(coinlist);
                    newCoinList.setKoreanName(marketInfo.getKoreanName());
                    newCoinList.setEnglishName(marketInfo.getEnglishName());

                    coinListRepository.save(newCoinList);
                }
            } else {
                System.err.println("API response is null or data is empty.");
            }
        } catch (HttpClientErrorException e) {
            System.err.println("API 호출 중 오류 발생: " + e.getMessage());
        }
    }
}
