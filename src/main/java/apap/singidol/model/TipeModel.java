package apap.singidol.model;


import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GeneratorType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tipe")
public class TipeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="tipe_id", nullable = false) @NotNull
    private Long id;

    @Column(name="harga") @NotNull
    private Long harga;

    @Column(name="nama") @NotNull
    private String nama;

    @Column(name="deskripsi_tipe") @NotNull
    private String deskripsiTipe;

    @OneToMany(mappedBy = "tipe")
    List<TiketModel> tiket;

    public String formatCurrency(String amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return "Rp " +  formatter.format(Double.parseDouble(amount));
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", harga='" + getHarga() + "'" +
            ", nama='" + getNama() + "'" +
            ", deskripsiTipe='" + getDeskripsiTipe() + "'" +
            "}";
    }
}

   