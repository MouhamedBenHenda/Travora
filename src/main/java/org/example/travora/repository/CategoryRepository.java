package org.example.travora.repository;

import org.example.travora.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category , String> {

}
