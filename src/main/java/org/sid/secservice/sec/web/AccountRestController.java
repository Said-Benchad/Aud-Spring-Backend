package org.sid.secservice.sec.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.sid.secservice.sec.JWTUtil;
import org.sid.secservice.sec.entities.*;
import org.sid.secservice.sec.services.AccountService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class AccountRestController {
    private final AccountService accountService;
    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }
    @GetMapping(path = "/users")
    @PostAuthorize("hasAuthority('USER')")
    public List<AppUser> appUsers(){
    return accountService.listeUser();
    }

    @PostMapping(path = "/h")
    public void n (long w ){
        System.out.println(w);

    }
    @PostMapping(path = "/saveUser")
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppUser saveUser( @RequestBody AppUser appUser){
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

    @PostMapping(path = "/upUsers")
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppUser updateuser(@RequestBody  AppUser user){

        return accountService.updateUser(user.getId() , user);
    }

    @PostMapping(path = "/roles")
    public AppRole saveRole(@RequestBody AppRole appRole){
        return accountService.addNewRole(appRole);
    }

    @PostMapping(path = "/addRoleToUser")
    public void addRoleToUser ( @RequestBody RoleUserForm roleUserForm){
         accountService.addRoletoUser(roleUserForm.getUsername(),roleUserForm.getRoleName());
    }

    @GetMapping(path = "/refreshToken")
    public void refreshToken(HttpServletRequest request , HttpServletResponse response) throws Exception{
        String authToken = request.getHeader(JWTUtil.AUTH_HEADER);
        if(authToken!=null && authToken.startsWith(JWTUtil.PREFIX)){
            try{
                String jwt = authToken.substring(JWTUtil.PREFIX.length());//length = 7
                Algorithm algo = Algorithm.HMAC256(JWTUtil.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algo).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String username = decodedJWT.getSubject();
                AppUser appUser = accountService.LoadUserByUsername(username);
                String jwtAccessToken = JWT.create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+JWTUtil.EXPIRE_ACCESS_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("role",appUser.getAppRole().stream().map(r->r.getRoleName()).collect(Collectors.toList()))
                        .sign(algo);
                Map<String,String >idToken = new HashMap<>();
                idToken.put("acces-token",jwtAccessToken);
                idToken.put("refresh-token",jwt);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(),idToken);
            }catch (Exception e){

                throw e;
            }
        }
        else {
            throw new RuntimeException("Refresh token required");
        }
    }

    @GetMapping(path = "/profile")
    public AppUser profile(Principal principal){
        return accountService.LoadUserByUsername(principal.getName());
    }

    @GetMapping(path = "/historique")
    public List<Historique> historique(Principal principal){
        AppUser user = accountService.LoadUserByUsername(principal.getName());
        List<Devis> devisList =accountService.listeDevisByclient(user);
        List<Historique> historiques = new ArrayList<>();
        devisList.forEach(e->{
            Historique htr = new Historique(e.getTitre_devis(),e.getDateCreation());
            historiques.add(htr);
        });
        return historiques;
    }

    @PostMapping(path = "/Revision")
    public List<Packages> Revision(@RequestBody RevisionForm revisionForm){
      Moteur moteur = accountService.getMoteurByName(revisionForm.getMotorisation());
      Voiture voiture = accountService.listVoiture(moteur , revisionForm.getModele());
      List<Packages> packages = accountService.getPackByTypeNVtr(voiture);
       return packages;
    }
}
@Data
class RoleUserForm{
    private String username;
    private String roleName;
}
@Data
class RevisionForm{
    private String username;
    private String modele;
    private String motorisation ;
    private String kilometrage;
    private List<Services> services;

}
@Data
class IdUser{
    private long id;
    private AppUser user;
}
