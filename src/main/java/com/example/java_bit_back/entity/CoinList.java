package com.example.java_bit_back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "coin_list")
public class CoinList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String market;

    @Column(name = "korean_name", nullable = false)
    private String koreanName;

    @Column(name = "english_name", nullable = false)
    private String englishName;
}
