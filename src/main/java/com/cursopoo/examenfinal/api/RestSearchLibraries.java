package com.cursopoo.examenfinal.api;

import com.cursopoo.examenfinal.buscador.intefaces.Search;
import com.cursopoo.examenfinal.entities.Category;
import com.cursopoo.examenfinal.entities.Library;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestSearchLibraries {

    private final Search search;

    @GetMapping
    public List<Library> findAll(){
        log.info("[findAll]");
        return search.findAllLibraries();
    }

    @PostMapping("/s/li")
    public List<Library> search(){
        log.info("[search]");
        log.debug("[texto:{}",texto);

        List<Library> libraries= search.findLibrariesByTexto(texto);
        log.debug("Libraries find: {}",libraries);

        return libraries;
    }

    @GetMapping
    public List<Category> findAllCategories(){
        log.info("[findAllCategories]");
        return search.findAllCategories();
    }
}
