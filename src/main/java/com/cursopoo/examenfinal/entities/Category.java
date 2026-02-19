package com.cursopoo.examenfinal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    private long id;
    private String name;
}
