package com.security.demo.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter @Setter
@Data @NoArgsConstructor @AllArgsConstructor
public class Packages {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String codePack;
    private  String Type ;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Services> services = new  ArrayList <>();
    @ManyToMany( fetch = FetchType.EAGER)
    private  Collection<MainOeuvre> mainOeuvres= new ArrayList<>();
    @ManyToMany
    private Collection<Voiture> voiture;
    private Integer cout;




}
