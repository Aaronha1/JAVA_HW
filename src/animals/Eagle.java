package animals;

import olympics.Medal;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class representing an eagle.
 * Extends the AirAnimal class and adds the altitude of flight property specific to eagles.
 */
public class Eagle extends AirAnimal{

    private static final double MAX_ALTITUDE = 1000;
    private final double altitudeOfFlight;

    /**
     * Default constructor for Eagle.
     * Initializes with default values and sets the altitude of flight to 30.50.
     */
    public Eagle(){
        super();
        this.altitudeOfFlight = 30.50;
    }
    
    /**
     * Constructs an Eagle with specified properties.
     *
     * @param name The name of the eagle.
     * @param gender The gender of the eagle.
     * @param weight The weight of the eagle.
     * @param speed The speed of the eagle.
     * @param medals An array of medals won by the eagle.
     * @param wingspan The wingspan of the eagle.
     * @param altitudeOfFlight The altitude at which the eagle flies.
     */
    public Eagle(String name, Gender gender, double weight, int speed, ArrayList<Medal> medals, int id, int size, int maxEnergy,
                 int energyPerMeter, Orientation orien, double wingspan, double altitudeOfFlight){
        super(name, gender, weight, speed,medals,id,size,maxEnergy,energyPerMeter,orien, wingspan);
        this.altitudeOfFlight = altitudeOfFlight;
    }
    
    /**
     * Checks if this eagle is equal to another object.
     *
     * @param obj The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        boolean ans = false;
        if (obj instanceof Eagle){
            if (super.equals(obj))
                ans = (this.altitudeOfFlight == ((Eagle)obj).altitudeOfFlight);
        }
        return ans;
    }
    
    /**
     * Returns a string representation of the eagle.
     *
     * @return A string representation of the eagle including its altitude of flight.
     */
    public String toString() {
        return super.toString() + ". Altitude of flight: " + this.altitudeOfFlight;
    }
    
    /**
     * Returns the sound made by the eagle.
     *
     * @return A string representing the sound made by the eagle.
     */
    protected String speak() {
        return "Clack-wack-chack";
    }
    
    /**
     * Returns the maximum altitude an eagle can fly.
     *
     * @return The maximum altitude an eagle can fly.
     */
    public static double getMaxALTITUDE(){
        return MAX_ALTITUDE;
    }

    @Override
    public void drewObject(Graphics g) {

    }
}
