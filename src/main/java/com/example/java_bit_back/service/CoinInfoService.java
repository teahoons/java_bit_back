package com.example.java_bit_back.service;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.List;
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

    public Object getCoinInfo(String marketCode) throws IOException {
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

            // 응답이 JSON 배열인지 객체인지 확인한 후 적절하게 처리
            if (jsonResponse.trim().startsWith("[")) {
                // JSON 응답이 배열인 경우
                return objectMapper.readValue(jsonResponse, new TypeReference<List<Map<String, Object>>>(){});
            } else {
                // JSON 응답이 객체인 경우
                return objectMapper.readValue(jsonResponse, new TypeReference<Map<String, Object>>(){});
            }
        }
    }
}
