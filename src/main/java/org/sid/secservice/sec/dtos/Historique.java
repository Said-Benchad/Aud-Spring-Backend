package org.sid.secservice.sec.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.sid.secservice.sec.entities.StatusDevis;

import java.util.Date;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Historique {
    private String title;
    private Date CretaedAt;
    private StatusDevis statusDevis;
}
