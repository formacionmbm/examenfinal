package com.cursopoo.examenfinal.repositories;

import com.cursopoo.examenfinal.entities.Category;
import com.cursopoo.examenfinal.entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library,Long> {

    @Query("SELECT l FROM Library l JOIN l.categories c WHERE c = :category")
    List<Library> findByCategory(Category category);
}
