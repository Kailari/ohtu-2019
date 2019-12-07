package statistics.matcher;

import statistics.Player;

public class HasFewerThan implements Matcher {
    private final Matcher notHasAtLeast;

    public HasFewerThan(final int value, final String category) {
        this.notHasAtLeast = new Not(new HasAtLeast(value, category));
    }

    @Override
    public boolean matches(Player p) {
        return this.notHasAtLeast.matches(p);
    }
}
