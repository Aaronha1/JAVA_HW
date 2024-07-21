package graphics;

import mobility.Point;

public interface IMoveable {
    public String getAnimalName();
    public int getSpeed();
    public double move(Point p);
}
