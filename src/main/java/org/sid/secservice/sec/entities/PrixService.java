package org.sid.secservice.sec.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Data @NoArgsConstructor @AllArgsConstructor
public class PrixService {
    @Id @GeneratedValue
    private int idPrixService ;
    @ManyToOne
    private Voiture voiture;
    @ManyToOne
    private Services services;
    private double prixServVoitr;

}
