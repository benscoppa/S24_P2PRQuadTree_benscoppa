import java.util.LinkedList;

/**
 * This class holds the base QuadNode interface which is inherited by the type
 * specific node classes. It contains an insert,
 * TODO finish javadoc
 * 
 * @author Ben Scoppa
 * 
 * @version 2024-03-15
 * @param <String>
 *            The name of the point
 * @param <Point>
 *            The actual point
 */
public interface QuadNode {

    /**
     * Inserts a KV pair into the QuadTree by being called recursivly on various
     * node types.
     * 
     * @param it
     *            the KVPair to be inserted
     * @param params
     *            object that stores the parameters of of the region
     * 
     * @return QuadNodes recursivly
     */
    QuadNode insert(KVPair<String, Point> it, Params params);


    /**
     * Dumps a string output describing the structure of the QuadTree
     * 
     * @param level
     *            the level of the QuadTree node
     * @param params
     *            object that stores the parameters of of the region
     * 
     * @return QuadNodes recursivly
     */
    int dump(int level, Params params);


    /**
     * Removes a point from the QuadTree
     * 
     * @param pt
     *            the point to remove
     * @param params
     *            object that stores the parameters of of the region
     * @param name
     *            the name of the point to be removed if needed
     *            "0" as the name results in not using a name
     *            to remove and removing by value
     * 
     * @return RemoveResult contains the updateed node, the name of removed
     *         node.
     */
    RemoveResult remove(Point pt, Params params, String name);


    /**
     * get the points from a child node returned as a linked list
     * 
     * @return linked list of nodes in the child
     */
    LinkedList<KVPair<String, Point>> getPoints();


    /**
     * Prints all the duplicate points in the QuadTree
     * 
     * @return itself recursivly
     */
    QuadNode duplicates();


    /**
     * Gets a list of all the points inside of a search region.
     * 
     * @param searchRegion
     *            a rectangle object that contains the region to search for
     *            points in within the QuadTree.
     * @param params
     *            object that stores the parameters of of the region
     * 
     * @return a linked list of all the points in the search region
     */
    LinkedList<KVPair<String, Point>> regionSearch(Rectangle searchRegion, Params params);

}
