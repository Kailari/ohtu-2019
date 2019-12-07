package statistics.matcher;

import statistics.Player;

import java.util.Arrays;

public class Or implements Matcher {
    private final Matcher[] matchers;

    public Or(final Matcher... matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Player p) {
        return Arrays.stream(this.matchers)
                     .anyMatch(matcher -> matcher.matches(p));
    }
}
