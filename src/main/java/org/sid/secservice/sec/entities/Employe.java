package org.sid.secservice.sec.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Setter
@Getter
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Employe {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "idEmp", updatable = false, nullable = false)
    private int id_emp;
    private String nom;
    private Integer tel ;
    private String adress ;
    @OneToOne
    private  AppRole role ;
    private String mail ;
    private String password ;
    @ManyToMany(mappedBy =  "employes", fetch = FetchType.LAZY)
    private Collection<Devis> devis ;

}
