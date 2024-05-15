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
    private Long codeMoteur ;
    private String cylindee ;
    private String puissance;
    private String typeMotorisation;



//    @OneToOne(mappedBy = "moteur")
//    private Voiture voiture;
    public Moteur( String cylindee , String puissance ,String typeMotorisation){
        this.cylindee =cylindee;
        this.puissance = puissance;
        this.typeMotorisation = typeMotorisation;
    }

}