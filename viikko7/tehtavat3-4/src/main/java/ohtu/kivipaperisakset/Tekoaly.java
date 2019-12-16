package ohtu.kivipaperisakset;

public class Tekoaly implements Pelaaja {
    private int siirto;

    public Tekoaly() {
        this.siirto = 0;
    }

    @Override
    public String annaSiirto() {
        this.siirto++;
        this.siirto = this.siirto % 3;

        if (this.siirto == 0) {
            return "k";
        } else if (this.siirto == 1) {
            return "p";
        } else {
            return "s";
        }
    }

    void asetaSiirto(String ekanSiirto) {
        // ei tehdä mitään 
    }
}
