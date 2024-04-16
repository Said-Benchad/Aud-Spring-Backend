package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository  extends JpaRepository<Employe,Integer> {
}
