package ohtu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class Tapahtumankuuntelija implements ActionListener {
    private JButton plus;
    private JButton miinus;
    private JButton nollaa;
    private JButton undo;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private Sovelluslogiikka sovellus;

    private final Map<JButton, Komento> komennot;
    private Komento edellinen;

    public Tapahtumankuuntelija(
            JButton plus,
            JButton miinus,
            JButton nollaa,
            JButton undo,
            JTextField tuloskentta,
            JTextField syotekentta
    ) {
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.undo = undo;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.sovellus = new Sovelluslogiikka();
        this.komennot = Map.ofEntries(
                Map.entry(plus, new Komento.Summa(this.sovellus)),
                Map.entry(miinus, new Komento.Erotus(this.sovellus)),
                Map.entry(nollaa, new Komento.Nollaa(this.sovellus))
        );
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton source = (JButton) ae.getSource();
        if (source == undo) {
            edellinen.peru();
            edellinen = null;
        } else {
            try {
                var arvo = Integer.parseInt(syotekentta.getText());
                var komento = komennot.get((JButton) ae.getSource());
                komento.suorita(arvo);
                edellinen = komento;
            } catch (NumberFormatException ignored) {
            }
        }

        paivitaKentat();
    }

    private void paivitaKentat() {
        int laskunTulos = sovellus.tulos();

        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
        if (laskunTulos == 0) {
            nollaa.setEnabled(false);
        } else {
            nollaa.setEnabled(true);
        }
        undo.setEnabled(true);
    }
}