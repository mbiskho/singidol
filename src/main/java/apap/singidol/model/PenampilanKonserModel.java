package apap.singidol.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class PenampilanKonserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    public Long idKonser;
    public Long idIdol;

    @Column(name="jamMulaiTampil")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime jamMulaiTampil;
    
        
    @ManyToOne
    @JoinColumn(name="konser_id")
    KonserModel konser;


    @ManyToOne
    @JoinColumn(name="idol_id")
    IdolModel idol;



    public PenampilanKonserModel() {
        this.jamMulaiTampil = LocalDateTime.now();
    }



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdKonser() {
        return this.idKonser;
    }

    public void setIdKonser(Long idKonser) {
        this.idKonser = idKonser;
    }

    public Long getIdIdol() {
        return this.idIdol;
    }

    public void setIdIdol(Long idIdol) {
        this.idIdol = idIdol;
    }

    public LocalDateTime getJamMulaiTampil() {
        return this.jamMulaiTampil;
    }

    public void setJamMulaiTampil(LocalDateTime jamMulaiTampil) {
        this.jamMulaiTampil = jamMulaiTampil;
    }

    public KonserModel getKonser() {
        return this.konser;
    }

    public void setKonser(KonserModel konser) {
        this.konser = konser;
    }

    public IdolModel getIdol() {
        return this.idol;
    }

    public void setIdol(IdolModel idol) {
        this.idol = idol;
    }



    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", idKonser='" + getIdKonser() + "'" +
            ", idIdol='" + getIdIdol() + "'" +
            ", jamMulaiTampil='" + getJamMulaiTampil() + "'" +
            ", konser='" + getKonser() + "'" +
            ", idol='" + getIdol() + "'" +
            "}";
    }

}
