package org.sid.secservice.sec.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Getter
@Setter
@Data @NoArgsConstructor @AllArgsConstructor
public class Packages {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "codePack", updatable = false, nullable = false)
    private String codePack;
    private  String Type ;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Services> services = new ArrayList<>();
    @ManyToMany( fetch = FetchType.EAGER)
    private  Collection<MainOeuvre> mainOeuvres= new ArrayList<>();
    @ManyToMany
    private Collection<Voiture> voiture;
    private Integer cout;




}
