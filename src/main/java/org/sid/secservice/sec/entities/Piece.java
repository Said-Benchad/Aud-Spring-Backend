package org.sid.secservice.sec.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Data @AllArgsConstructor @NoArgsConstructor
public class Piece {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPiece;
    private String nom;
    private String reference;
    private float PRIX;
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Voiture> voiture ;

}