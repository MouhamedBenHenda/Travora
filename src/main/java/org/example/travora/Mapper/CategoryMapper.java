package org.example.travora.Mapper;

import org.example.travora.dto.request.CategoryRequest;
import org.example.travora.dto.response.CategoryResponse;
import org.example.travora.model.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
public CategoryResponse fromCategory(Category category) {

    CategoryResponse categoryResponse = new CategoryResponse() ;
    categoryResponse.setId(category.getId());
    categoryResponse.setName(category.getName());
    return  categoryResponse ;


}

}
