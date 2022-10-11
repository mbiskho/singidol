package apap.singidol.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

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
@Entity
@Table(name="tiket")
public class TiketModel {
    
    @Id @NotNull
    @Column(name="tiket_id", nullable = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    @Column(name="nomor_tiket", nullable = true, unique = true)
    @Size(max = 255) @NotNull
    private String nomorTiket;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name="tanggal_pembelian", nullable = true)
    private LocalDateTime  tanggalPembelian;


    @Column(name="nama_lengkap", nullable = true)
    @Size(max = 255) @NotNull
    private String namaLengkap;

    @Column(name="tanggal_lahir", nullable = true)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tanggalLahir;

    @Column(name="email", nullable = true) @NotNull
    @Size(max = 255)
    private String email;

    @ManyToOne
    @JoinColumn(name="konser_id")
    KonserModel konser;

    @ManyToOne
    @JoinColumn(name="tipe_id")
    TipeModel tipe;



    public TiketModel() {
        this.nomorTiket = "";
        this.tanggalLahir = LocalDateTime.now();
        this.tanggalPembelian = LocalDateTime.now();
        this.namaLengkap = "";
        this.email = "";
    }


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

    private Integer tanggalToNumber(LocalDateTime A){
        Integer ans = A.getDayOfMonth() * 10000 + A.getMonthValue();
        return ans; 
    }

    private String transformChar(String str) {
        str = str.toUpperCase();
        char first = str.charAt(0);  
        int subtraction = first - 'A';
        String ans = "";

        if(subtraction / 10 == 0){
            ans += "0";
        }

        ans += subtraction;
        return ans;
    }

    public void generateNomorTiket(){
        
        String firstSuffix = ((this.namaLengkap + "   ").substring(0, 3)).toUpperCase();

        Integer secondSuffix = tanggalToNumber(tanggalLahir) + tanggalToNumber(tanggalPembelian); 

        String thirdSuffix = transformChar(this.namaLengkap);

        String fourSuffix = this.getTipe().getNama().toUpperCase();

        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'A');

        String fiveSuffix = Character.toString(c);

        String ans = firstSuffix + secondSuffix + thirdSuffix + fourSuffix + fiveSuffix;

        this.nomorTiket = ans;
    }
    

    public String parsingTanggalPembelian(){
        LocalDateTime tanggalPembelian = this.tanggalPembelian;
        String tanggalPembelianParse = tanggalPembelian.getDayOfMonth() + " " + tanggalPembelian.getMonth() + " " + tanggalPembelian.getYear();
        return tanggalPembelianParse;
    }

}
