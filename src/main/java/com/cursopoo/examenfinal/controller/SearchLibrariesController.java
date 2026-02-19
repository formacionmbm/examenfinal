package com.cursopoo.examenfinal.api;

import com.cursopoo.examenfinal.buscador.intefaces.Search;
import com.cursopoo.examenfinal.entities.Category;
import com.cursopoo.examenfinal.entities.Library;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestSearchLibraries {

    private final Search search;

    // Ruta corregida a /li para que el test.get("/api/li") funcione
    @GetMapping("/li")
    public List<Library> findAll() {
        log.info("[findAll]");
        return search.findAllLibraries();
    }

    // Ruta corregida a /s/li para que el test.post("/api/s/li") funcione
    @PostMapping("/s/li")
    public List<Library> search(@RequestParam("texto") String texto) {
        log.info("[search]");
        log.debug("[texto:{}]", texto);

        List<Library> libraries = search.findLibrariesByTexto(texto);
        log.debug("Libraries find: {}", libraries);

        return libraries;
    }

    // Ruta para obtener categorías (opcional pero recomendada)
    @GetMapping("/categories")
    public List<Category> findAllCategories() {
        log.info("[findAllCategories]");
        return search.findAllCategories();
    }
}