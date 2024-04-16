package org.sid.secservice.sec.entities;

import lombok.*;

import javax.persistence.*;
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
    @OneToMany(mappedBy = "voiture" , fetch = FetchType.LAZY)
    private Collection<Devis>devis ;
    @ManyToOne
    private AppUser proprietaire; // a discuter had lblan
    @OneToMany(mappedBy = "voiture" , fetch = FetchType.LAZY)
    private Collection<PrixService> prixService;
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Packages> packages;
    @OneToOne
    private Moteur moteur;
    @ManyToMany(mappedBy = "voiture" , fetch = FetchType.LAZY)
    private Collection<Piece> pieces;


}




