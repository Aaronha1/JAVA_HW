package mobility;
/**
 * Represents a point in a 2-dimensional coordinate system.
 */
public class Point implements Cloneable {
    private final int x;
    private final int y;
    /**
     * Default constructor initializes the point at the origin (0,0).
     */
    
    public Point(){
        this.x = 0;
        this.y = 0;
    }
    /**
     * Constructor initializes the point with specified coordinates.
     *
     * @param x The x-coordinate of the point
     * @param y The y-coordinate of the point
     */
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Checks whether this point is equal to another object.
     *
     * @param obj The object to compare with this point
     * @return true if the objects are equal (have the same coordinates), false otherwise
     */
    
    public boolean equals(Object obj) {
        boolean ans = false;
        if(obj instanceof Point){
            ans = (this.getX() == ((Point)obj).getX() &&
                    this.getY() == ((Point)obj).getY() );
        }
        return ans;
    }
    /**
     * Returns a string representation of the point.
     *
     * @return A string representation of the point in the format "(x,y)"
     */
    
    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ").";
    }

    /**
     * Retrieves the x-coordinate of the point.
     *
     * @return The x-coordinate of the point
     */
    
    public int getX() {
        return this.x;
    }
    /**
     * Retrieves the y-coordinate of the point.
     *
     * @return The y-coordinate of the point
     */
    public int getY() {
        return this.y;
    }
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
