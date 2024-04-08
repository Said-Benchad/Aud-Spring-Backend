package com.security.demo.model.repositories;

import com.security.demo.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository <Client,Integer> {

    Optional<Client> findClientByEmail(String email);


}
