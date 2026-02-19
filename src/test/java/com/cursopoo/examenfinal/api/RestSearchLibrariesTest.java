package com.cursopoo.examenfinal.api;

import com.cursopoo.examenfinal.buscador.interfaces.Search;
import com.cursopoo.examenfinal.entities.Library;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestSearchLibraries.class)
class RestSearchLibrariesTest {


    @Autowired
    private MockMvc mockMvc;



    @MockitoBean
    private Search search;

    // =============================
    // GET /api
    // =============================
    @Test
    @DisplayName("GET /api debe devolver todas las libraries en JSON")
    void testFindAll() throws Exception {
        // Arrange
        Library lib1 = new Library(1L, "com.spring","Spring Boot", "3.2.0", List.of());
        Library lib2 = new Library(2L, "org.hibernate","Hibernate", "6.4.0", List.of());
        List<Library> libraries = List.of(lib1, lib2);

        when(search.findAllLibraries()).thenReturn(libraries);

        // Act & Assert
        mockMvc.perform(get("/api/li"))
                .andExpect(status().isOk());

        verify(search, times(1)).findAllLibraries();
    }

    // =============================
    // POST /api
    // =============================
    @Test
    @DisplayName("POST /api debe devolver libraries filtradas por texto en JSON")
    void testSearch() throws Exception {
        // Arrange
        String texto = "Framework";
        Library lib = new Library(1L,"com.spring", "Spring Boot", "3.2.0", List.of());
        List<Library> libraries = List.of(lib);

        when(search.findLibrariesByTexto(texto)).thenReturn(libraries);

        // Act & Assert
        mockMvc.perform(post("/api/s/li")
                        .param("texto", texto)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());

        verify(search, times(1)).findLibrariesByTexto(texto);
    }

}