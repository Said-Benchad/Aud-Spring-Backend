package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoitureRepository extends JpaRepository<Voiture,Long> {
    Voiture findVoitureByNumChassis(String numChassis);
}
