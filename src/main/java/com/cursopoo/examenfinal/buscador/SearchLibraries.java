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

    @Override
    public List<Library> findAllLibraries() {
        log.info("[findAllLibraries]");
        try {

            return repository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public List<Category> findAllCategories() {
        log.info("[findAllCategories]");
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public List<Library> findLibrariesByTexto(String texto) {
        log.info("[findLibrariesByTexto]");
        log.debug("[texto:{}]", texto);

        List<Library> libraries = new ArrayList<>();

        try {
            // 1. Evitamos que el programa explote si el texto llega nulo
            if (texto == null) return libraries;

            // 2. Procesamos el texto (se supone que lo pasa a MAYÚSCULAS)
            texto = procesarTexto(texto);

            // 3. Dividimos el texto por espacios para buscar cada palabra como categoría
            String[] nameCategories = texto.split(" ");

            for (String nameCategory : nameCategories) {
                log.debug("Buscamos la categoría:{}", nameCategory);

                // 4. Buscamos la categoría en la base de datos
                Optional<Category> category = categoryRepository.findByName(nameCategory);

                if (category.isPresent()) {
                    // 5. Si existe, buscamos las librerías de esa categoría y las añadimos a la lista
                    List<Library> foundLibraries = repository.findByCategory(category.get());
                    libraries.addAll(foundLibraries);
                }
            }

            log.debug("[libraries:{}]", libraries);

            // 6. DEVOLVEMOS LA LISTA (nunca null) para que el test sea verde
            return libraries;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AppException(e.getMessage());
        }
    }
}