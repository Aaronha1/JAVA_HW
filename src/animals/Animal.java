package animals;
import graphics.BackGroundPanel;
import graphics.IDrawable;
import mobility.ILocatable;
import mobility.Mobile;
import mobility.Point;
import olympics.Medal;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Animal extends Mobile implements ILocatable, IDrawable, Cloneable, IAnimal {

    private final String name;
    private final Gender gender;
    private final double weight;
    private int speed;
    private final ArrayList<Medal> medals;
    private final int size = 65;
    private final int id;
    private int energyAmount;
    private final int maxEnergy;
    private int energyPerMeter;
    private BackGroundPanel pan;
    private BufferedImage img1 = null, img2 = null, img3 = null, img4 = null;
    private Orientation orien;
    private int energyConsumption;
    private Point location;

    public Animal() {
        this("Dan", Gender.HERMAPHRODITE, 10, 2, new Point(0, 0), new ArrayList<>(), 123, 10, 2, Orientation.EAST);
    }

    public Animal(String name, Gender gender, double weight, int speed, Point location, ArrayList<Medal> medals,
                  int id, int maxEnergy, int energyPerMeter, Orientation orien) {
        super(location);
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.speed = speed;
        this.location = location;
        this.medals = medals != null ? new ArrayList<>(medals) : new ArrayList<>();
        this.id = id;
        this.maxEnergy = maxEnergy;
        this.energyPerMeter = energyPerMeter;
        this.orien = orien;
        this.energyAmount = 0;
        this.energyConsumption = 0;
        setImgs();
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    @Override
    public String toString() {
        return super.toString() + "The animal " + this.name +
                " with ID- " + this.id +
                ". Size: " + this.size +
                ". With max energy " + this.maxEnergy +
                ", and " + this.energyPerMeter + " per meter" +
                ", " + this.gender.getDisplayGender() +
                ", weighs " + this.weight +
                " kilos and its speed is " + this.speed + "KMh" +
                ". It is currently at the position: " + this.location +
                " and has the following medals: " + this.medals.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Animal)) return false;
        Animal other = (Animal) obj;
        return this.name.equals(other.name) &&
                this.gender == other.gender &&
                Double.compare(this.weight, other.weight) == 0 &&
                this.speed == other.speed &&
                this.location.equals(other.location) &&
                Arrays.equals(this.medals.toArray(), other.medals.toArray()) &&
                this.id == other.id &&
                this.maxEnergy == other.maxEnergy &&
                this.energyPerMeter == other.energyPerMeter;
    }

    public void makeSound() {
        System.out.println("Animal " + getAnimalName() + " said " + speak());
    }

    protected void setPosition(Point p) {
        this.location = new Point(p.getX(), p.getY());
    }

    public String getAnimalName() {
        return name;
    }

    public int getenergyAmount() {
        return energyAmount;
    }

    public int getSpeed() {
        return speed;
    }

    protected boolean setSpeed(int num) {
        if (num > 0) {
            this.speed += num;
            return true;
        }
        return false;
    }

    abstract protected String speak();

    abstract protected void setImgs();

    public void loadImages(String nm) {
        try {
            if (img1 == null) {
                img1 = ImageIO.read(new File(nm));
            } else if (img2 == null) {
                img2 = ImageIO.read(new File(nm));
            } else if (img3 == null) {
                img3 = ImageIO.read(new File(nm));
            } else if (img4 == null) {
                img4 = ImageIO.read(new File(nm));
            }
        } catch (IOException e) {
            System.out.println("Cannot load image: " + nm);
            e.printStackTrace();
        }
        System.out.println("Image loaded: " + nm);
    }

    public BufferedImage getImg(int imgNum) {
        return switch (imgNum) {
            case 1 -> img1;
            case 2 -> img2;
            case 3 -> img3;
            case 4 -> img4;
            default -> null;
        };
    }


    public void move() {
        if (energyAmount <= 0) {
            return; // Animal stops moving when out of energy
        }

        // Calculate movement based on speed and direction
        int deltaX = 0, maxX = 800 - size;
        int deltaY = 0, maxY = 600 - size;

        switch (orien) {
            case EAST -> deltaX = getSpeed();
            case WEST -> deltaX = -getSpeed();
            case NORTH -> deltaY = getSpeed();
            case SOUTH -> deltaY = -getSpeed();
        }
        int x = location.getX() + deltaX;
        int y = location.getY() + deltaY;

        x = Math.max(0,Math.min(x,maxX));
        y = Math.max(0,Math.min(y,maxY));

        if (x == 0 && y == 0) {
            changeDirection(Orientation.NORTH);
        } else if (x == 0 && y == maxY) {
            changeDirection(Orientation.EAST);
        } else if (x == maxX && y == 0) {
            changeDirection(Orientation.WEST);
        } else if (x == maxX && y == maxY) {
            changeDirection(Orientation.SOUTH);
        }

        Point newLocation = new Point(x, y);


        energyAmount -= energyPerMeter;
        if (energyAmount < 0) {
            energyAmount = 0;
        }

        setPosition(newLocation);
        System.out.println("Animal moved to: " + newLocation + "dictance: " +move(newLocation));
    }
    private void changeDirection(Orientation newOrien) {
        orien = newOrien;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void addMedal(Medal medal) {
        this.medals.add(medal);
    }

    public ArrayList<Medal> getMedals() {
        return new ArrayList<>(medals);
    }

    public boolean eat(int energy){

        if (energy > 0){
            if (this.energyAmount + energy <= this.maxEnergy){
                this.energyAmount = this.energyAmount +energy;
                this.energyConsumption = this.energyConsumption +energy;
            }
            else {
                int x = this.maxEnergy - this.energyAmount;
                this.energyConsumption = this.energyConsumption +x;
                this.energyAmount = this.maxEnergy;

            }
            return true;
        }
        return false;
    }

    protected void setImgs(String e, String s, String w, String n) {
        loadImages(PICTURE_PATH + e);
        loadImages(PICTURE_PATH + s);
        loadImages(PICTURE_PATH + w);
        loadImages(PICTURE_PATH + n);
    }

    protected void setImgs(String img) {
        setImgs(img, img, img, img);
    }

    public void drewObject(Graphics g) {
        switch (orien) {
            case EAST -> g.drawImage(img1, location.getX(), location.getY(), size, size, pan);
            case SOUTH -> g.drawImage(img2, location.getX(), location.getY() - size / 10, size, size, pan);
            case WEST -> g.drawImage(img3, location.getX(), location.getY() - size / 10, size * 2, size, pan);
            case NORTH -> g.drawImage(img4, location.getX() - size / 2, location.getY() - size / 10, size, size * 2, pan);
        }
        System.out.println("Drew animal at: " + location);
    }
}
