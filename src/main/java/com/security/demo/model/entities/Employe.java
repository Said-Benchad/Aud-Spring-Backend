package com.security.demo.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Employe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_emp;
    private String nom;
    private Integer tel ;
    private String adress ;
    private  Role role ;
    private String mail ;
    private String password ;

}
