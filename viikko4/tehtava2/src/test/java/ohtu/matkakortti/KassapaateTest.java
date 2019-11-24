
package ohtu.matkakortti;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class KassapaateTest {
    private Kassapaate kassa;
    private Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = mock(Maksukortti.class);
    }

    @Test
    public void kortiltaVelotetaanHintaJosRahaaOn() {
        when(kortti.getSaldo()).thenReturn(10);
        kassa.ostaLounas(kortti);

        verify(kortti, times(1)).getSaldo();
        verify(kortti).osta(eq(Kassapaate.HINTA));
    }

    @Test
    public void kortiltaEiVelotetaJosRahaEiRiita() {
        when(kortti.getSaldo()).thenReturn(4);
        kassa.ostaLounas(kortti);

        verify(kortti, times(1)).getSaldo();
        verify(kortti, times(0)).osta(anyInt());
    }

    @Test
    public void kortinLataaminenLisaaOikeanSummanJosSummaOnPositiivinen() {
        kassa.lataa(kortti, 10);
        verify(kortti, times(1)).lataa(10);
    }

    @Test
    public void kortinLataaminenEiTeeMitaanJosSummaOnNegatiivinen() {
        kassa.lataa(kortti, -10);
        verify(kortti, times(0)).lataa(anyInt());
    }
}
