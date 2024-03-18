import java.util.LinkedList;

/**
 * This class is a return object that allows both a LinkedList and a integer to
 * be returned together recursivly.
 * 
 * @author Ben Scoppa
 * 
 * @version 2024-03-17
 */
public class RegionSearchResult {

    private LinkedList<KVPair<String, Point>> regionPoints;
    private int visitedNodes;

    /**
     * Creates an object with the values to the parameters given in the
     * regionPoints, visitedNodes
     * 
     * @param regionPoints
     *            the points in a region also in search region
     * @param visitedNodes
     *            the nodes visited by the regionSearch method
     */
    public RegionSearchResult(
        LinkedList<KVPair<String, Point>> regionPoints,
        int visitedNodes) {
        this.regionPoints = regionPoints;
        this.visitedNodes = visitedNodes;
    }


    /**
     * Getter for the regionPoints
     * 
     * @return the regionPoints linked list
     */
    public LinkedList<KVPair<String, Point>> getRegionPoints() {
        return regionPoints;
    }


    /**
     * Getter for the visitedNodes
     * 
     * @return the numer of visted nodes
     */
    public int getVisitedNodes() {
        return visitedNodes;
    }
}
