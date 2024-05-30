package org.sid.secservice.sec.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.secservice.sec.entities.AppRole;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
   
        private Long id;
        private String firstname;
        private String lastname;
        private String username;
        private List<String> role;
        private String password;
  
}
