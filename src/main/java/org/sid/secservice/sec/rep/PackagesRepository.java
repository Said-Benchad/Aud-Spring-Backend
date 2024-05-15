package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.Packages;
import org.sid.secservice.sec.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PackagesRepository extends JpaRepository<Packages , Long> {
    List<Packages> findByVoiture (Voiture voiture);
}
