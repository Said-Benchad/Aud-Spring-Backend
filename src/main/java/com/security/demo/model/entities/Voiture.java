package com.security.demo.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Setter
@Getter
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Voiture {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String numChassis ;
    private Integer anneeFab;
    private String modele;
    private String finition;
    @ManyToOne
    private Client proprietaire; // a discuter had lblan
    @OneToMany(mappedBy = "voiture" , fetch = FetchType.LAZY)
    private Collection<PrixService> prixService;
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Packages> packages;


}




