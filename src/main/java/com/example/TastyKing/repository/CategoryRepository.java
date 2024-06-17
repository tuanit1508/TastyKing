package com.example.TastyKing.repository;

import com.example.TastyKing.dto.request.UpdateCategoryRequest;
import com.example.TastyKing.entity.Category;
import com.example.TastyKing.entity.User;
import org.mapstruct.MappingTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByCategoryName(String categoryName);
}
