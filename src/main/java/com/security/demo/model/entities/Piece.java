package com.security.demo.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Getter @Setter
@Data @AllArgsConstructor @NoArgsConstructor
public class Piece {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPiece;
    private String nom;
    private String reference;
    private float PRIX;
    @ManyToMany
    private Collection<Voiture> voiture ;

}
