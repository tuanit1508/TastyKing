package com.example.TastyKing.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "combofood")
public class ComboFood {
    @EmbeddedId
    private ComboFoodId id;

    @ManyToOne
    @MapsId("comboID")
    @JoinColumn(name = "comboID")
    private Combo combo;

    @ManyToOne
    @MapsId("foodID")
    @JoinColumn(name = "foodID")
    private Food food;

    @Column(nullable = false)
    private Integer quantity;
}
