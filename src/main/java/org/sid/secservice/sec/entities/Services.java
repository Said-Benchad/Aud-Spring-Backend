package org.sid.secservice.sec.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Services {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idService;
//    @Enumerated(EnumType.STRING)
//    @OneToMany(mappedBy = "services" , fetch = FetchType.LAZY)
//    private Collection<PrixServices> prixService ;
    @ManyToMany
    private List<MainOeuvre> mainOeuvre;
//    @ManyToMany(mappedBy = "services" , fetch = FetchType.LAZY)
//    private Collection<Packages> packages ;

}