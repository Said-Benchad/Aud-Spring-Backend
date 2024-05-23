package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.PrixServices;
import org.sid.secservice.sec.entities.Services;
import org.sid.secservice.sec.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PrixServicesRepository extends JpaRepository<PrixServices,Long> {
    List<PrixServices> findAllByVoiture(Voiture voiture);
    List<PrixServices> findAllByServices(Services service);
    @Query("SELECT e FROM PrixServices e WHERE e.voiture = :voiture AND e.services = :services")
   PrixServices findVoitureAndServices(@Param("voiture") Voiture voiture, @Param("services") Services services);

}
