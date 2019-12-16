package ohtu.kivipaperisakset;

import java.util.Scanner;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KPSPeli {
    private final Pelaaja ensimmainenPelaaja;
    private final Tekoaly tekoaly;

    public static KPSPeli uusiPeli() {
        return new KPSParempiTekoaly();
    }

    private KPSParempiTekoaly() {
        this.ensimmainenPelaaja = new IhmisPelaaja(true, new Scanner(System.in));
        this.tekoaly = new TekoalyParannettu(20);
    }

    @Override
    protected String toisenSiirto() {
        var siirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + siirto);

        tekoaly.asetaSiirto(siirto);
        return siirto;
    }
}
