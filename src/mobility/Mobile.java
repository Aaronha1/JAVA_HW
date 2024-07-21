package mobility;
import olympics.Medal;

/**
 * Abstract class representing a mobile object that can be located in space.
 * Implements the ILocatable interface to provide methods for managing its location.
 */
public abstract class Mobile implements ILocatable {
    private Point location;
    private double totalDistance;

    /**
     * Default constructor initializes the mobile object at the origin (0,0) with zero total distance.
     */
    
    public Mobile(){
        this.location = new Point(0,0);
        this.totalDistance = 0.0;
    }
    /**
     * Constructor that initializes the mobile object at a specified location with zero total distance.
     *
     * @param p The initial location of the mobile object
     */
    
    public Mobile(Point p){
        this.location = new Point(p.getX(),p.getY());
        this.totalDistance = 0.0;
    }
    /**
     * Adds the given distance to the total distance traveled by the mobile object.
     *
     * @param toAdd The distance to add to the total distance
     */
    public void addTotalDistance(double toAdd){
        this.totalDistance = this.totalDistance + toAdd;
    }
    /**
     * Calculates the Euclidean distance between the current location of the mobile object and a given point.
     *
     * @param p The point to which the distance is calculated
     * @return The distance between the current location and the given point
     */
    public double calcDistance(Point p){
        double x = p.getX() - this.location.getX();
        double y = p.getY() - this.location.getY();
        return Math.sqrt(x*x + y*y);
    }
    /**
     * Moves the mobile object to the specified location and returns the distance traveled.
     * Updates the total distance traveled by adding the distance to the previous total.
     *
     * @param p The new location to move the mobile object
     * @return The distance traveled to reach the new location
     */
    
    public double move(Point p){
        double d = this.calcDistance(p);
        this.totalDistance = this.totalDistance + d;
        return d;
    }
    /**
     * Retrieves the current location of the mobile object.
     *
     * @return The current location of the mobile object as a Point
     */
    public Point getLocation(){
        return location;
    }
    /**
     * Sets the location of the mobile object to the specified Point.
     *
     * @param p The new location to set for the mobile object
     * @return true if the location was successfully set, false otherwise (e.g., if the input Point is null)
     */
    public boolean setLocation(Point p){
        if(p != null){
            this.location = new Point(p.getX(), p.getY());
            return true;
        }
        return false;
    }
    /**
     * Checks whether this mobile object is equal to another object.
     *
     * @param obj The object to compare with this mobile object
     * @return true if the objects are equal (same location and total distance), false otherwise
     */
    public boolean equals(Object obj) {
        boolean ans = false;
        if(obj instanceof Mobile){
            ans = (this.getLocation() == ((Mobile)obj).getLocation() &&
                    this.totalDistance == ((Mobile)obj).totalDistance);
        }
        return ans;
    }

    /**
     * Returns a string representation of the mobile object, including its location and total distance traveled.
     *
     * @return A string representation of the mobile object
     */
    
    public String toString() {
        return "Mobile{" +
                "location=" + location +
                ", totalDistance=" + totalDistance +
                '}';
    }
}
