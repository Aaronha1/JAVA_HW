package animals;

import mobility.Point;
import olympics.Medal;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class representing a cat.
 * Extends the TerrestrialAnimals class and adds the castrated property specific to cats.
 */
public class Cat extends TerrestrialAnimals{
    private final boolean castrated;

    /**
     * Default constructor for Cat.
     * Initializes with default values and sets castrated to false.
     */
    public Cat(){
        super();
        this.castrated = false;
    }

    /**
     * Constructs a Cat with specified properties.
     *
     * @param name The name of the cat.
     * @param gender The gender of the cat.
     * @param weight The weight of the cat.
     * @param speed The speed of the cat.
     * @param medals An array of medals won by the cat.
     * @param noLegs The number of legs the cat has.
     * @param castrated Indicates whether the cat is castrated.
     */
    public Cat(String name, Gender gender, double weight, int speed, Point loction, ArrayList<Medal> medals, int id, int maxEnergy,
               int energyPerMeter, Orientation orien, int noLegs, boolean castrated){
        super(name, gender, weight, speed,loction, medals,id,maxEnergy,energyPerMeter,orien, noLegs);
        this.castrated = castrated;
    }

    protected void setImgs(){
        setImgs("cat2E.png","cat2S.png","cat2W.png","cat2N.png");
    }

    
    /**
     * Checks if this cat is equal to another object.
     *
     * @param obj The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        boolean ans = false;
        if (obj instanceof Cat){
            if (super.equals(obj)){
                ans = this.castrated == (((Cat)obj).castrated);
            }
        }
        return ans;
    }
    
    /**
     * Returns a string representation of the cat.
     *
     * @return A string representation of the cat including whether it is castrated.
     */
    public String toString() {
        return super.toString() + ". Castrated: " + this.castrated ;
    }

    /**
     * Returns the sound made by the cat.
     *
     * @return A string representing the sound made by the cat.
     */
    protected String speak() {
        return "Meow";
    }

}
