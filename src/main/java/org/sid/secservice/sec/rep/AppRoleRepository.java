package org.sid.secservice.sec.rep;

import org.sid.secservice.sec.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole , Long> {
    AppRole findFirstByRoleName(String roleName);

}
