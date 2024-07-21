package animals;

/**
 * Enum representing the gender of an animal.
 * Provides constants for Male, Female, and Hermaphrodite genders.
 */
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    HERMAPHRODITE("Hermaphrodite");

    private final String displayGender;

    /**
     * Constructor for Gender enum.
     *
     * @param displayGender The display name of the gender.
     */
    Gender(String displayGender) {
        this.displayGender = displayGender;
    }

    /**
     * Returns the display name of the gender.
     *
     * @return The display name of the gender.
     */
    public String getDisplayGender() {
        return displayGender;
    }
}
