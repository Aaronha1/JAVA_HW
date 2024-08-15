package competitions;

import animals.*;
import graphics.CompetitionInfo;

import java.util.*;

public class CourierTournament extends Tournament {
    private static Boolean startFlag = true;
    private static final Scores scores = new Scores();

    @Override
    protected void setup(ArrayList<ArrayList<Animal>> groups) {
        int numGroups = groups.size();

        List<Boolean> flags = new ArrayList<>();
        for (ArrayList<Animal> group : groups){
            List<Boolean> groupFlags = new ArrayList<>();
            for (int i=0; i<group.size(); i++){
                groupFlags.add(false);
            }
            for (int i=0; i<group.size(); i++){
                Animal animal = group.get(i);
                double neededDistance = CompetitionInfo.getDistanceNeeded();
                Boolean startFlagForAnimal = (i == 0) ? startFlag : groupFlags.get(i - 1);
                Boolean finishFlagForAnimal = groupFlags.get(i);
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
    public void upFlag() { startFlag = true; }

}
