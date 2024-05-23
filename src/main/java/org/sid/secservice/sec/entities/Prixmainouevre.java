package org.sid.secservice.sec.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Prixmainouevre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrixMO ;
    @ManyToOne
    @JsonIgnore
    private Voiture voiture;
    @ManyToOne
    @JsonIgnore
    private MainOeuvre mainOeuvre;
    private double prixServVoitrMo;
}
