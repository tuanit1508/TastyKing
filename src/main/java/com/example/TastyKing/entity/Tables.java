package com.example.TastyKing.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tableID;

    @ManyToOne
    @JoinColumn(name = "tablePositionID", nullable = false)
    private TablePosition tablePosition;

    @Column(nullable = false)
    private String tableName;

    @Column(nullable = false)
    private String tableStatus;

    @Column(nullable = false)
    private Integer numOfchair;
}
