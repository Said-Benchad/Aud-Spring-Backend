package org.sid.secservice.sec.services;

import org.sid.secservice.sec.entities.AppRole;
import org.sid.secservice.sec.entities.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole( AppRole appRole);
    void addRoletoUser(String username ,String roleName );
    AppUser LoadUserByUsername( String username);
    List<AppUser> listeUser();

}
