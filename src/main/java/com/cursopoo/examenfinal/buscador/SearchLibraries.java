package com.cursopoo.examenfinal.buscador;

import com.cursopoo.examenfinal.buscador.interfaces.Search;
import com.cursopoo.examenfinal.entities.Category;
import com.cursopoo.examenfinal.entities.Library;
import com.cursopoo.examenfinal.exceptions.AppException;
import com.cursopoo.examenfinal.repositories.CategoryRepository;
import com.cursopoo.examenfinal.repositories.LibraryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.cursopoo.examenfinal.buscador.util.TextUtilities.procesarTexto;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchLibraries implements Search {

    private final LibraryRepository repository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Library> findAllLibraries() {
        log.info("[findAllLibraries]");
        try{
            return repository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public List<Category> findAllCategories() {
        log.info("[findAllCategories]");
        try{
            return categoryRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new AppException("Error retrieving categories");
        }
    }

    public List<Library> findLibrariesByTexto(String texto){
        log.info("[findLibrariesByTexto]");
        log.debug("[texto:{}]",texto);

        List<Library> libraries=new ArrayList<>();

        try {
            texto = procesarTexto(texto);
            String[] nameCategories = texto.split(" ");

            for (String nameCategory : nameCategories) {
                log.debug("Buscamos la categoría:{}",nameCategory);

                Optional<Category> category = categoryRepository.findByName(nameCategory);
                if(category.isPresent()){
                    libraries.addAll(repository.findByCategory(category.get()));
                }
            }
            log.debug("[libraries:{}]",libraries);
            return libraries;

        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new AppException(e.getMessage());
        }
    }
}



