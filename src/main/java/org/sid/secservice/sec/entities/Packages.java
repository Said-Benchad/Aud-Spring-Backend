package org.sid.secservice.sec.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Packages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codePack", updatable = false, nullable = false)
    private UUID codePack;
    private  String Type ;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Services> services = new ArrayList<>();
    @ManyToMany
    private Collection<Voiture> voiture;
    private Integer cout;




}