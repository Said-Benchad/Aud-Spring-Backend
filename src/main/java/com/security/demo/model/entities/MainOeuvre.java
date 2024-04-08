package com.security.demo.model.entities;

import com.security.demo.model.entities.Services;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Data @NoArgsConstructor @AllArgsConstructor
public class MainOeuvre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMO_Service ;
    private String nom;
    private Double cout ;
    @OneToOne
    private Services service ;

}
