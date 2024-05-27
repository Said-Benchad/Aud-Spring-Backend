package org.sid.secservice.sec.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class FormDTO {
    private String firstname;
    private String lastname;
    private String motorisation;
    private String modele;
    private String kilometrage;
}
