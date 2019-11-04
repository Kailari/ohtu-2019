package ohtuesimerkki;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class StatisticsTest {
    private final Player kurri = new Player("Kurri",   "EDM", 37, 53);
    private final List<Player> edm = List.of(
            kurri,
            new Player("Semenko", "EDM", 4, 12),
            new Player("Gretzky", "EDM", 35, 89)
    );
    private final Reader reader = () -> {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Semenko", "EDM", 4, 12));
        players.add(new Player("Lemieux", "PIT", 45, 54));
        players.addAll(edm);
        return players;
    };

    private Statistics statistics;

    @Before
    public void setUp() {
        statistics = new Statistics(reader);
    }

    @Test
    public void SearchLoytaaJosHakutermiOnNimiKokonaan() {
        Player loydetty = statistics.search("Kurri");
        assertEquals(kurri, loydetty);
    }

    @Test
    public void SearchLoytaaJosHakutermiOnNimenOsa() {
        Player loydetty = statistics.search("Kur");
        assertEquals(kurri, loydetty);
    }

    @Test
    public void SearchPalauttaaNullJosNimeaEiOle() {
        Player loydetty = statistics.search("Jaska");
        assertNull(loydetty);
    }

    @Test
    public void TeamLoytaaKaikkiJoukkueenPelaajat() {
        assertTrue(statistics.team("EDM").containsAll(edm));
    }

    @Test
    public void TeamPalauttaaTyhjanTuntemattomallaNimella() {
        assertEquals(0, statistics.team("tätä nimeä ei löydy").size());
    }

    @Test
    public void TopScorersPalauttaaOikeanLukumaaran() {
        assertEquals(3, statistics.topScorers(3).size());
    }

    @Test
    public void TopScorersPalauttaaOikeatPelaajat() {
        List<Player> loydetty = statistics.topScorers(3);
        assertEquals(loydetty.get(0).getName(), "Gretzky");
        assertEquals(loydetty.get(1).getName(), "Lemieux");
        assertEquals(loydetty.get(2).getName(), "Kurri");
    }
}
