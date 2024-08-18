package animals;

import java.util.concurrent.atomic.AtomicBoolean;

public class AnimalThread implements Runnable {
    private final Animal participant;
    private final double neededDistance;
    private final AtomicBoolean startFlag;
    private final AtomicBoolean finishFlag;
    private static long SLEEP_TIME = 1000;

    public AnimalThread(Animal participant, double neededDistance, AtomicBoolean startFlag, AtomicBoolean finishFlag) {
        this.participant = participant;
        this.neededDistance = neededDistance;
        this.startFlag = startFlag;
        this.finishFlag = finishFlag;
    }

    public static long getSleepTime(){return SLEEP_TIME;}

    public static boolean setSleepTime(long time){
        if (time > 0) {
            SLEEP_TIME = time;
            return true;
        }
        return false;
    }
    @Override
    public void run() {
        while (true) {

            synchronized (startFlag) {
                try {
                    while (!startFlag.get()) {
                        startFlag.wait();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }


            while (true) {
                synchronized (participant) {
                    participant.move();
                    if (participant.getTotalDistance() >= neededDistance) {
                        synchronized (finishFlag) {
                            finishFlag.set(true);
                        }
                        break;
                    }
                }

                try {
                    Thread.sleep(getSleepTime());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            synchronized (finishFlag){
                finishFlag.notifyAll();
            }
            break;
        }
    }
}
