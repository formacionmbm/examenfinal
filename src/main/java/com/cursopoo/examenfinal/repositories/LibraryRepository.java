package com.cursopoo.examenfinal.repositories;

import com.cursopoo.examenfinal.entities.Category;
import com.cursopoo.examenfinal.entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Long> {
    List<Library> findByCategory(Category category);
}