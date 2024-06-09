package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.AppUser;
import org.sid.secservice.sec.entities.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DevisRepository extends JpaRepository<Devis, Long> {
    List<Devis> findAllByAppUser (AppUser user);
    @Query("select count(*) from Devis d")
    int countdevis();
    @Query("select sum(d.montant)from Devis d ")
    double sumDevis();

}
