package org.sid.secservice.sec.controllers;

import org.sid.secservice.sec.dtos.IdUser;
import org.sid.secservice.sec.dtos.UserDto;
import org.sid.secservice.sec.entities.AppRole;
import org.sid.secservice.sec.entities.AppUser;
import org.sid.secservice.sec.services.AccountService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    private final AccountService accountService;
    private PasswordEncoder passwordEncoder;






                    //---------------------------------All the methods work perfectly------------------------------------------------------------------------
    public UserController(AccountService accountService ,PasswordEncoder passwordEncoder ) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }



    @GetMapping(path = "/get-users")
    @PostAuthorize("hasAuthority('USER')")
    public List<AppUser> appUsers() {
        return accountService.listeUser();
    }
    @GetMapping(path = "/get-userss")
    public List<UserDto> appUserdto() {
        List<AppUser> appUsers= accountService.listeUser();
        List<UserDto> appuserdto = new ArrayList<>();
        for (AppUser user : appUsers){
            List<String> roles =new ArrayList<>();
            for(AppRole r : user.getAppRole()){
                roles.add(r.getRoleName());

            }
            appuserdto.add(new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername() ,roles, passwordEncoder.encode(user.getPassword() )));
        }
        return appuserdto;
     }

    @GetMapping(path = "/get-users/search")
    public List<UserDto> appUserdSerched(@RequestParam(name = "keyword",defaultValue = "") String keyword) {
        List<AppUser> appUsers= accountService.searchUser(keyword);
        List<UserDto> appuserdto = new ArrayList<>();
        for (AppUser user : appUsers){
            List<String> roles =new ArrayList<>();
            for(AppRole r : user.getAppRole()){
                roles.add(r.getRoleName());

            }
            appuserdto.add(new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername() ,roles, passwordEncoder.encode(user.getPassword() )));
        }
        return appuserdto;
    }



    @PostMapping(path = "/saveUser")
    // @PostAuthorize("hasAuthority('ADMIN')")
    public AppUser saveUser( @RequestBody UserDto userDto){
        List<AppRole> role = new ArrayList<>();
        System.out.println(userDto.getRole());
        for(String r : userDto.getRole()){
            AppRole role1 = accountService.getRoleByname(r);
            role.add(role1);
        }



        return accountService.addNewUser(new AppUser(null, userDto.getFirstname(), userDto.getLastname(),null, userDto.getEmail(), userDto.getPassword(),role ));
    }

    @DeleteMapping(path = "/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id ){
        accountService.deleteUser(id);
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
