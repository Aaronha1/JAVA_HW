package animals;

import olympics.Medal;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class representing a dolphin.
 * Extends the WaterAnimal class and adds the water type property specific to dolphins.
 */
public class Dolphin extends WaterAnimal{
    private final WaterType waterType;
    
    /**
     * Default constructor for Dolphin.
     * Initializes with default values and sets the water type to SEA.
     */
    public Dolphin(){
        super();
        this.waterType = WaterType.SEA;
    }
    /**
     * Constructs a Dolphin with specified properties.
     *
     * @param name The name of the dolphin.
     * @param gender The gender of the dolphin.
     * @param weight The weight of the dolphin.
     * @param speed The speed of the dolphin.
     * @param medals An array of medals won by the dolphin.
     * @param diveDept The dive depth of the dolphin.
     * @param waterType The type of water the dolphin lives in.
     */
    public Dolphin(String name, Gender gender, double weight, int speed, ArrayList<Medal> medals, int id, int size, int maxEnergy,
                   int energyPerMeter, Orientation orien, double diveDept, WaterType waterType){
        super(name, gender, weight, speed,medals,id,size,maxEnergy,energyPerMeter,orien, diveDept);
        this.waterType = waterType;
    }
    
    /**
     * Checks if this dolphin is equal to another object.
     *
     * @param obj The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        boolean ans = false;
        if (obj instanceof Dolphin){
            if (super.equals(obj))
                ans = (this.waterType == ((Dolphin)obj).waterType );
        }
        return ans;
    }
    
    /**
     * Returns a string representation of the dolphin.
     *
     * @return A string representation of the dolphin including its water type.
     */
    public String toString() {
        return super.toString() + ". Water type: " + this.waterType.getDisplayWaterType();
    }
    
    /**
     * Returns the sound made by the dolphin.
     *
     * @return A string representing the sound made by the dolphin.
     */
    protected String speak() {
        return "Click-click";
    }
    public void drewObject(Graphics g) {
        try {
            img1 = ImageIO.read(new File(PICTURE_PATH + "dolphin1.png" ));
        }
        catch (IOException e){
            System.out.println("Cannot load image");
        }
    }
}
