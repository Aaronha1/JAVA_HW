package competitions;

import java.util.*;

public class Scores {
    private final Map<String, Date> scores;

    public Scores() {
        scores = Collections.synchronizedMap(new HashMap<>());
    }

    public void add(String name) {
        synchronized (scores) {
            scores.put(name, new Date());
        }
    }

    public Map<String, Date> getAll() {
        synchronized (scores) {
            return new HashMap<>(scores);
        }
    }
}
