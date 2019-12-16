package ohtu.kivipaperisakset;

public abstract class KPSPeli {
    public void pelaa() {
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
        aloita();
    }

    protected abstract void aloita();
}
