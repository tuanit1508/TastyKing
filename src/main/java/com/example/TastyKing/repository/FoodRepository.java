package com.example.TastyKing.repository;

import com.example.TastyKing.entity.Category;
import com.example.TastyKing.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {





    List<Food> findByCategory_CategoryID(Long categoryId);
}
