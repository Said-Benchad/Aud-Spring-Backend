package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.Moteur;
import org.sid.secservice.sec.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoitureRepository extends JpaRepository<Voiture,Long> {
    Optional<Voiture> findById(Long id);

    Voiture findByModeleAndMoteur(String modele, Moteur moteur);
    List<Voiture>findAllByModeleAndFinition(String modele , String finition);

    List<Voiture> findAllByMoteur(Moteur moteur);
    List<Voiture> findAllByModeleContaining(String keyword);

}
