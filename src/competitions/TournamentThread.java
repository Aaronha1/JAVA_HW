package competitions;

import graphics.CompetitionInfo;

import  java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TournamentThread implements Runnable {
    private static Map<String,Date> realtime;
    private final Scores scores;
    private final AtomicBoolean startSignal;
    private final int groups;

    public TournamentThread(Scores scores, AtomicBoolean startSignal, int groups) {
        this.scores = scores;
        this.startSignal = startSignal;
        this.groups = groups;
    }

    @Override
    public void run() {
        synchronized (startSignal) {
            startSignal.notifyAll();
        }

        while (true) {
            synchronized (scores) {
                realtime = scores.getAll();
            }

            if (realtime.size() == groups) {
                break;
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    public static Map<String,Date> getRealtimeScores(){ return realtime;}

}
