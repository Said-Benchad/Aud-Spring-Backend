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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPiece;
    private String nom;
    private String reference;
    private float PRIX;
    @ManyToMany
    private Collection<Voiture> voiture ;

}
