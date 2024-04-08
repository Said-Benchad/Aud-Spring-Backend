package com.security.demo.model.entities;

import jakarta.persistence.*;
import lombok.*;


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
    private Services service;
    private double prixServVoitr;

}
