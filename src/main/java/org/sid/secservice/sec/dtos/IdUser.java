package org.sid.secservice.sec.dtos;

import lombok.Data;
import org.sid.secservice.sec.entities.AppUser;

@Data
public class IdUser {
    private long id;
    private AppUser user;
}
