package com.security.demo.model.repositories;

import com.security.demo.model.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe, Integer> {

}
