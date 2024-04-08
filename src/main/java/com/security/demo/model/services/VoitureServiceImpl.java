package com.security.demo.model.services;

import com.security.demo.model.entities.Employe;
import com.security.demo.model.repositories.ClientRepository;
import com.security.demo.model.repositories.DevisRepository;
import com.security.demo.model.repositories.EmployeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class VoitureServiceImpl {
    ClientRepository clientRepository;
    DevisRepository devisRepository;
    EmployeRepository employeRepository;


}
