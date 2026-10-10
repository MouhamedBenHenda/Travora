package org.example.travora.service;

import lombok.RequiredArgsConstructor;
import org.example.travora.Mapper.CategoryMapper;
import org.example.travora.dto.request.CategoryRequest;
import org.example.travora.dto.response.CategoryResponse;
import org.example.travora.model.Category;
import org.example.travora.repository.CategoryRepository;
import org.example.travora.service.interfaceS.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest) {

        Category category = new Category();
        category.setName(categoryRequest.getName());

        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.fromCategory(savedCategory);
    }

    @Override
    public CategoryResponse updateCategory(
            CategoryRequest categoryRequest, String categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        category.setName(categoryRequest.getName());

        Category updatedCategory = categoryRepository.save(category);

        return categoryMapper.fromCategory(updatedCategory);
    }

    @Override
    public void deleteCategory(String categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryResponse> getAllCategory() {

        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::fromCategory)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getCategoryById(String categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        return categoryMapper.fromCategory(category);
    }
}