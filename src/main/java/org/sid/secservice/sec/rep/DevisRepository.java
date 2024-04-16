package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.Devis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DevisRepository extends JpaRepository<Devis, UUID> {
}
