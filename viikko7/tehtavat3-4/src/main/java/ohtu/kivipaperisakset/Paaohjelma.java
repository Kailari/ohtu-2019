package ohtu.kivipaperisakset;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;

public class Paaohjelma {
    private static final Map<String, Kaynnistyskomento> kaynnistyskomennot = Map.ofEntries(
            Map.entry("a", new Kaynnistyskomento(KPSPelaajaVsPelaaja::uusiPeli, "ihmistä vastaan")),
            Map.entry("b", new Kaynnistyskomento(KPSTekoaly::uusiPeli, "tekoälyä vastaan")),
            Map.entry("c", new Kaynnistyskomento(KPSParempiTekoaly::uusiPeli, "parannettua tekoälyä vastaan"))
    );

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            tulostaOhjeet();

            var komento = scanner.nextLine();
            Optional<KPSPeli> peli = kasitteleSeuraavaKomento(komento);
            if (peli.isPresent()) {
                peli.get().pelaa();
            } else {
                break;
            }
        }
    }

    private static Optional<KPSPeli> kasitteleSeuraavaKomento(String komento) {
        return kaynnistyskomennot.keySet()
                                 .stream()
                                 .filter(komento::endsWith)
                                 .findFirst()
                                 .map(kaynnistyskomennot::get)
                                 .map(Kaynnistyskomento::getPeliTehdas)
                                 .map(Supplier::get);
    }

    private static void tulostaOhjeet() {
        var komentojenOhjerivit = luoKomentojenOhjerivit();

        System.out.println("\nValitse pelataanko"
                                   + komentojenOhjerivit
                                   + "\nmuilla valinnoilla lopetataan");
    }

    private static String luoKomentojenOhjerivit() {
        return kaynnistyskomennot.entrySet()
                                 .stream()
                                 .sorted(Map.Entry.comparingByKey())
                                 .map(entry -> String.format("\n(%s) %s",
                                                             entry.getKey(),
                                                             entry.getValue().getKuvaus()))
                                 .reduce(String::concat)
                                 .orElseThrow();
    }

    private static class Kaynnistyskomento {
        private final Supplier<KPSPeli> peliTehdas;
        private final String kuvaus;

        public Supplier<KPSPeli> getPeliTehdas() {
            return peliTehdas;
        }

        public String getKuvaus() {
            return kuvaus;
        }

        public Kaynnistyskomento(Supplier<KPSPeli> peliTehdas, String kuvaus) {
            this.peliTehdas = peliTehdas;
            this.kuvaus = kuvaus;
        }
    }
}
