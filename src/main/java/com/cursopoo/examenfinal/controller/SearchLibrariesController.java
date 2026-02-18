package com.cursopoo.examenfinal.controller;

import com.cursopoo.examenfinal.buscador.intefaces.Search;
import com.cursopoo.examenfinal.entities.Category;
import com.cursopoo.examenfinal.entities.Library;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class SearchLibrariesController {

    private static final String VIEW_SEARCH = "t_libraries";
    private final Search search;

    @GetMapping
    public String goToSearchForm(Model model){
        log.info("[goToSearchForm]");

        return VIEW_SEARCH;
    }

    @PostMapping
    public String search(){
        log.info("[search]");
        log.debug("[texto:{}",texto);

        List<Library> libraries= search.findLibrariesByTexto(texto);
        log.debug("Packages find: {}",libraries);
        loadCategories(model);

        model.addAttribute("elements",libraries);
        model.addAttribute("texto",texto);

        return VIEW_SEARCH;
    }

    private void loadCategories(Model model){
        log.debug("[loadCategories]");
        List<Category> categories = search.findAllCategories();
        model.addAttribute("categories",categories);

    }
}
