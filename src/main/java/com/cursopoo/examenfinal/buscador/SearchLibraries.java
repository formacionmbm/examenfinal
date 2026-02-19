package com.cursopoo.examenfinal.buscador;

import com.cursopoo.examenfinal.buscador.intefaces.Search;
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
//CAMBIOS nada en el try y devolvia null y no estaba puesto repository
    @Override
    public List<Library> findAllLibraries() {
        log.info("[findAllLibraries]");
        try{
            List<Library> res = repository.findAll();
            return (res != null) ? res : List.of();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new AppException(e.getMessage());
        }
    }
//CAMBIOS try vacio y sacaba runtime exception en vez app
    @Override
    public List<Category> findAllCategories() {
        log.info("[findAllCategories]");
        try{
            List<Category> res = categoryRepository.findAll();
            return (res != null) ? res : List.of();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new AppException(e.getMessage());
        }
    }






//CAMBIOS le falta el override devolvia nulls y puse app exception
    @Override
    public List<Library> findLibrariesByTexto(String texto) {
        log.info("[findLibrariesByTexto]");
        log.debug("[texto:{}]", texto);

        try {
            texto = procesarTexto(texto);

            if (texto == null || texto.isBlank()) {
                return findAllLibraries();
            }

            String[] nameCategories = texto.split(" ");
            List<Library> libraries = new ArrayList<>();

            for (String nameCategory : nameCategories) {
                log.debug("Buscamos la categoría:{}", nameCategory);

                Optional<Category> categoryOpt = categoryRepository.findByName(nameCategory);
                if (categoryOpt.isPresent()) {
                    Category cat = categoryOpt.get();

                    // esto lo pide el test
                    List<Library> libs = repository.findByCategory(cat);

                    if (libs != null) {
                        libraries.addAll(libs);
                    }
                }
            }

            log.debug("[libraries:{}]", libraries);
            return libraries;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AppException(e.getMessage());
        }
    }
}
