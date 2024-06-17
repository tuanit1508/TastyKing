package com.example.TastyKing.repository;

import com.example.TastyKing.entity.RewardPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardPointRepository extends JpaRepository<RewardPoint, Integer> {
}
