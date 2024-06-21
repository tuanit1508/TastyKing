package com.example.TastyKing.repository;

import com.example.TastyKing.entity.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Long> {

    List<Combo> findByEndDateBefore(LocalDateTime now);

    List<Combo> findByOpenDate(LocalDateTime now);
}
