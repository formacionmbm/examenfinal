package com.cursopoo.examenfinal.api;

import com.cursopoo.examenfinal.buscador.intefaces.Search;
import com.cursopoo.examenfinal.entities.Category;
import com.cursopoo.examenfinal.entities.Library;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestSearchLibraries {

    private final Search search;
// CAMBIOS en los dos get no habia nadaxqlo que dan conflicto separe rutas
    //CAMBIOS estaba libreria en vez de li, que es el que llama el test
    @GetMapping ("/li")
    public List<Library> findAll(){
        log.info("[findAll]");
        return search.findAllLibraries();
    }
//CAMBIOS texto no estaba definido
    @PostMapping("/s/li")
    public List<Library> search(@RequestParam(required = false) String texto){
        log.info("[search]");
        log.debug("[texto:{}",texto);

        List<Library> libraries= search.findLibrariesByTexto(texto);
        log.debug("Libraries find: {}",libraries);

        return libraries;
    }

    @GetMapping ("/categories")
    public List<Category> findAllCategories(){
        log.info("[findAllCategories]");
        return search.findAllCategories();
    }
}
