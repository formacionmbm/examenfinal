package com.cursopoo.examenfinal.buscador.intefaces;

import com.cursopoo.examenfinal.entities.Category;
import com.cursopoo.examenfinal.entities.Library;

import java.util.List;

public interface Search {

    public List<Library> findAllLibraries();
    public List<Category> findAllCategories();
    public List<Library> findLibrariesByTexto(String texto);


}
