
package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {
    private static final int OLETUSKAPASITEETTI = 5;
    private static final int OLETUSKASVATUS = 5;

    private int kasvatuskoko;
    private int alkioidenLukumaara;
    private int[] luvut;

    public IntJoukko() {
        this(OLETUSKAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }


    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IllegalArgumentException("Kapasiteetti ei voi olla negatiivinen!");
        }
        if (kasvatuskoko < 0) {
            throw new IllegalArgumentException("Kasvatuskoko ei voi olla negatiivinen!");
        }
        this.luvut = new int[kapasiteetti];
        this.alkioidenLukumaara = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }

        luvut[alkioidenLukumaara] = luku;
        ++alkioidenLukumaara;

        if (alkioidenLukumaara >= luvut.length) {
            kasvata();
        }

        return true;
    }

    public boolean kuuluu(int luku) {
        return Arrays.stream(luvut).anyMatch(joukonLuku -> joukonLuku == luku);
    }

    public boolean poista(int luku) {
        int kohta = luvunIndeksi(luku);
        if (kohta != -1) {
            --alkioidenLukumaara;
            System.arraycopy(luvut, kohta + 1, luvut, kohta, alkioidenLukumaara - kohta);
            return true;
        }

        return false;
    }

    private int luvunIndeksi(int luku) {
        int indeksi = -1;
        for (int i = 0; i < alkioidenLukumaara; ++i) {
            if (luku == luvut[i]) {
                luvut[indeksi = i] = 0;
                break;
            }
        }
        return indeksi;
    }

    public int mahtavuus() {
        return alkioidenLukumaara;
    }


    @Override
    public String toString() {
        if (alkioidenLukumaara == 0) {
            return "{}";
        } else if (alkioidenLukumaara == 1) {
            return "{" + luvut[0] + "}";
        } else {
            StringBuilder tuotos = new StringBuilder("{");
            for (int i = 0; i < alkioidenLukumaara - 1; i++) {
                tuotos.append(luvut[i]);
                tuotos.append(", ");
            }
            tuotos.append(luvut[alkioidenLukumaara - 1]);
            tuotos.append("}");
            return tuotos.toString();
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLukumaara];
        System.arraycopy(luvut, 0, taulu, 0, taulu.length);
        return taulu;
    }


    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        yhdiste.lisaaKaikki(a);
        yhdiste.lisaaKaikki(b);
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        Arrays.stream(a.luvut)
              .filter(b::kuuluu)
              .forEach(leikkaus::lisaa);

        return leikkaus;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        Arrays.stream(a.luvut)
              .filter(joukonALuku -> !b.kuuluu(joukonALuku))
              .forEach(erotus::lisaa);

        return erotus;
    }

    private void lisaaKaikki(IntJoukko joukko) {
        for (int value : joukko.luvut) {
            lisaa(value);
        }
    }

    private void kasvata() {
        luvut = Arrays.copyOf(luvut, alkioidenLukumaara + kasvatuskoko);
    }
}
