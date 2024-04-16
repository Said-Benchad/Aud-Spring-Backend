package org.sid.secservice.sec.entities;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Moteur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codeMoteur", updatable = false, nullable = false)
    private UUID codeMoteur ;
    private String cylindee ;
    private String puissance;
    private String typeMotorisation;
    private String boitaVitesse;
    @OneToOne(mappedBy = "moteur")
    private Voiture voiture;

}