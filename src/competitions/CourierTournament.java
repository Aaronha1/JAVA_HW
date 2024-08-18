package competitions;

import animals.*;
import graphics.CompetitionInfo;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class CourierTournament extends Tournament {
    private AtomicBoolean startFlag;
    private Scores scores;


    @Override
    protected void setup(ArrayList<ArrayList<Animal>> groups) {
        scores = new Scores();
        startFlag = new AtomicBoolean(true);
        int numGroups = groups.size();
        System.out.println("B: "+startFlag.get());
        List<AtomicBoolean> flags = new ArrayList<>();
        for (ArrayList<Animal> group : groups){
            List<AtomicBoolean> groupFlags = new ArrayList<>();
            for (int i=0; i<group.size(); i++){
                groupFlags.add(new AtomicBoolean(false));
            }
            for (int i=0; i<group.size(); i++){
                Animal animal = group.get(i);
                double neededDistance = CompetitionInfo.getDistanceNeeded();
                AtomicBoolean startFlagForAnimal = (i == 0) ? startFlag : groupFlags.get(i - 1);
                AtomicBoolean finishFlagForAnimal = groupFlags.get(i);
                AnimalThread animalThread = new AnimalThread(animal, neededDistance, startFlagForAnimal, finishFlagForAnimal);
                new Thread(animalThread).start();
                if (i == group.size() - 1) {
                    Referee referee = new Referee(animal.getAnimalName(), scores,finishFlagForAnimal);
                    new Thread(referee).start();
                }
            }
        }
        tournamentThread = new TournamentThread(scores, startFlag, numGroups);
        new Thread(tournamentThread).start();
    }
    public Boolean getStartFlag(){return startFlag.get();}
    public void upFlag(){startFlag.set(true);}
}
