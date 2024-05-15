package org.sid.secservice.sec.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne @ToString.Exclude
    private Services service ;

    public MainOeuvre( String s, double i) {
        this.nom=s;
        this.cout=i;
    }public MainOeuvre( String s,Services services ,double i) {
        this.nom=s;
        this.service =services;
        this.cout=i;
    }
}