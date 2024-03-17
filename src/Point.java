/**
 * This class holds the coordinates and dimensions of a point and methods to
 * check if the point is invalid or if points are equal
 * 
 * @author Ben Scoppa
 * 
 * @version 2024-03-15
 */
public class Point {
    // the x coordinate of the point
    private int xCoordinate;
    // the y coordinate of the point
    private int yCoordinate;

    /**
     * Creates an object with the values to the parameters given in the
     * xCoordinate, yCoordinate
     * 
     * @param x
     *            x-coordinate of the rectangle
     * @param y
     *            y-coordinate of the rectangle
     */
    public Point(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
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
     * Checks, if the invoking point has the same corrdinates as the input
     * point.
     * 
     * @param pt
     *            the point parameter
     * @return true if the point has the same coordinates as pt, false if
     *         not
     */
    public boolean equals(Object pt) {

        // if point is compared to itself they are equal
        if (this == pt) {
            return true;
        }

        // make sure pt is a point class object
        if (!(pt instanceof Point)) {
            return false;
        }

        // compare the cordinates of this rectangle and rec
        Point pt2 = (Point)pt;

        if (xCoordinate != pt2.xCoordinate) {
            return false;
        }

        if (yCoordinate != pt2.yCoordinate) {
            return false;
        }

        // if both cordinates are the same return true
        return true;
    }


    /**
     * Outputs a human readable string with information about the point
     * which includes the x and y coordinate
     * 
     * @return a human readable string containing information about the
     *         point
     */
    public String toString() {

        return String.format("%d, %d", xCoordinate, yCoordinate);
    }


    /**
     * Checks if the point has invalid parameters
     * 
     * @return true if the point has invalid parameters, false if not
     */
    public boolean isInvalid() {

        // make sure the point fits into the 1024 x 1024
        // worldbox with (0,0) top left
        if (xCoordinate < 0) {
            return true;
        }

        if (yCoordinate < 0) {
            return true;
        }

        if (xCoordinate > 1024) {
            return true;
        }

        if (yCoordinate > 1024) {
            return true;
        }

        // otherwise the point is valid
        return false;
    }


    /**
     * Checks if the point is inside of a region
     * 
     * @param x
     *            top left x cordinate of region
     * @param y
     *            top left y cordinate of region
     * @param size
     *            the size of the region
     * 
     * @return true if the point is inside the region, false if not
     */
    public boolean inRegion(int x, int y, int size) {

        // check all sides of the region to see if the point is in the region
        if (xCoordinate < x) {
            return false;
        }

        else if (yCoordinate < y) {
            return false;
        }

        else if (xCoordinate > x + size) {
            return false;
        }

        return !(yCoordinate > y + size);
    }
}
