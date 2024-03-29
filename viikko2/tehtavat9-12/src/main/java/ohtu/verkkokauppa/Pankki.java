package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Pankki implements IPankki {
    @Autowired
    private IKirjanpito kirjanpito;

    @Override
    public boolean tilisiirto(
            String nimi,
            int viitenumero,
            String tililta,
            String tilille,
            int summa
    ) {
        kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}
