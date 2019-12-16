package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KPSPeli {
    public static KPSPeli uusiPeli() {
        return new KPSPelaajaVsPelaaja();
    }

    private final Pelaaja ensimmainenPelaaja;
    private final Pelaaja toinenPelaaja;

    private KPSPelaajaVsPelaaja() {
        var scanner = new Scanner(System.in);
        this.ensimmainenPelaaja = new IhmisPelaaja(true, scanner);
        this.toinenPelaaja = new IhmisPelaaja(false, scanner);
    }

    @Override
    protected String ensimmaisenSiirto() {
        return ensimmainenPelaaja.annaSiirto();
    }

    @Override
    protected String toisenSiirto() {
        return toinenPelaaja.annaSiirto();
    }
}