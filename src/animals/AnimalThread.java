package animals;


import competitions.TournamentThread;

public class AnimalThread implements Runnable {
    private final Animal participant;
    private final double neededDistance;
    private final Boolean startFlag;
    private final Boolean finishFlag;
    private static long SLEEP_TIME = 1000;

    public AnimalThread(Animal participant, double neededDistance, Boolean startFlag, Boolean finishFlag) {
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
        synchronized (startFlag) {
            try {
                while (!startFlag) {
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
                        finishFlag.notifyAll();
                    }
                    break;
                }
            }

            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
