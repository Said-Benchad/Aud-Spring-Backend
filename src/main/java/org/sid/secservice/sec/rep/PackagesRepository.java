package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.Packages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackagesRepository extends JpaRepository<Packages , String> {
}
