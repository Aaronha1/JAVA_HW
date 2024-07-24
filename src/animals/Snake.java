package animals;

import olympics.Medal;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class representing a snake.
 * Extends the TerrestrialAnimals class and implements the IReptile interface.
 */
public class Snake extends TerrestrialAnimals implements IReptile{

    private final double length;
    private final Poisonous poisonous;
    
    /**
     * Default constructor for Snake.
     * Initializes with default values: length 1.5 meters and poisonous as Toxic.
     */
    public Snake(){
        super();
        this.length = 1.5;
        this.poisonous = Poisonous.LOW;
    }
    
    /**
     * Constructs a Snake with specified properties.
     *
     * @param name The name of the snake.
     * @param gender The gender of the snake.
     * @param weight The weight of the snake.
     * @param speed The speed of the snake.
     * @param medals An array of medals won by the snake.
     * @param length The length of the snake.
     * @param poisonous Indicates whether the snake is poisonous or not.
     */
    public Snake(String name, Gender gender, double weight, int speed, ArrayList<Medal> medals, int id, int size, int maxEnergy,
                 int energyPerMeter, Orientation orien, double length, Poisonous poisonous){
        super(name,gender,weight,speed,medals,id,size,maxEnergy,energyPerMeter,orien, 0);
        this.length = length;
        this.poisonous = poisonous;
    }

    private void setImgs() {
        setImgs("snake2E","snake2S","snake2W","snake2N");
    }

    /**
     * Checks if this snake is equal to another object.
     *
     * @param obj The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        boolean ans = false;
        if (obj instanceof Snake){
            if (super.equals(obj))
                ans = (this.length == ((Snake)obj).length && this.poisonous == ((Snake)obj).poisonous);
        }
        return ans;
    }
    
    /**
     * Returns a string representation of the snake.
     *
     * @return A string representation of the snake including its length and poisonous state.
     */
    public String toString() {
        return super.toString() + ". Length: " + this.length + ", and the snake "+ this.poisonous.getDisplayGender();
    }


    /**
     * Returns the sound made by the snake.
     *
     * @return A string representing the sound made by the snake.
     */
    protected String speak() {
        return "ssssssss";
    }


    /**
     * Increases the speed of the snake by a specified amount, ensuring it does not exceed the maximum speed.
     *
     * @param num The amount by which to increase the speed of the snake.
     */
    public void speedUp(int num) {
        if ( this.getSpeed() +  (double) num <= MAX_SPEED){
            this.setSpeed(num);
        }
        else {
            this.setSpeed(MAX_SPEED);
        }
    }
    
    /**
     * Returns the maximum speed that a snake can achieve.
     *
     * @return The maximum speed that a snake can achieve.
     */
    public static double getMaxSpeed(){
        return MAX_SPEED;
    }
    public void drewObject(Graphics g) {

    }
}
