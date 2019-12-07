package statistics;

import statistics.matcher.*;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {
    private List<Matcher> matchers = new ArrayList<>();

    public Matcher build() {
        Matcher result = new And(this.matchers.toArray(new Matcher[0]));
        this.matchers.clear();
        return result;
    }

    public QueryBuilder playsIn(final String team) {
        this.matchers.add(new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(final int value, final String category) {
        this.matchers.add(new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(final int value, final String category) {
        this.matchers.add(new HasFewerThan(value, category));
        return this;
    }

    public QueryBuilder oneOf(final Matcher a, final Matcher b) {
        this.matchers.add(new Or(a, b));
        return this;
    }
}
