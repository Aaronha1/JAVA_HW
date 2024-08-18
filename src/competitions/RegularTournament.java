package competitions;

import animals.*;
import graphics.CompetitionInfo;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class RegularTournament extends Tournament {
    private static final AtomicBoolean startFlag = new AtomicBoolean(false);
    private Scores scores;

    @Override
    protected void setup(ArrayList<ArrayList<Animal>> groups) {
        scores = new Scores();
        int numGroups = groups.size();

        for (ArrayList<Animal> group : groups) {
            for (Animal animal : group) {
                AtomicBoolean finishFlag = new AtomicBoolean(false);
                double neededDistance = CompetitionInfo.getDistanceNeeded() * 2;

                AnimalThread animalThread = new AnimalThread(animal, neededDistance, startFlag, finishFlag);
                new Thread(animalThread).start();

                Referee referee = new Referee(animal.getAnimalName(), scores,finishFlag);
                new Thread(referee).start();
            }
        }
        tournamentThread = new TournamentThread(scores, startFlag, numGroups);
        new Thread(tournamentThread).start();
    }
    public void upFlag(){
        synchronized (startFlag) {
            startFlag.set(true);
            startFlag.notifyAll();
        }
    }
}
