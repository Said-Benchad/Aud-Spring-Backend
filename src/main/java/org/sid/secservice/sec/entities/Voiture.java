package org.sid.secservice.sec.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voiture {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Integer anneeFab;
    private String modele;
    private String finition;
    @OneToOne
    private Moteur moteur;
//    @OneToMany(mappedBy = "voiture" , fetch = FetchType.LAZY)
//    private Collection<Devis> devis ;
    @ManyToOne
    private AppUser proprietaire; // a discuter had lblan
    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<Piece> pieces;
//    @OneToMany(mappedBy = "voiture")
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private List<Prixmainouevre> prixMainOeuvres;
//    @OneToMany(mappedBy = "voiture" , fetch = FetchType.LAZY)
//    private Collection<PrixServices> prixService;
//    @ManyToMany(fetch = FetchType.LAZY)
//    private Collection<Packages> packages;
public Voiture(String modele ,String finition , Moteur moteur){
    this.modele = modele;
    this.finition=finition;
    this.moteur=moteur;
}

}