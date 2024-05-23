package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.Revision;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RevisionRepository extends JpaRepository<Revision,Long> {
}
