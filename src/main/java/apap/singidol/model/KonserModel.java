package apap.singidol.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity 
@AllArgsConstructor
@Setter
@Getter
@Table(name="konser")
public class KonserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="konser_id", nullable = true)
    private Long id;

    @Column(name="nama_konser")
    @NotNull @Size(max = 255)
    private String namaKonser;

    @Column(name="waktu")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime waktu;

    @Column(name="total_pendapatan")
    @NotNull
    private Long totalPendapatan;

    @Column(name="tempat") @Size(max = 255)
    private String tempat;

    @OneToMany(mappedBy = "konser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TicketModel> tiket;

    @OneToMany(mappedBy = "konser")
    List<PenampilanKonserModel> konserIdol;


    public KonserModel() {
        this.namaKonser = "";
        this.waktu = LocalDateTime.now();
        this.totalPendapatan =  Long.valueOf(0);
        this.tempat = "";
    }

    public String parsingWaktu(){
        LocalDateTime waktuKonser = this.waktu;
        String waktuParse = waktuKonser.getDayOfMonth() + " " + waktuKonser.getMonth() + " " + waktuKonser.getYear()+", " + waktuKonser.getHour()+"."+waktuKonser.getMinute();
        return waktuParse;
    }

        
}
