


package animals;

import graphics.CompetitionPanel;
import graphics.IDrawable;
import graphics.IMoveable;
import mobility.ILocatable;
import mobility.Mobile;
import mobility.Point;
import olympics.Medal;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;


public abstract class Animal extends Mobile implements ILocatable, IDrawable,Cloneable, IAnimal {
    private final String name;
    private IMoveable iMoveable;
    private IDrawable iDrawable;
    private Cloneable cloneable;
    private final Gender gender;
    private final double weight;
    private int speed;
    private ArrayList<Medal> medals;
    private Point location;
    private int size;
    private int id;

    private int maxEnergy;
    private int energyPerMeter;
    private CompetitionPanel pan;
    protected BufferedImage img1, img2, img3, img4;

    private final Orientation orien;


    public Animal(){
        this.name = "Dan";
        this.gender = Gender.HERMAPHRODITE;
        this.weight = 10;
        this.speed = 2;
        this.location = new Point(0,0);
        this.id = 123;
        this.size = 5;
        this.maxEnergy = 10;
        this.energyPerMeter = 2;
        this.orien = Orientation.EAST;
        this.medals = new ArrayList<>();
    }


    public Animal(String name, Gender gender, double weight, int speed, Point location, ArrayList<Medal> medals,
                  int id, int size, int maxEnergy, int energyPerMeter, Orientation orien){
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.speed = speed;
        if (medals != null) {
            this.medals = new ArrayList<>(medals);
        } else {
            this.medals = new ArrayList<>();
        }
        this.location = new Point(location.getX(),location.getY());
        this.id = id;
        this.size = size;
        this.maxEnergy = maxEnergy;
        this.energyPerMeter = energyPerMeter;
        this.orien = orien;
    }


    public String toString() {
        return "The animal " + this.name +
                " with ID- " + this.id +
                ". Size: " + this.size +
                ". With max energy " + this.maxEnergy +
                ", and " + this.energyPerMeter + " per meter" +
                ", " +this.gender.getDisplayGender() +
                ", weighs " + this.weight +
                " kilos and its speed is " + this.speed + "KMh" +
                ". It is currently at the position: " + this.location +
                " and has the following medals: " + this.medals.toString() ;
    }


    public boolean equals(Object obj) {
        boolean ans = false;
        if (obj instanceof Animal){
            ans = (this.name.equals(((Animal)obj).name) &&
                    this.gender == ((Animal)obj).gender &&
                    this.weight == ((Animal)obj).weight &&
                    this.speed == ((Animal)obj).speed &&
                    this.location == ((Animal)obj).location &&
                    Arrays.equals(this.medals.toArray(), ((Animal)obj).medals.toArray()) &&
                    this.id == ((Animal)obj).id &&
                    this.size == ((Animal)obj).size &&
                    this.maxEnergy == ((Animal)obj).maxEnergy &&
                    this.energyPerMeter == ((Animal)obj).energyPerMeter);
        }
        return ans;
    }


    public void makeSound(){
        System.out.println("Animal " + this.name + " said "+ this.speak());
    }


    protected void setPosition(Point p){
        this.location = new Point(p.getX(),p.getY());
    }

    public String getAnimalName() {
        return name;
    }


    public int getSpeed(){
        return speed;
    }

    protected boolean setSpeed(int num){
        if (num > 0){
            this.speed = this.speed + num;
            return true;
        }
        return false;
    }

    abstract protected String speak();

    public void loadImages(String nm) {

    }
    public double move(Point p){
        return 2.5;
    }
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    public void addMedal(Medal medal) {
        this.medals.add(medal);
    }
    public ArrayList<Medal> getMedals() {
        return medals;
    }
    public boolean eat(int energy){
        if (this.energyPerMeter + energy <= this.maxEnergy){
            this.energyPerMeter = this.energyPerMeter +energy;
            return true;
        }
        return false;
    }

}
