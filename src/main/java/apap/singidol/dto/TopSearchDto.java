package apap.singidol.dto;

public class TopSearchDto {
    public String namaTipe;

    public TopSearchDto() {
    }

    public TopSearchDto(String namaTipe) {
        this.namaTipe = namaTipe;
    }

    public String getNamaTipe() {
        return this.namaTipe;
    }

    public void setNamaTipe(String namaTipe) {
        this.namaTipe = namaTipe;
    }

    public TopSearchDto namaTipe(String namaTipe) {
        setNamaTipe(namaTipe);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " namaTipe='" + getNamaTipe() + "'" +
            "}";
    }

   


}
