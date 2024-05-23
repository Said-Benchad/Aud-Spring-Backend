package org.sid.secservice.sec.controllers;

import org.sid.secservice.sec.dtos.IdUser;
import org.sid.secservice.sec.entities.AppUser;
import org.sid.secservice.sec.services.AccountService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    private final AccountService accountService;







                    //---------------------------------All the methods work perfectly------------------------------------------------------------------------
    public UserController(AccountService accountService) {
        this.accountService = accountService;
    }



    @GetMapping(path = "/get-users")
    @PostAuthorize("hasAuthority('USER')")
    public List<AppUser> appUsers() {
        return accountService.listeUser();
    }



    @PostMapping(path = "/saveUser")
    // @PostAuthorize("hasAuthority('ADMIN')")
    public AppUser saveUser( @RequestBody AppUser appUser){
        System.out.println(appUser.getUsername());
        return accountService.addNewUser(appUser);
    }

    @PostMapping(path = "/deleteUser")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void deleteUser( @RequestBody AppUser appUser){
        accountService.deleteUser(appUser);
    }



    @GetMapping(path = "/userss")
    @PostAuthorize("hasAuthority('USER')")
    public Optional<IdUser> appUser(long id){
        Optional<AppUser> usr = accountService.user(id);   /* Verfied api , it works perfectly */
        IdUser us = new IdUser();
        us.setUser(usr.get());
        us.setId(id);
        return Optional.of(us);
    }


}
