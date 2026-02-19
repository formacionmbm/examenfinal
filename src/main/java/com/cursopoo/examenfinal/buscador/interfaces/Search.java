package com.cursopoo.examenfinal.buscador.interfaces;

import com.cursopoo.examenfinal.entities.Category;
import com.cursopoo.examenfinal.entities.Library;

import java.util.List;

public interface Search {

    List<Library> findAllLibraries();
    List<Category> findAllCategories();
    List<Library> findLibrariesByTexto(String texto);
}



