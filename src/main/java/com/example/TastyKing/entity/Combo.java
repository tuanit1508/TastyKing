package com.example.TastyKing.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "combo")
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comboID;

    @Column(length = 2000)
    private String comboTitle;

    @Column(nullable = false)
    private Double oldPrice;

    @Column(nullable = false)
    private Double newPrice;

    @Column(nullable = false)
    private LocalDateTime openDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(length = 5000)
    private String comboImage;

    @Column(length = 5000)
    private String comboImage2;

    @Column(length = 5000)
    private String comboImage3;

    @Column(length = 5000)
    private String comboDescription;

    @Lob
    @Column(nullable = true)
    private String comboDetail;

    @OneToMany(mappedBy = "combo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ComboFood> comboFoods;
}
