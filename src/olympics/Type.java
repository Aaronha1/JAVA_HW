package olympics;
/**
 * Enum representing the types of medals (bronze, silver, gold).
 */
public enum Type {
    BRONZE("bronze"),
    SILVER("silver"),
    GOLD("gold");

    private final String displayType;
    /**
     * Constructor for initializing each medal type with its display name.
     *
     * @param displayType The display name of the medal type (e.g., "gold" for GOLD)
     */
    Type(String displayType) {
        this.displayType = displayType;
    }
    /**
     * Retrieves the display name of the medal type.
     *
     * @return The display name of the medal type
     */
    public String getDisplayName() {
        return displayType;
    }
}
