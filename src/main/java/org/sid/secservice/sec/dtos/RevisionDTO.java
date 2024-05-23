package org.sid.secservice.sec.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class RevisionDTO {
    private Long id ;
    private String nom;
    private List<MainOeuvreDTO> mainOeuvre = new ArrayList<>();
    private double couSer;
}
