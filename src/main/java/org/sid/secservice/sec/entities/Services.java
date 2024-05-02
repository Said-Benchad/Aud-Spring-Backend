package org.sid.secservice.sec.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Services {
    public Services(String nom){
        this.nom = nom;
        this.packages = new ArrayList<>();
        this.prixService = new ArrayList<>();
    }
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idService;
    private String nom ;
    @Enumerated(EnumType.STRING)
    private TypeService typeService ;
    @OneToMany(mappedBy = "ser" , fetch = FetchType.LAZY)
    private Collection<PrixServices> prixService ;
    @OneToOne(mappedBy = "service")
    private MainOeuvre mainOeuvre;
    @ManyToMany(mappedBy = "services" , fetch = FetchType.LAZY)
   private Collection<Packages> packages ;

}