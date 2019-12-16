package ohtu.kivipaperisakset;

public class KPSTekoaly extends KPSPeli {
    private final Tekoaly tekoaly;

    public static KPSPeli uusiPeli() {
        return new KPSTekoaly();
    }

    private KPSTekoaly() {
        this.tekoaly = new Tekoaly();
    }

    @Override
    protected String toisenSiirto() {
        var siirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + siirto);

        tekoaly.asetaSiirto(siirto);
        return siirto;
    }
}