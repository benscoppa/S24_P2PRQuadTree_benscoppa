/**
 * This class implements an object that stores region parameters. It also
 * includes methods to get each parameter.
 * 
 * @author Ben Scoppa
 * 
 * @version 2024-03-15
 */
public class Params {
    private int topLeftX;
    private int topLeftY;
    private int regionSize;

    /**
     * Initializes the parameter values of the object
     * 
     * @param x
     *            the top left x cordinate of the region
     * @param y
     *            the top left y cordiante of the region
     * @param size
     *            the size of the region
     */
    public Params(int x, int y, int size) {
        topLeftX = x;
        topLeftY = y;
        regionSize = size;
    }


    /**
     * Get the top left x coordinate.
     * 
     * @return the top left x coordinate
     */
    public int getX() {
        return topLeftX;
    }


    /**
     * Get the top left y coordinate.
     * 
     * @return the top left y coordinate
     */
    public int getY() {
        return topLeftY;
    }


    /**
     * Get the size of the region.
     * 
     * @return the size of the region
     */
    public int getSize() {
        return regionSize;
    }
}
