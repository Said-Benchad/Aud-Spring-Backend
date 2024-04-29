package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.Moteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MoteurRepository extends JpaRepository<Moteur, Long> {
    Moteur findByTypeMotorisation(String motorisation);
}
