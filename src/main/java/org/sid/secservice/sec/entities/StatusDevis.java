package org.sid.secservice.sec.entities;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class StatusDevis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStat;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dureeEstimee;
    @OneToOne(mappedBy = "statusDevis")
    private Devis devis;

}
