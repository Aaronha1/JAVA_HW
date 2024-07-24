package animals;
import mobility.Point;
import olympics.Medal;

import java.awt.*;
import java.util.ArrayList;

/**
 * Abstract class representing an air animal.
 * Extends the Animal class and adds wingspan property specific to air animals.
 */
public class AirAnimal extends Animal implements IAirAnimal{

    private final double wingspan;
    
    /**
     * Constructs an AirAnimal with specified properties.
     * 
     * @param name The name of the air animal.
     * @param gender The gender of the air animal.
     * @param weight The weight of the air animal.
     * @param speed The speed of the air animal.
     * @param medals An array of medals won by the air animal.
     * @param wingspan The wingspan of the air animal.
     */
    public AirAnimal(String name, Gender gender, double weight, int speed, ArrayList<Medal> medals, int id, int size,
                     int maxEnergy, int energyPerMeter, Orientation orien, double wingspan){
        super(name,gender,weight,speed, new Point(0,100),medals,id,size,maxEnergy,energyPerMeter,orien);
        this.wingspan = wingspan;
    }
    
    /**
     * Default constructor for AirAnimal.
     * Initializes with default values and sets the position to (0, 100).
     */
    public AirAnimal(){
        super();
        this.setPosition(new Point(0,100));
        this.wingspan = 1.5;
    }

    public void setImgs(String img){
        img1 = Img(img);
    }


    /**
     * Returns a string representation of the air animal.
     * 
     * @return A string representation of the air animal including its wingspan.
     */
    public String toString() {
        return super.toString() + ". WingSpan " + this.wingspan;
    }

    /**
     * Checks if this air animal is equal to another object.
     * 
     * @param obj The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        boolean ans = false;
        if (obj instanceof AirAnimal){
            if (super.equals(obj)){
                ans = this.wingspan == ((AirAnimal)obj).wingspan;
            }
        }
        return ans;
    }


    protected String speak() {
        return null;
    }

    @Override
    public void drewObject(Graphics g) {

    }

}
