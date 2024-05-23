package org.sid.secservice.sec.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    /*@OneToMany(mappedBy = "mainOeuvre")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Prixmainouevre> prixMainOeuvres;*/
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @ManyToOne
//    @ToString.Exclude
//    private Services service ;

}