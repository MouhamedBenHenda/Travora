package org.example.travora.controller;

import lombok.RequiredArgsConstructor;
import org.example.travora.dto.request.CategoryRequest;
import org.example.travora.dto.response.CategoryResponse;
import org.example.travora.service.interfaceS.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryController {
    private final CategoryService categoryService;

    // Ajouter une catégorie
    @PostMapping
    public ResponseEntity<CategoryResponse> addCategory(
            @RequestBody CategoryRequest categoryRequest) {

        CategoryResponse response =
                categoryService.addCategory(categoryRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Modifier une catégorie
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable String categoryId,
            @RequestBody CategoryRequest categoryRequest) {

        CategoryResponse response =
                categoryService.updateCategory(categoryRequest, categoryId);

        return ResponseEntity.ok(response);
    }

    // Supprimer une catégorie
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable String categoryId) {

        categoryService.deleteCategory(categoryId);

        return ResponseEntity.noContent().build();
    }

    // Récupérer toutes les catégories
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategory() {

        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    // Récupérer une catégorie par ID
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @PathVariable String categoryId) {

        return ResponseEntity.ok(
                categoryService.getCategoryById(categoryId));
    }
}
