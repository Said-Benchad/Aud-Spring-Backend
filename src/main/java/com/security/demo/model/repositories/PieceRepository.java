package com.security.demo.model.repositories;

import com.security.demo.model.entities.Piece;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PieceRepository extends JpaRepository<Piece, Integer> {
}
