package statistics.matcher;

import statistics.Player;

public class All implements Matcher {
    @Override
    public boolean matches(final Player p) {
        return true;
    }
}
