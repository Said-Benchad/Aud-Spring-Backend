package org.sid.secservice.sec.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor @AllArgsConstructor
public class PreDevisDTO {
    private PackDTO packDTO;
    private List<RevisionDTO> revisionDTO;
}
