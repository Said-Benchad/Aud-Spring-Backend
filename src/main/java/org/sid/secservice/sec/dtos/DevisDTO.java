package org.sid.secservice.sec.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.secservice.sec.entities.Devis;
import org.sid.secservice.sec.entities.Services;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DevisDTO {
    private String firstName;
    private String LastName;
    private String modele;
    private String motorisation;
    private String typePack;
    private List<RevisionDTO> services;
    private double cout;
}
