package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser , Long> {
    Optional<AppUser> findFirstByUsername(String username);
    @Query("select a from AppUser a where a.username like %:kw%")
    List<AppUser> userserchead(@Param(value = "kw") String key);
    @Query("select count(*) from AppUser a")
    int countusers();

}


