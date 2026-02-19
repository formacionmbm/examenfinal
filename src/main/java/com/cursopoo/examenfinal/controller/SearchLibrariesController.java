package com.cursopoo.examenfinal.controller;

import com.cursopoo.examenfinal.buscador.intefaces.Search;
import com.cursopoo.examenfinal.entities.Library;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Service
@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class SearchLibrariesController {

    private final Search search;

    @GetMapping("/li")
    public List<Library> findAll() {
        log.info("[findAll]");
        return search.findAllLibraries();
    }

    @PostMapping("/s/li")
    public List<Library> search(@RequestParam String texto) {
        log.info("[search]");
        log.debug("[texto:{}]", texto);
        return search.findLibrariesByTexto(texto);
    }
}