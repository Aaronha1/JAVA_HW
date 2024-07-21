package animals;

/**
 * Enum representing types of water bodies.
 * Provides constants for Sea and Sweet water types.
 */
public enum WaterType {
    SEA("Sea"),
    SWEET("Sweet");

    private final String displayWaterType;

    /**
     * Constructor for WaterType enum.
     *
     * @param displayWaterType The display name indicating the type of water.
     */
    WaterType(String displayWaterType) {
        this.displayWaterType = displayWaterType ;
    }

    /**
     * Returns the display name indicating the type of water.
     *
     * @return The display name indicating the type of water.
     */
    public String getDisplayWaterType() {
        return displayWaterType;
    }
}
