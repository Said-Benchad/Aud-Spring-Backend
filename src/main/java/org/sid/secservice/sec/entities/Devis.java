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
    private UUID code_devis ;
    @ManyToOne
    private AppUser client;
    @OneToOne
    private StatusDevis statusDevis;
    @ManyToOne
    private Voiture voiture;
    @ManyToMany( fetch = FetchType.LAZY)
    List<Employe> employes ;
    //private TypeService typeService;
    private String description;
    private Date dateCreation;
    private Date dateModif ;

}
