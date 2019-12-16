package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KPSPeli {
    private final Pelaaja ensimmainenPelaaja;

    protected KPSPeli() {
        ensimmainenPelaaja = new IhmisPelaaja(true, new Scanner(System.in));
    }

    public void pelaa() {
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");

        Tuomari tuomari = new Tuomari();

        String ekanSiirto = ensimmaisenSiirto();
        String tokanSiirto = toisenSiirto();

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            ekanSiirto = ensimmaisenSiirto();
            tokanSiirto = toisenSiirto();
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }

    protected String ensimmaisenSiirto() {
        return ensimmainenPelaaja.annaSiirto();
    }

    protected abstract String toisenSiirto();

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}
