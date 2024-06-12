package com.example.TastyKing.service;

import com.example.TastyKing.dto.request.CategoryRequest;
import com.example.TastyKing.dto.response.CategoryResponse;
import com.example.TastyKing.entity.Category;
import com.example.TastyKing.exception.AppException;
import com.example.TastyKing.exception.ErrorCode;
import com.example.TastyKing.mapper.CategoryMapper;
import com.example.TastyKing.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;


    public CategoryResponse createNewCategory(CategoryRequest request){
        if(categoryRepository.existsByCategoryName(request.getCategoryName()))
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        Category category = categoryMapper.toCategory(request);
            category = categoryRepository.save(category);
            return categoryMapper.toCategoryResponse(category);

    }
    public List<CategoryResponse> getAllCategory(){
        var categorys =categoryRepository.findAll();

        return categorys.stream().map(categoryMapper::toCategoryResponse).toList();
    }

}
