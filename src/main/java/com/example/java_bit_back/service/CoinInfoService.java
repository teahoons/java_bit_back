package com.example.java_bit_back.service;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
public class CoinInfoService {

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public CoinInfoService() {
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public Map<String, Object> getCoinInfo(String marketCode) throws IOException {
        String url = "https://api.bithumb.com/v1/ticker?markets=" + marketCode;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String jsonResponse = response.body().string();

            // API 응답이 JSON 객체이므로, JSON 객체로 파싱
            Map<String, Object> resultMap = objectMapper.readValue(jsonResponse, new TypeReference<Map<String, Object>>(){});

            // 데이터 확인을 위해 로그를 출력하거나 필요한 처리를 할 수 있습니다.
            System.out.println("API Response: " + resultMap);
            // 결과를 반환
            return resultMap;
        }
    }
}
