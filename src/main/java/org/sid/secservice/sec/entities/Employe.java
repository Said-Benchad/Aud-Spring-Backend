package org.sid.secservice.sec.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Setter
@Getter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employe  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_emp;
    private String nom;
    private Integer tel;
    private String adress;
    private String mail;
    private String password;
    @ManyToMany(mappedBy = "employes", fetch = FetchType.LAZY)
    private Collection<Devis> devis;
}
