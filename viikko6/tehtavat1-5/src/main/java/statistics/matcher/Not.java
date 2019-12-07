package statistics.matcher;

import statistics.Player;

public class Not implements Matcher {
    private final Matcher negated;

    public Not(final Matcher negated) {
        this.negated = negated;
    }

    @Override
    public boolean matches(final Player p) {
        return !this.negated.matches(p);
    }
}
