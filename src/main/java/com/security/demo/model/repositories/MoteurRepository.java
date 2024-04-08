package com.security.demo.model.repositories;

import com.security.demo.model.entities.Moteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoteurRepository extends JpaRepository<Moteur , String> {
}
