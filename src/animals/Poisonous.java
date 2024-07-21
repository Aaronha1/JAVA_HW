package animals;

/**
 * Enum representing whether an animal is poisonous.
 * Provides constants for high , medium and low.
 */
public enum Poisonous {

    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    private final String displayPoisonous;

    /**
     * Constructor for Poisonous enum.
     *
     * @param displayPoisonous The display name indicating poisonous state.
     */
    Poisonous(String displayPoisonous) {
        this.displayPoisonous = displayPoisonous;
    }

    /**
     * Returns the display name indicating poisonous state.
     *
     * @return The display name indicating poisonous state.
     */
    public String getDisplayGender() {
        return displayPoisonous;
    }
}
