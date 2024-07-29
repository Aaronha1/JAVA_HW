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
        this.setPosition(new Point(0,0));
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
    public TerrestrialAnimals(String name, Gender gender, double weight, int speed, Point loction, ArrayList<Medal> medals, int id,
                              int maxEnergy, int energyPerMeter, Orientation orien, int noLegs){
        super(name,gender,weight,speed,loction,medals,id,maxEnergy,energyPerMeter,Orientation.NORTH);
        this.noLegs = noLegs;
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
    protected void setImgs() { }


    /**
     * Returns a string representation of the terrestrial animal.
     *
     * @return A string representation of the terrestrial animal including its number of legs.
     */
    public String toString() {
        return super.toString() + ". With " + getNoLegs() + " legs ";
    }
    public int getNoLegs(){
        return noLegs;
    }

    protected void changeDirection(int x, int y, int mx, int my) {
        if (x == 0 && y == 0) {
            changeDirection(Orientation.EAST);
        } else if (x == 0 && y == my) {
            changeDirection(Orientation.NORTH);
        } else if (x == mx && y == 0) {
            changeDirection(Orientation.SOUTH);
        } else if (x == mx && y == my) {
            changeDirection(Orientation.WEST);
        }
    }

}
