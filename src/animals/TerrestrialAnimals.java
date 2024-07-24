package animals;

import mobility.Point;
import olympics.Medal;

import java.awt.*;
import java.util.ArrayList;

/**
 * Abstract class representing terrestrial animals.
 * Extends the Animal class and provides additional properties specific to terrestrial animals.
 */
public class TerrestrialAnimals extends Animal implements ITerrestrialAnimals {
    private final int noLegs;
    
    /**
     * Default constructor for TerrestrialAnimals.
     * Initializes with default values: position (0,20) and 2 legs.
     */
    public TerrestrialAnimals(){
        super();
        this.setPosition(new Point(0,20));
        this.noLegs = 2;
    }
    
    /**
     * Constructs a TerrestrialAnimals with specified properties.
     *
     * @param name The name of the terrestrial animal.
     * @param gender The gender of the terrestrial animal.
     * @param weight The weight of the terrestrial animal.
     * @param speed The speed of the terrestrial animal.
     * @param medals An array of medals won by the terrestrial animal.
     * @param noLegs The number of legs the terrestrial animal has.
     */
    public TerrestrialAnimals(String name, Gender gender, double weight, int speed, ArrayList<Medal> medals, int id, int size,
                              int maxEnergy, int energyPerMeter, Orientation orien, int noLegs){
        super(name,gender,weight,speed,new Point(0,0),medals,id,size,maxEnergy,energyPerMeter,orien);
        this.noLegs = noLegs;
    }

    public void setImgs(String e,String s,String w,String n){
        img1 = Img(e);
        img2 = Img(s);
        img3 = Img(w);
        img4 = Img(n);
    }
    
    /**
     * Checks if this terrestrial animal is equal to another object.
     *
     * @param obj The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        boolean ans = false;
        if (obj instanceof TerrestrialAnimals){
            if (super.equals(obj)){
                ans = this.noLegs == ((TerrestrialAnimals)obj).noLegs;
            }
        }
        return ans;
    }

    protected String speak() {
        return null;
    }

    /**
     * Returns a string representation of the terrestrial animal.
     *
     * @return A string representation of the terrestrial animal including its number of legs.
     */
    public String toString() {
        return super.toString() + ". With " + this.noLegs + " legs ";
    }
    public int getNoLegs(){
        return noLegs;
    }

    @Override
    public void drewObject(Graphics g) {

    }
}
