package apap.singidol.dto;

import java.time.LocalDateTime;
import java.util.List;

import apap.singidol.model.IdolModel;

public class PenampilanKonserDto {
    public Long idKonser;
    public Long idPenampilan;
    public Long idIdol;
    public LocalDateTime jamMulaiTampil;
    public String namaIdol;
    public LocalDateTime tanggalDebut;
    public Integer jumlahAnggota;



    public PenampilanKonserDto(Long idKonser, Long idPenampilan, Long idIdol, LocalDateTime jamMulaiTampil, String namaIdol, LocalDateTime tanggalDebut, Integer jumlahAnggota) {
        this.idKonser = idKonser;
        this.idPenampilan = idPenampilan;
        this.idIdol = idIdol;
        this.jamMulaiTampil = jamMulaiTampil;
        this.namaIdol = namaIdol;
        this.tanggalDebut = tanggalDebut;
        this.jumlahAnggota = jumlahAnggota;
    }

    public String parseMulaiTampil(){
        String result = this.jamMulaiTampil.getHour() + " : " + jamMulaiTampil.getMinute();

        return result;
    }

    public Long getIdKonser() {
        return this.idKonser;
    }

    public void setIdKonser(Long idKonser) {
        this.idKonser = idKonser;
    }

    public Long getIdPenampilan() {
        return this.idPenampilan;
    }

    public void setIdPenampilan(Long idPenampilan) {
        this.idPenampilan = idPenampilan;
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

    public String getNamaIdol() {
        return this.namaIdol;
    }

    public void setNamaIdol(String namaIdol) {
        this.namaIdol = namaIdol;
    }

    public LocalDateTime getTanggalDebut() {
        return this.tanggalDebut;
    }

    public void setTanggalDebut(LocalDateTime tanggalDebut) {
        this.tanggalDebut = tanggalDebut;
    }

    public Integer getJumlahAnggota() {
        return this.jumlahAnggota;
    }

    public void setJumlahAnggota(Integer jumlahAnggota) {
        this.jumlahAnggota = jumlahAnggota;
    }

    @Override
    public String toString() {
        return "{" +
            " idKonser='" + idKonser + "'" +
            ", idPenampilan='" + idPenampilan + "'" +
            ", idIdol='" + idIdol + "'" +
            ", jamMulaiTampil='" + jamMulaiTampil + "'" +
            ", namaIdol='" + namaIdol + "'" +
            ", tanggalDebut='" + tanggalDebut + "'" +
            ", jumlahAnggota='" + jumlahAnggota + "'" +
            "}";
    }


}
