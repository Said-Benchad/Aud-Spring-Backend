package org.sid.secservice.sec.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    
    private String jwt;
    private Long user_id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String jwt,long user_id,String username, String email, List<String> roles){
        this.user_id = user_id;
        this.jwt = jwt;
        this.email = email;
        this.username = username;
        this.roles = roles;
    }


}
