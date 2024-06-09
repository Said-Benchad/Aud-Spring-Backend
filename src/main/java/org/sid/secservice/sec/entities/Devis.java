package org.sid.secservice.sec.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Devis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codeDevis", updatable = false, nullable = false)
    private Long code_devis ;
    private String titre_devis;
    @ManyToOne
    private AppUser appUser;
   @ManyToOne
    private Voiture voiture;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
   @ManyToMany
    private List<Services> services;
   private double montant;


}
