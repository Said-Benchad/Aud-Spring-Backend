package org.sid.secservice.sec.services;

import org.sid.secservice.sec.entities.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private AccountService accountService ;

    public UserDetailServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }
    //lorsque l'utilisateur rempli le formulaire on fait appel a cette methode pour recuperer un user
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //tout ca pour recuperer un user a travers son nom

        AppUser appUser = accountService.LoadUserByUsername(username);
        //ici on doit convertir de la collection appRole a la collection grandAuthorities
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        appUser.getAppRole().forEach(e ->{
            grantedAuthorities.add(new SimpleGrantedAuthority(e.getRoleName()));
        });
        return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);
    }
}
