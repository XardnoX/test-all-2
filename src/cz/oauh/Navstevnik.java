import java.time.LocalDate;

public class Navstevnik {
    private String jmeno;
    private String prijmeni;
    private LocalDate datum;
    private int ktedit;

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public int getKtedit() {
        return ktedit;
    }

    public void setKtedit(int ktedit) {
        this.ktedit = ktedit;
    }

    public Navstevnik(String jmeno, String prijmeni, LocalDate datum, int ktedit) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.datum = datum;
        this.ktedit = ktedit;
    }
}
