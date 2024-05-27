package org.sid.secservice.sec.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoitureDTO {
    private Long Id;
    private String modele;
    private String finition;
    private String moteur;

}
