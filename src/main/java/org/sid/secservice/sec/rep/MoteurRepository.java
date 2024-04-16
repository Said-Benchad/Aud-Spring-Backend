package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.Moteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoteurRepository extends JpaRepository<Moteur,String> {
}
