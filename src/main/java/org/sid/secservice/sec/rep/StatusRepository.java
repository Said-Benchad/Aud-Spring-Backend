package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.StatusDevis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusDevis,Long> {
}
