package com.example.TastyKing.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class RewardPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rewardID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;



    @Column(nullable = false)
    private Double balance;



}
