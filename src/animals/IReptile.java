package animals;

/**
 * Interface representing behaviors of a reptile.
 * Defines constants and methods related to reptile behavior.
 */
public interface IReptile {
    /**
     * Maximum speed constant for reptiles.
     */
    public static final int MAX_SPEED = 5;

    /**
     * Method to speed up the reptile by a specified amount.
     *
     * @param num The amount by which to increase the speed of the reptile.
     */
    public void speedUp(int num);
}
