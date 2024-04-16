package org.sid.secservice.sec.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Devis {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "codeDevis", updatable = false, nullable = false)
    private String code_devis ;
    @ManyToOne
    private AppUser client;
    @OneToOne
    private StatusDevis statusDevis;
    @ManyToOne
    private Voiture voiture;
    @ManyToMany( fetch = FetchType.LAZY)
    List<Employe> employes ;
    @Enumerated(EnumType.STRING)
    private TypeService typeService;
    private String description;
    private Date dateCreation;
    private Date dateModif ;

}