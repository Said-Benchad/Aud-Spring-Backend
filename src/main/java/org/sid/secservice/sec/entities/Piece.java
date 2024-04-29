package org.sid.secservice.sec.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Piece {
    public Piece( String nom , String reference , float prix){
        this.nom = nom;
        this.reference = reference;
        this.prix = prix;
    }
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPiece;
    private String nom;
    private String reference;
    private float prix;
    @ManyToMany
    private Collection<Voiture> voiture ;

}
