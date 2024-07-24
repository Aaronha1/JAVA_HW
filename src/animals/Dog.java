package animals;

import olympics.Medal;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class representing a dog.
 * Extends the TerrestrialAnimals class and adds the breed property specific to dogs.
 */
public class Dog extends TerrestrialAnimals{

    private final String breed;
    
    /**
     * Default constructor for Dog.
     * Initializes with default values and sets the breed to "Pitbull".
     */
    public Dog(){
        super();
        this.breed = "Pitbull";
        setImgs();
    }
    
    /**
     * Constructs a Dog with specified properties.
     *
     * @param name The name of the dog.
     * @param gender The gender of the dog.
     * @param weight The weight of the dog.
     * @param speed The speed of the dog.
     * @param medals An array of medals won by the dog.
     * @param noLegs The number of legs the dog has.
     * @param breed The breed of the dog.
     */
    public Dog(String name, Gender gender, double weight, int speed, ArrayList<Medal> medals, int id, int size,
               int maxEnergy, int energyPerMeter , Orientation orien, int noLegs, String breed){
        super(name,gender,weight,speed,medals,id,size,maxEnergy,energyPerMeter,orien, noLegs);
        this.breed = breed;
        setImgs();
    }
    private void setImgs(){
        setImgs("dog2E","dog2S","dog2W","dog2N");
    }

    /**
     * Checks if this dog is equal to another object.
     *
     * @param obj The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        boolean ans = false;
        if (obj instanceof Dog){
            if (super.equals(obj)){
                ans = this.breed.equals(((Dog)obj).breed);
            }
        }
        return ans;
    }
    
    /**
     * Returns a string representation of the dog.
     *
     * @return A string representation of the dog including its breed.
     */
    public String toString() {
        return super.toString() + ". Breed: " + this.breed + " legs ";
    }

    /**
     * Returns the sound made by the dog.
     *
     * @return A string representing the sound made by the dog.
     */
    protected String speak() {
        return "Woof Woof";
    }
    public void drewObject(Graphics g) {

    }
}
