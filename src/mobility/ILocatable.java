package mobility;
/**
 * Interface representing a locatable object with a specific location.
 */
public interface ILocatable {
    /**
     * Retrieves the current location of the object.
     *
     * @return The current location of the object as a Point
     */
    public Point getLocation();
    /**
     * Sets the location of the object to the specified Point.
     *
     * @param p The new location to set
     * @return true if the location was successfully set, false otherwise
     */
    public boolean setLocation(Point p);
}
