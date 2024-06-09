package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.Revision;
import org.sid.secservice.sec.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ServicesRepository extends JpaRepository<Services,Long> {
    @Query("select count(*) from Services s")
    int countservice();

}
