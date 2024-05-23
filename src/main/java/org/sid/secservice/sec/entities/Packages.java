package org.sid.secservice.sec.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    private Long codePack;
    private  String type ;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Revision> revision = new ArrayList<>();
    @ManyToOne
    private Voiture voiture;
    private Double cout ;
    public void resetCout(){
        double a = 0;
        setCout( a);
    }

}