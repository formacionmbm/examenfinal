package com.cursopoo.examenfinal.repositories;

import com.cursopoo.examenfinal.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByName(@Param("name") String name);
}
