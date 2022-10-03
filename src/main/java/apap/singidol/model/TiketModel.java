package apap.singidol.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ManyToAny;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tiket")
public class TiketModel {
    
    @Id @NotNull
    @Column(name="tiket_id", nullable = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    @Column(name="nomor_tiket", nullable = true)
    @Size(max = 255) @NotNull
    private String nomorTiket;

    @NotNull
    @Column(name="tanggal_pembelian", nullable = true)
    private LocalDateTime  tanggalPembelian;


    @Column(name="nama_lengkap", nullable = true)
    @Size(max = 255) @NotNull
    private String namaLengkap;

    @Column(name="tanggal_lahir", nullable = true)
    @NotNull
    private LocalDate tanggalLahir;

    @Column(name="email", nullable = true) @NotNull
    @Size(max = 255)
    private String email;

    @ManyToOne
    @JoinColumn(name="konser_id")
    KonserModel konser;

    @ManyToOne
    @JoinColumn(name="tipe_id")
    TipeModel tipe;


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nomorTiket='" + getNomorTiket() + "'" +
            ", tanggalPembelian='" + getTanggalPembelian() + "'" +
            ", namaLengkap='" + getNamaLengkap() + "'" +
            ", tanggalLahir='" + getTanggalLahir() + "'" +
            ", email='" + getEmail() + "'" +
            ", konser='" + getKonser() + "'" +
            ", tipe='" + getTipe() + "'" +
            "}";
    }

    public String parsingTanggalPembelian(){
        LocalDateTime tanggalPembelian = this.tanggalPembelian;
        String tanggalPembelianParse = tanggalPembelian.getDayOfMonth() + " " + tanggalPembelian.getMonth() + " " + tanggalPembelian.getYear();
        return tanggalPembelianParse;
    }

}