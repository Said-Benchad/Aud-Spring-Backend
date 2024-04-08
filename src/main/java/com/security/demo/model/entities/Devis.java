package com.security.demo.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Devis {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String code_devis ;
    @ManyToOne
    private Client client;
    @OneToOne (mappedBy = "devis")
    private StatusDevis statusDevis;
    @ManyToOne
    private Voiture voiture;
    @ManyToMany(mappedBy = "devis" ,fetch = FetchType.LAZY)
    List<Employe> employes ;
    private TypeService typeService;
    private String description;
    private Date dateCreation;
    private Date dateModif ;

}
