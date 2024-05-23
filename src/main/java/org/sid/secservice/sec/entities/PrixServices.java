package org.sid.secservice.sec.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrixServices {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrixService ;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Voiture voiture;
    @ManyToOne
    private Services services;
    private double prixServVoitr;
//    public PrixServices(Long idPrixService ,Voiture voiture, Services services, double prixServVoitr){
//        this.idPrixService = idPrixService;
//        this.voiture = voiture;
//        this.services = services;
//        this.prixServVoitr = prixServVoitr;
//    }

}