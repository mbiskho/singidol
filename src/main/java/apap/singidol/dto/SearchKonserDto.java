package apap.singidol.dto;

public class SearchKonserDto {
    
    public Long pendapatan;
    public Long idIdol;



    public SearchKonserDto() {
        this.pendapatan = Long.parseLong("0");
        this.idIdol = Long.parseLong("0");
    }

    public SearchKonserDto(Long pendapatan, Long idIdol) {
        this.pendapatan = pendapatan;
        this.idIdol = idIdol;
    }

    @Override
    public String toString() {
        return "{" +
            " pendapatan='" + this.pendapatan  + "'" +
            ", idIdol='" + this.idIdol + "'" +
            "}";
    }



    public Long getPendapatan() {
        return this.pendapatan;
    }

    public void setPendapatan(Long pendapatan) {
        this.pendapatan = pendapatan;
    }

    public Long getIdIdol() {
        return this.idIdol;
    }

    public void setIdIdol(Long idIdol) {
        this.idIdol = idIdol;
    }


}
