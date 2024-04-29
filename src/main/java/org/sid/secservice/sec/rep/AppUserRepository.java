package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser , Long> {
    AppUser findByUsername(String username);

}


