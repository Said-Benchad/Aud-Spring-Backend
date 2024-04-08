package com.security.demo.model.repositories;

import com.security.demo.model.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface voitureRepository extends JpaRepository<Voiture,Long> {
}
