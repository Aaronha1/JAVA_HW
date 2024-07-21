package olympics;

/**
 * Represents a medal won in a tournament.
 */

public class Medal {

    private final Type type;
    private final String tournament;
    private final int year;
    /**
     * Default constructor initializes the medal with default values:
     * - Type: GOLD
     * - Tournament: "European Championship"
     * - Year: 2020
     */
    public Medal(){
        this.type = Type.GOLD;
        this.tournament = "European Championship";
        this.year = 2020;
    }
    /**
     * Parameterized constructor to create a medal with specified attributes.
     *
     * @param type The type of the medal (gold, silver, bronze)
     * @param tournament The name of the tournament where the medal was won
     * @param year The year when the medal was won
     */
    
    public Medal(Type type, String tournament , int year){
        this.type = type;
        this.tournament = tournament;
        this.year = year;
    }
    /**
     * Retrieves the type of the medal.
     *
     * @return The type of the medal (gold, silver, bronze)
     */
    public Type getType() {
        return type;
    }
    /**
     * Retrieves the name of the tournament where the medal was won.
     *
     * @return The name of the tournament
     */
    public String getTournament() {
        return tournament;
    }
    /**
     * Retrieves the year when the medal was won.
     *
     * @return The year when the medal was won
     */
    public int getYear() {
        return year;
    }
    /**
     * Returns a string representation of the medal.
     *
     * @return A string describing the medal's type, tournament, and year
     */
    public String toString() {
        return "The " + this.type.getDisplayName() + " medal was won at the " + this.tournament + " tournament in " + this.year;
    }
    /**
     * Checks whether this medal is equal to another object.
     *
     * @param obj The object to compare with this medal
     * @return true if the objects are equal (same type, tournament, and year), false otherwise
     */

    public boolean equals(Object obj) {
        boolean ans = false;
        if (obj instanceof Medal){
            ans = (this.getType() == ((Medal)obj).getType() &&
                    this.getTournament().equals(((Medal) obj).getTournament()) &&
                    this.getYear() == ((Medal)obj).getYear() );
        }
        return ans;
    }
}
