package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.PrixServices;
import org.sid.secservice.sec.entities.Services;
import org.sid.secservice.sec.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrixServicesRepository extends JpaRepository<PrixServices,Long> {
    List<PrixServices> findAllByVoit(Voiture voiture);
    List<PrixServices> findAllBySer(Services service);
    PrixServices findByVoitAndSer(Voiture voiture , Services services);
}
