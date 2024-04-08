package com.security.demo.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class StatusDevis {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStat ;
    @Enumerated(EnumType.STRING)
    private Status status ;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dureeEstimee;
    @OneToOne
    private Devis devis;


}
