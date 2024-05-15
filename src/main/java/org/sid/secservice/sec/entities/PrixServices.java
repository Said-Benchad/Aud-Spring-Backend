package org.sid.secservice.sec.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrixServices {
    @Id @GeneratedValue
    private Long idPrixService ;
    @ManyToOne
    private Voiture voiture;
    @ManyToOne
    private Services services;
    private double prixServVoitr;

}