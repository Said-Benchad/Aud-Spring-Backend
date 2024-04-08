package com.security.demo.model.repositories;

import com.security.demo.model.entities.Devis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevisRepository extends JpaRepository<Devis , String> {


}
