package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.AppUser;
import org.sid.secservice.sec.entities.Devis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DevisRepository extends JpaRepository<Devis, Long> {
    List<Devis> findAllByClient (AppUser user);
}
