package org.sid.secservice.sec.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Data @NoArgsConstructor @AllArgsConstructor
public class MainOeuvre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMO_Service ;
    private String nom;
    private Double cout ;
    @OneToOne
    private Services service ;
    @ManyToMany(mappedBy =  "mainOeuvres",fetch = FetchType.LAZY)
    private Collection<Packages> packages;

}
