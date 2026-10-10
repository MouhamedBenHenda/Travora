package org.example.travora.service.interfaceS;

import org.example.travora.dto.request.CategoryRequest;
import org.example.travora.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse addCategory(CategoryRequest categoryRequest);

    CategoryResponse updateCategory(CategoryRequest categoryRequest, String categoryId);

    void deleteCategory(String categoryId);

    List<CategoryResponse> getAllCategory();

    CategoryResponse getCategoryById(String categoryId);
}
