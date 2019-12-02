package ohtu;

public abstract class Komento {
    protected final Sovelluslogiikka sovellus;
    protected int edellinenSyote;

    protected Komento(Sovelluslogiikka sovellus) {
        this.sovellus = sovellus;
    }

    public void suorita(int syote) {
        this.edellinenSyote = syote;
    }

    public void peru() {
    }

    public static class Summa extends Komento {
        public Summa(Sovelluslogiikka sovellus) {
            super(sovellus);
        }

        @Override
        public void suorita(int syote) {
            super.suorita(syote);
            this.sovellus.plus(syote);
        }
    }

    public static class Erotus extends Komento {
        public Erotus(Sovelluslogiikka sovellus) {
            super(sovellus);
        }

        @Override
        public void suorita(int syote) {
            super.suorita(syote);
            this.sovellus.miinus(syote);
        }
    }

    public static class Nollaa extends Komento {
        public Nollaa(Sovelluslogiikka sovellus) {
            super(sovellus);
        }

        @Override
        public void suorita(int syote) {
            super.suorita(syote);
            this.sovellus.nollaa();
        }
    }
}
