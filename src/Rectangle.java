
/**
 * This class holds the coordinates and dimensions of a rectangle and methods to
 * check if it intersects or has the same coordinates as an other rectangle.
 * 
 * @author CS Staff
 * @author Ben Scoppa
 * 
 * @version 2024-02-15
 */
public class Rectangle {
    // the x coordinate of the rectangle
    private int xCoordinate;
    // the y coordinate of the rectangle
    private int yCoordinate;
    // the distance from the x coordinate the rectangle spans
    private int width;
    // the distance from the y coordinate the rectangle spans
    private int height;

    /**
     * Creates an object with the values to the parameters given in the
     * xCoordinate, yCoordinate, width, height
     * 
     * @param x
     *            x-coordinate of the rectangle
     * @param y
     *            y-coordinate of the rectangle
     * @param w
     *            width of the rectangle
     * @param h
     *            height of the rectangle
     */
    public Rectangle(int x, int y, int w, int h) {
        xCoordinate = x;
        yCoordinate = y;
        width = w;
        height = h;
    }


    /**
     * Getter for the x coordinate
     *
     * @return the x coordinate
     */
    public int getxCoordinate() {
        return xCoordinate;
    }


    /**
     * Getter for the y coordinate
     *
     * @return the y coordinate
     */
    public int getyCoordinate() {
        return yCoordinate;
    }


    /**
     * Getter for the width
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }


    /**
     * Getter for the height
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }


    /**
     * Checks if the invoking rectangle intersects with rec.
     * 
     * @param r2
     *            Rectangle parameter
     * @return true if the rectangle intersects with rec, false if not
     */
    public boolean intersect(Rectangle r2) {
        
        // check that r2 is not null
        if (r2 == null) {
            return false;
        }
        
        // check if the rectangle is right of r2 
        if (xCoordinate >= r2.xCoordinate + r2.width) {
            return false;
        }
        
        // check if the rectangle is left of r2
        if (xCoordinate + width <= r2.xCoordinate) {
            return false;
        }
        
        // check if the rectangle is below r2
        if (yCoordinate >= r2.yCoordinate + r2.height) {
            return false;
        }
        
        // check if the rectangle is above r2
        if (yCoordinate + height <= r2.yCoordinate) {
            return false;
        }
            
        // otherwise rectange intersects
        return true;

    }


    /**
     * Checks, if the invoking rectangle has the same coordinates as rec.
     * 
     * @param rec
     *            the rectangle parameter
     * @return true if the rectangle has the same coordinates as rec, false if
     *         not
     */
    public boolean equals(Object rec) {
        
        // if rectangle is compared to itself they are equal
        if (this == rec) {
            return true;
        }
        
        // make sure rec is a rectangle class object
        if (!(rec instanceof Rectangle)) {
            return false;
        }
        
        // compare the cordinates of this rectangle and rec
        Rectangle r2 = (Rectangle) rec;
        
        
        if (xCoordinate != r2.xCoordinate) {
            return false;
        }
        
        if (yCoordinate != r2.yCoordinate) {
            return false;
        }
        
        if (height != r2.height) {
            return false;
        }
        
        if (width != r2.width) {
            return false;
        }
        
        // if both cordinates are the same return true
        return true;
    }


    /**
     * Outputs a human readable string with information about the rectangle
     * which includes the x and y coordinate and its height and width
     * 
     * @return a human readable string containing information about the
     *         rectangle
     */
    public String toString() {
        
        return String.format("%d, %d, %d, %d", 
                             xCoordinate, yCoordinate, width, height);
    }


    /**
     * Checks if the rectangle has invalid parameters
     * 
     * @return true if the rectangle has invalid parameters, false if not
     */
    public boolean isInvalid() {
        
        // check that height and width are positive and  greater than zero
        if (height <= 0) {
            return true;
        }
        
        if (width <= 0) {
            return true;
        }
        
        // make sure the rectangle fits into the 1024 x 1024 
        // worldbox with (0,0) top left
        if (xCoordinate < 0) {
            return true;
        }
        
        if (yCoordinate < 0) {
            return true;
        }
        
        if (xCoordinate + width > 1024) {
            return true;
        }
        
        if (yCoordinate + height > 1024) {
            return true;
        }
        
        // otherwise the retangle is valid
        return false;
    }
}
