package com.security.demo.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Getter @Setter
@Data @AllArgsConstructor @NoArgsConstructor
public class Services {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idService;
    private String nom ;
    @Enumerated(EnumType.STRING)
    private TypeService typeService ;
    @OneToMany(mappedBy = "service" , fetch = FetchType.LAZY)
    private Collection<PrixService>prixService ;
    @OneToOne(mappedBy = "service")
    private MainOeuvre mainOeuvre;
    @ManyToMany(mappedBy = "services" , fetch = FetchType.LAZY)
    private Collection<Packages> packages ;

}
