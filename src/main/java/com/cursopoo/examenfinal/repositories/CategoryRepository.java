package com.cursopoo.examenfinal.repositories;

import com.cursopoo.examenfinal.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByNombre(String name);
}
