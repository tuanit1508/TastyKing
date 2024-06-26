package com.example.TastyKing.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class TablePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long tablePositionID;
    @Column(nullable = false)
    private int tableQuantity;

    @Column(nullable = false)
    private String tablePosition;
}
