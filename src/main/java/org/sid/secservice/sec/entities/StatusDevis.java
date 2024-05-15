package org.sid.secservice.sec.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusDevis {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStat ;
   @Enumerated(EnumType.STRING)
    private Status status ;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dureeEstimee;



}

