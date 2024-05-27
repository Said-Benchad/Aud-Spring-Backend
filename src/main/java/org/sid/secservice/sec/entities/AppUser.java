package org.sid.secservice.sec.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class AppUser {

    public AppUser(  String username , String password ,String email){
        this.username = username;
        this.password = password;
        this.email=email;
    }
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String firstName;
    private  String lastName;
    private String email;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> appRole = new ArrayList<>();

//    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
//    @Column(name = "CDEVIS", updatable = false, nullable = false)
//    private Collection<Devis> devis;


}
