package animals;

import mobility.Point;
import olympics.Medal;

import java.awt.*;
import java.util.ArrayList;

/**
 * Abstract class representing water animals.
 * Extends the Animal class and provides additional properties specific to water animals.
 */
public class WaterAnimal extends Animal implements IWaterAnimal{

    private double diveDept;
    
    /**
     * Constructs a WaterAnimal with specified properties.
     *
     * @param name The name of the water animal.
     * @param gender The gender of the water animal.
     * @param weight The weight of the water animal.
     * @param speed The speed of the water animal.
     * @param medals An array of medals won by the water animal.
     * @param diveDept The initial diving depth of the water animal.
     */
    public WaterAnimal(String name, Gender gender, double weight, int speed, ArrayList<Medal> medals, int id, int size,
                       int maxEnergy, int energyPerMeter, Orientation orien, double diveDept){
        super(name,gender,weight,speed,new Point(50,0),medals,id,size,maxEnergy,energyPerMeter,orien);
        this.diveDept = diveDept;
    }
    
    /**
     * Default constructor for WaterAnimal.
     * Initializes with default values: position (50,0) and initial diving depth -200.
     */
    public WaterAnimal(){
        super();
        this.setPosition(new Point(50,0));
        this.diveDept = -200;
    }

    /**
     * Checks if this water animal is equal to another object.
     *
     * @param obj The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        boolean ans = false;
        if (obj instanceof WaterAnimal){
            if (super.equals(obj)){
                ans = this.diveDept == ((WaterAnimal)obj).diveDept;
            }
        }
        return ans;
    }
    protected String speak() {
        return null;
    }

    /**
     * Returns a string representation of the water animal.
     *
     * @return A string representation of the water animal including its diving depth.
     */
    public String toString() {
        return super.toString() + ". Dive deeper " + this.diveDept;
    }
    
    /**
     * Allows the water animal to dive by a specified depth.
     *
     * @param dive The depth by which the water animal dives.
     * @return True if the dive is within the maximum depth limit, false otherwise.
     */
    public boolean Dive(double dive){
        if (this.diveDept + dive <= MAX_DIVE){
            this.diveDept = this.diveDept + dive;
            return true;
        }
        return false;
    }

    @Override
    public void drewObject(Graphics g) {

    }

}
