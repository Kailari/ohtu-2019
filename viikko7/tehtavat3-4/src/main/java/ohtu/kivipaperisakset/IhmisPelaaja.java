package ohtu.kivipaperisakset;

import java.util.Scanner;

public class IhmisPelaaja implements Pelaaja {
    private static final String ENSIMMAINEN_VUORO_ILMOITUS = "Ensimmäisen pelaajan siirto: ";
    private static final String TOINEN_VUORO_ILMOITUS = "Toisen pelaajan siirto: ";

    private final boolean ensimmainen;
    private final Scanner scanner;

    public IhmisPelaaja(boolean ensimmainen, Scanner scanner) {
        this.ensimmainen = ensimmainen;
        this.scanner = scanner;
    }

    @Override
    public String annaSiirto() {
        System.out.println(this.ensimmainen
                                   ? ENSIMMAINEN_VUORO_ILMOITUS
                                   : TOINEN_VUORO_ILMOITUS);
        return scanner.nextLine();
    }
}
