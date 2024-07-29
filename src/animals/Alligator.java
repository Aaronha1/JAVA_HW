package animals;

import graphics.CompetitionInfo;
import mobility.Point;
import olympics.Medal;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class representing an alligator.
 * Extends the WaterAnimal class and adds the area of living property specific to alligators.
 */
public class Alligator extends WaterAnimal implements IReptile,IWaterAnimal,ITerrestrialAnimals {

    private final String AreaOfLiving;
    private final IWaterAnimal waterAnimal;
    private final ITerrestrialAnimals terrestrialAnimals;
    /**
     * Default constructor for Alligator.
     * Initializes with default values and sets the area of living to "River".
     */
    public Alligator(){
        super();
        waterAnimal = new WaterAnimal();
        terrestrialAnimals = new TerrestrialAnimals();
        this.AreaOfLiving = "River";
    }
    
    /**
     * Constructs an Alligator with specified properties.
     * 
     * @param name The name of the alligator.
     * @param gender The gender of the alligator.
     * @param weight The weight of the alligator.
     * @param speed The speed of the alligator.
     * @param medals An array of medals won by the alligator.
     * @param diveDept The dive depth of the alligator.
     * @param areaOfLiving The area where the alligator lives.
     */
    public Alligator(String name, Gender gender, double weight, int speed, Point loction, ArrayList<Medal> medals, int id,
                     int maxEnergy, int energyPerMeter, Orientation orien, double diveDept, int noLegs,
                     String areaOfLiving){
        super(name, gender, weight, speed,loction,medals,id,maxEnergy,energyPerMeter,orien, diveDept);
        this.waterAnimal = new WaterAnimal(name,gender,weight,speed,loction,medals,id,maxEnergy,energyPerMeter, orien, diveDept);
        this.terrestrialAnimals = new TerrestrialAnimals(name,gender,weight,speed,loction,medals,id,maxEnergy,
                energyPerMeter,orien, noLegs);
        this.AreaOfLiving = areaOfLiving;
    }

    protected void setImgs(){
        if ("Water".equals(CompetitionInfo.getCategory())){
            setImgs("alligator3.png");
        } else {
            setImgs("alligator2E.png", "alligator2S.png", "alligator2W.png", "alligator2N.png");
        }
    }

    protected void changeDirection(int x, int y, int mx, int my){
        if ("Water".equals(CompetitionInfo.getCategory())){
            if (x  < 20){
                changeDirection(Orientation.EAST);
            } else if (x > 730) {
                changeDirection(Orientation.WEST);
            }
        } else {
            if (x == 0 && y == 0) {
                changeDirection(Orientation.SOUTH);
            } else if (x == 0 && y == my) {
                changeDirection(Orientation.EAST);
            } else if (x == mx && y == 0) {
                changeDirection(Orientation.WEST);
            } else if (x == mx && y == my) {
                changeDirection(Orientation.NORTH);
            }
        }
    }

    /**
     * Checks if this alligator is equal to another object.
     * 
     * @param obj The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        boolean ans = false;
        if (obj instanceof Alligator){
            if (super.equals(obj))
                ans = (this.AreaOfLiving.equals(((Alligator)obj).AreaOfLiving));
        }
        return ans;
    }

    public int getNoLegs() {
        return terrestrialAnimals.getNoLegs();
    }

    /**
     * Returns a string representation of the alligator.
     * 
     * @return A string representation of the alligator including its area of living.
     */
    public String toString() {
        return super.toString() + ". With " + this.getNoLegs() + " legs " + ". Area of living: " + this.AreaOfLiving;
    }
    
    /**
     * Returns the sound made by the alligator.
     * 
     * @return A string representing the sound made by the alligator.
     */
    protected String speak() {
        return "Roar";
    }


    public void speedUp(int num) {
        if ( this.getSpeed() +  (double) num <= MAX_SPEED){
            this.setSpeed(num);
        }
        else {
            this.setSpeed(MAX_SPEED);
        }
    }


    public boolean Dive(double dive) {
        return waterAnimal.Dive(dive);
    }

}
