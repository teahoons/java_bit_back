package com.example.java_bit_back.repository;

import com.example.java_bit_back.entity.CoinList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoinListRepository extends JpaRepository<CoinList, Long> {
    Optional<CoinList> findByKoreanName(String koreanName);
}
