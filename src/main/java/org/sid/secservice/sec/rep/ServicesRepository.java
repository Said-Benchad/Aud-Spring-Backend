package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository extends JpaRepository<Services,Long> {
}
