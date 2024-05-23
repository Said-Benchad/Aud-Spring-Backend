package org.sid.secservice.sec.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MainOeuvreDTO {
    private Long id ;
    private String nom ;
    private double coutMO ;

}
