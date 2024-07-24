package animals;

import olympics.Medal;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class representing a whale, a type of water animal.
 * Extends the WaterAnimal class and provides additional properties specific to whales.
 */
public class Whale extends WaterAnimal {
    private final String foodType;
    
    /**
     * Default constructor for Whale.
     * Initializes with default values: food type "fish".
     */
    public Whale(){
        super();
        this.foodType = "fish";
        setImgs();
    }
    
    /**
     * Constructs a Whale with specified properties.
     *
     * @param name The name of the whale.
     * @param gender The gender of the whale.
     * @param weight The weight of the whale.
     * @param speed The speed of the whale.
     * @param medals An array of medals won by the whale.
     * @param diveDept The diving depth capability of the whale.
     * @param foodType The type of food the whale consumes.
     */
    public Whale(String name, Gender gender, double weight, int speed, ArrayList<Medal> medals, int id, int size, int maxEnergy,
                 int energyPerMeter, Orientation orien, double diveDept, String foodType){
        super(name, gender, weight, speed,medals,id,size,maxEnergy,energyPerMeter,orien, diveDept);
        this.foodType = foodType;
        setImgs();
    }

    private void setImgs() {
        setImgs("whale2");
    }

    /**
     * Checks if this whale is equal to another object.
     *
     * @param obj The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        boolean ans = false;
        if (obj instanceof Whale){
            if (super.equals(obj))
                ans = (this.foodType.equals(((Whale)obj).foodType));
        }
        return ans;
    }
    
    /**
     * Returns a string representation of the whale.
     *
     * @return A string representation of the whale including its food type.
     */
    public String toString() {
        return super.toString() + ". Food type: " + this.foodType;
    }
    
    /**
     * Specifies the sound the whale makes.
     *
     * @return The sound "Splash" indicating the noise made by the whale.
     */
    protected String speak() {
        return "Splash";
    }
    public void drewObject(Graphics g) {

    }
}
