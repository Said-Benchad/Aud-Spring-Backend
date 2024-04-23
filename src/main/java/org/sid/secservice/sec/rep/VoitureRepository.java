package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoitureRepository extends JpaRepository<Voiture,Long> {
    Optional<Voiture> findById(Long id);
}
