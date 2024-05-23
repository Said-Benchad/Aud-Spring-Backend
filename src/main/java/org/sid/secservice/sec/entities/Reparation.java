package org.sid.secservice.sec.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Data  @AllArgsConstructor  @NoArgsConstructor
public class Reparation extends Services {
    private String nom;
    public Reparation(String nom, List<MainOeuvre> mainOeuvres){
        super(null , mainOeuvres);
        this.nom = nom;
    }
}
