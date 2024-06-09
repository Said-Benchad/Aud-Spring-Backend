package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.MainOeuvre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MainOeuvreRepository extends JpaRepository<MainOeuvre,Long> {
    @Query("select count(*) from MainOeuvre m")
    int countMO();

    Optional<MainOeuvre> findAllByNomContains(String key);
}
