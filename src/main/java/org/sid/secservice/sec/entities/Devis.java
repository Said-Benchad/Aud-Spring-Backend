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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codeDevis", updatable = false, nullable = false)
    private Long code_devis ;
    private String titre_devis;
    private  String devisURL;
    @ManyToOne
    private AppUser client;
    @OneToOne
    private StatusDevis statusDevis;
    @ManyToOne
    private Voiture voiture;
    @ManyToMany( fetch = FetchType.LAZY)
    List<Employe> employes ;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModif ;

}
