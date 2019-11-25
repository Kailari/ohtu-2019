package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class KauppaTest {
    private Kauppa kauppa;
    private PankkiInterface pankki;
    ViitegeneraattoriInterface viitegeneraattori;

    @Before
    public void beforeEach() {
        pankki = mock(PankkiInterface.class);
        viitegeneraattori = mock(ViitegeneraattoriInterface.class);
        when(viitegeneraattori.uusi()).thenReturn(1337);

        VarastoInterface varasto = mock(VarastoInterface.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(20);
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(1, "kissa", 50));
        when(varasto.haeTuote(3)).thenReturn(new Tuote(1, "koira", 100));

        kauppa = new Kauppa(varasto, pankki, viitegeneraattori);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostostaSuorittaessaTilisiirtoTehdaanOikeillaParametreilla() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");
        verify(pankki, times(1))
                .tilisiirto(eq("pekka"),
                            eq(1337),
                            eq("12345"),
                            anyString(),
                            eq(5));
    }

    @Test
    public void kahdenTuotteenOstaminenTekeeTilisiirronOikeillaParametreilla() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pertti", "12345");
        verify(pankki, times(1))
                .tilisiirto(eq("pertti"),
                            eq(1337),
                            eq("12345"),
                            anyString(),
                            eq(55));
    }

    @Test
    public void tuotteenJotaOnVarastossaJaLoppuneenTuotteenOstaminenSuorittaaOikeanTilisiirron() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.lisaaKoriin(3);
        kauppa.tilimaksu("paavo", "12345");
        verify(pankki, times(1))
                .tilisiirto(eq("paavo"),
                            eq(1337),
                            eq("12345"),
                            anyString(),
                            eq(50));
    }

    @Test
    public void asioinninAloittaminenUudelleenOstonJalkeenNollaaOstoskorin() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pertti", "12345");

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pertti", "12345");
        verify(pankki, times(1))
                .tilisiirto(anyString(),
                            anyInt(),
                            anyString(),
                            anyString(),
                            eq(50));
    }

    @Test
    public void jokaiselleMaksutapahtumallePyydetaanUusiViite() {
        for (int i = 0; i < 3; ++i) {
            kauppa.aloitaAsiointi();
            kauppa.lisaaKoriin(1);
            kauppa.tilimaksu("pertti", "12345");
        }

        verify(viitegeneraattori, times(3))
                .uusi();
    }
}