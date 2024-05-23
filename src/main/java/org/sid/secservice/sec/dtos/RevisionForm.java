package org.sid.secservice.sec.dtos;

import lombok.Data;
import org.sid.secservice.sec.entities.Services;

import java.util.List;

@Data
public class RevisionForm {
    private String username;
    private String modele;
    private String puissance;
    private List<Services> services;
}
