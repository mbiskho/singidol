package apap.singidol.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GeneratorType;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity 
@AllArgsConstructor
@Setter
@Getter
@Table(name="idol")
public class IdolModel {
    @Id
    @Column(name="idol_id", nullable = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name="nama_idol")
    @NotNull @Size(max = 255)
    public String namaIdol;

    @Column(name="tanggal_debut")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime tanggalDebut;

    @Column(name="jumlah_anggota")
    @NotNull
    public Integer jumlahAnggota;

    @Column(name="asal_negara") @Size(max = 255)
    public String asalNegara;   
    
    @OneToMany(mappedBy = "idol")
    List<PenampilanKonserModel> konserIdol;


    public IdolModel() {
        this.namaIdol = "";
        this.tanggalDebut = LocalDateTime.now();
        this.jumlahAnggota = 0;
        this.asalNegara = "";
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + id + "'" +
            ", namaIdol='" + namaIdol + "'" +
            ", tanggalDebut='" + tanggalDebut + "'" +
            ", jumlahAnggota='" + jumlahAnggota + "'" +
            ", asalNegara='" + asalNegara + "'" +
            ", konserIdol='" + konserIdol + "'" +
            "}";
    }


}
