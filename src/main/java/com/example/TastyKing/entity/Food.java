package com.example.TastyKing.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodID;

    @ManyToOne
    @JoinColumn(name = "categoryID", nullable = false)
    private Category category;

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private Double foodPrice;

    @Column(nullable = false)
    private Double foodCost;

    @Column(length = 10000)
    private String description;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private String foodImage;

    @Column(nullable = true)
    private String foodImage2;

    @Column(nullable = true)
    private String foodImage3;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ComboFood> comboFoods;
}
