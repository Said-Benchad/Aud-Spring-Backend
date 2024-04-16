package org.sid.secservice.sec.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainOeuvre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMO_Service ;
    private String nom;
    private Double cout ;
    @OneToOne
    private Services service ;

}