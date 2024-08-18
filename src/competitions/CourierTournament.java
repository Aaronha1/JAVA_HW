package competitions;

import animals.*;
import graphics.CompetitionInfo;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class CourierTournament extends Tournament {
    private static final AtomicBoolean startFlag = new AtomicBoolean(false);
    private Scores scores;


    @Override
    protected void setup(ArrayList<ArrayList<Animal>> groups) {
        scores = new Scores();
        startFlag.set(false);
        int numGroups = groups.size();

        for (ArrayList<Animal> group : groups){
            List<AtomicBoolean> groupFlags = new ArrayList<>();
            for (int i=0; i<group.size(); i++){
                groupFlags.add(new AtomicBoolean(false));
            }
            StringBuilder name = new StringBuilder("Group:");
            for (int i=0; i<group.size(); i++){
                Animal animal = group.get(i);
                double neededDistance = CompetitionInfo.getDistanceNeeded();
                AtomicBoolean startFlagForAnimal = (i == 0) ? startFlag : groupFlags.get(i - 1);
                AtomicBoolean finishFlagForAnimal = groupFlags.get(i);
                AnimalThread animalThread = new AnimalThread(animal, neededDistance, startFlagForAnimal, finishFlagForAnimal);
                new Thread(animalThread).start();
                name.append(" ").append(animal.getAnimalName());
                if (i == group.size() - 1) {
                    Referee referee = new Referee(name.toString(), scores,finishFlagForAnimal);
                    new Thread(referee).start();
                }
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
