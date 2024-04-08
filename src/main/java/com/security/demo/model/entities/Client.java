package com.security.demo.model.entities;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_client")
public class Client implements UserDetails {
    @Getter
    @Id @GeneratedValue
    private Integer id ;
    @Getter
    private String nom;
    @Getter
    private  String email;
    private  String password;
    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;
    @Getter
    @OneToMany(mappedBy = "proprietaire" , fetch = FetchType.LAZY)
    private List<Voiture> voiture ;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
