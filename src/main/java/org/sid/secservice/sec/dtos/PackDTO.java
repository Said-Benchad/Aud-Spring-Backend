package org.sid.secservice.sec.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class PackDTO {
    private Long id;
    private String type;
    private List<RevisionDTO> service = new ArrayList<>();
}
