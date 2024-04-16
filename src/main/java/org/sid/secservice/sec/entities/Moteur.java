package org.sid.secservice.sec.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Entity
@Getter
@Setter
@Data @AllArgsConstructor @NoArgsConstructor
public class Moteur {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "codeMoteur", updatable = false, nullable = false)
    private String codeMoteur ;
    private String cylindee ;
    private String puissance;
    private String typeMotorisation;
    private String boitaVitesse;
    @OneToOne
    private Voiture voiture;

}
