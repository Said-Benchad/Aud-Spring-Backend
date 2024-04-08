package com.security.demo.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Data @AllArgsConstructor @NoArgsConstructor
public class Moteur {
    @Id @GeneratedValue(strategy= GenerationType.UUID)
    private String codeMoteur ;
    private String cylindee ;
    private String puissance;
    private String typeMotorisation;
    private String boitaVitesse;
    @OneToOne
    private Voiture voiture;

}
