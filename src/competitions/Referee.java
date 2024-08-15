package competitions;

public class Referee implements Runnable {
    private final String name;
    private final Scores scores;
    private final Boolean finishFlag;

    public Referee(String name, Scores scores, Boolean finishFlag) {
        this.name = name;
        this.scores = scores;
        this.finishFlag = finishFlag;
    }

    @Override
    public void run() {
        synchronized (finishFlag) {
            try {
                while (!finishFlag) {
                    finishFlag.wait();
                }
                scores.add(name);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
