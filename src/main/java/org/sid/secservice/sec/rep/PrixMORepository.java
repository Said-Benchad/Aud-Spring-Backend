package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.MainOeuvre;
import org.sid.secservice.sec.entities.PrixServices;
import org.sid.secservice.sec.entities.Prixmainouevre;
import org.sid.secservice.sec.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PrixMORepository extends JpaRepository<Prixmainouevre, Long> {
    List<Prixmainouevre> findAllByVoiture(Voiture voiture);
    Prixmainouevre findFirstByVoitureAndMainOeuvre(Voiture voiture , MainOeuvre mainOeuvre);

}
