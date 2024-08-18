package competitions;

import animals.*;
import graphics.CompetitionInfo;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class RegularTournament extends Tournament {
    private AtomicBoolean startFlag;
    private final Scores scores = new Scores();
    private static final RegularTournament instance = new RegularTournament(5);

    public RegularTournament(){    }
    private RegularTournament(int k){
        this.startFlag = new AtomicBoolean(false);
    }

    public static synchronized RegularTournament getInstance() {
        return instance;
    }

    @Override
    protected void setup(ArrayList<ArrayList<Animal>> groups) {


        int numGroups = groups.size();

        for (ArrayList<Animal> group : groups) {
            for (Animal animal : group) {
                AtomicBoolean finishFlag = new AtomicBoolean(false);
                double neededDistance = CompetitionInfo.getDistanceNeeded() * 2;

                AnimalThread animalThread = new AnimalThread(animal, neededDistance, getInstance().startFlag, finishFlag);
                new Thread(animalThread).start();

                Referee referee = new Referee(animal.getAnimalName(), getInstance().scores,finishFlag);
                new Thread(referee).start();
            }
        }
        tournamentThread = new TournamentThread(getInstance().scores, getInstance().startFlag, numGroups);
        new Thread(tournamentThread).start();
    }
    public void upFlag(){startFlag.set(true);}
}
