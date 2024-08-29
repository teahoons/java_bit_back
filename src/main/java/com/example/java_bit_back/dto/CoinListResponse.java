package com.example.java_bit_back.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CoinListResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private Map<String, CoinInfo> data;

    @Getter
    @Setter
    public static class CoinInfo {
        @JsonProperty("korean_name")
        private String koreanName;

        @JsonProperty("english_name")
        private String englishName;
    }
}
