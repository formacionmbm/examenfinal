package com.cursopoo.examenfinal.api;

import com.cursopoo.examenfinal.buscador.intefaces.Search;
import com.cursopoo.examenfinal.entities.Library;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class RestSearchLibraries {

    private final Search search;

    // Coincide con mockMvc.perform(get("/api/li"))
    @GetMapping("/li")
    public List<Library> findAll() {
        log.info("[findAll] Solicitando todas las librerías");
        return search.findAllLibraries();
    }

    // Coincide con mockMvc.perform(post("/api/s/li").param("texto", texto))
    @PostMapping("/s/li")
    public List<Library> search(@RequestParam String texto) {
        log.info("[search] Buscando librerías por texto: {}", texto);
        return search.findLibrariesByTexto(texto);
    }
}