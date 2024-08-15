package competitions;

import animals.*;
import graphics.CompetitionInfo;

import java.util.*;

public class RegularTournament extends Tournament {
    private static Boolean startFlag = true;
    private static final Scores scores = new Scores();

    @Override
    protected void setup(ArrayList<ArrayList<Animal>> groups) {
        int numGroups = groups.size();

        for (ArrayList<Animal> group : groups) {
            for (Animal animal : group) {
                Boolean finishFlag = true;
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
    public void upFlag() { startFlag = true; }
}
