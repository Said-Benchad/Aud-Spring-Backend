package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.Piece;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PieceRepository extends JpaRepository<Piece,Long> {
}
