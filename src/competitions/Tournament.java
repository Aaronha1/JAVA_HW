package competitions;

import animals.Animal;
import graphics.CompetitionInfo;

import java.util.ArrayList;

public abstract class Tournament {
    protected TournamentThread tournamentThread;

    public Tournament() {
        setup(CompetitionInfo.getGroups());
    }

    protected abstract void setup(ArrayList<ArrayList<Animal>> groups);

    public TournamentThread getTournamentThread() {
        return tournamentThread;
    }
}
