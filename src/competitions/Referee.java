package competitions;

import java.util.concurrent.atomic.AtomicBoolean;

public class Referee implements Runnable {
    private final String name;
    private final Scores scores;
    private final AtomicBoolean finishFlag;

    public Referee(String name, Scores scores, AtomicBoolean finishFlag) {
        this.name = name;
        this.scores = scores;
        this.finishFlag = finishFlag;
    }

    @Override
    public void run() {
        synchronized (finishFlag) {
            try {
                while (!finishFlag.get()) {
                    finishFlag.wait();
                }
                scores.add(name);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
