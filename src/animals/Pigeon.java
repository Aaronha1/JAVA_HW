package animals;

import mobility.Point;
import olympics.Medal;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class representing a pigeon.
 * Extends the AirAnimal class and adds the family property specific to pigeons.
 */
public class Pigeon extends AirAnimal{
    private final String family;

    /**
     * Default constructor for Pigeon.
     * Initializes with default values and sets the family to "columbinae".
     */
    public Pigeon(){
        super();
        this.family = "columbinae";
    }

    /**
     * Constructs a Pigeon with specified properties.
     *
     * @param name The name of the pigeon.
     * @param gender The gender of the pigeon.
     * @param weight The weight of the pigeon.
     * @param speed The speed of the pigeon.
     * @param medals An array of medals won by the pigeon.
     * @param wingspan The wingspan of the pigeon.
     * @param family The family of the pigeon.
     */
    public Pigeon(String name, Gender gender, double weight, int speed, Point loction, ArrayList<Medal> medals, int id,
                  int maxEnergy, int energyPerMeter, Orientation orien, double wingspan, String family){
        super(name, gender, weight, speed,loction,medals,id,maxEnergy,energyPerMeter,orien, wingspan);
        this.family = family;
    }

    protected void setImgs() {
        setImgs("pigeon.png");
    }

    /**
     * Checks if this pigeon is equal to another object.
     *
     * @param obj The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        boolean ans = false;
        if (obj instanceof Pigeon){
            if (super.equals(obj))
                ans = (this.family.equals(((Pigeon)obj).family));
        }
        return ans;
    }
    
    /**
     * Returns a string representation of the pigeon.
     *
     * @return A string representation of the pigeon including its family.
     */
    public String toString() {
        return super.toString() + ". From family: " + this.family;
    }
    
    /**
     * Returns the sound made by the pigeon.
     *
     * @return A string representing the sound made by the pigeon.
     */
    protected String speak() {
        return "Arr-rar-rar-rar-raah";
    }


//    public void drewObject(Graphics g) {
//
//    }
}
