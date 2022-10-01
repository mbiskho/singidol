package apap.singidol.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PenampilanKonserModel {
    @Id
    Long id;
    
    @ManyToOne
    @JoinColumn(name="konser_id")
    KonserModel konser;


    @ManyToOne
    @JoinColumn(name="idol_id")
    IdolModel idol;

    @Column(name="jamMulaiTampil")
    LocalDate jamMulaiTampil;
}
