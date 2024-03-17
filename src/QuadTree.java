import java.util.LinkedList;

/**
 * This class holds QuadTree class which handles the head of the QuadTree and
 * TODO finish JavaDoc
 * 
 * @author Ben Scoppa
 * 
 * @version 2024-03-15
 * @param <String>
 *            The name of the point
 * @param <Point>
 *            The actual point
 */
public class QuadTree {

    // the root of the QuadTree
    private QuadNode root;

    // root level of the QuadTree
    private static final int ROOT_LEVEL = 0;

    /**
     * Initializes the root of the tree point to the flynode which is an empty
     * node
     */
    public QuadTree() {
        root = EmptyNode.getInstance();
    }

    // world box paramters
    private static final int WORLD_TOP_LEFT_Y = 0;
    private static final int WORLD_TOP_LEFT_X = 0;
    private static final int WORLD_SIZE = 1024;

    // paramter object that stores the world box parameters
    private Params worldParams = new Params(WORLD_TOP_LEFT_Y, WORLD_TOP_LEFT_X,
        WORLD_SIZE);

    /**
     * Calls the insert method on the QuadTree heaad node.
     * 
     * @param it
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, Point> it) {

        root = root.insert(it, worldParams);
    }


    /**
     * Calls the remove method on the QuadTree head node.
     * 
     * @param pt
     *            the point to be removed
     * @param name
     *            the name of the point to be removed if needed
     *            "0" as the name results in not using a name
     *            to remove and removing by value
     * 
     * @return the name of the point that was removed
     */
    public String remove(Point pt, String name) {

        RemoveResult removed = root.remove(pt, worldParams, name);
        root = removed.getUpdatedNode();
        String removedPt = removed.getRemovedPointName();
        return removedPt;
    }


    /**
     * Calls the dump method on the QuadTree heaad node.
     */
    public void dump() {

        System.out.printf("QuadTree dump:\n");

        int nodeCount = 0;
        nodeCount = root.dump(ROOT_LEVEL, worldParams);
        System.out.printf("%d quadtree nodes printed\n", nodeCount);
    }
    
    
    /**
     * Calls the duplicates method on the QuadTree heaad node.
     */
    public void duplicates() {
        
        System.out.printf("Duplicate points:\n");
        root.duplicates();
    }
    
    public LinkedList<KVPair<String, Point>> regionSearch(Rectangle searchRegion) {
        
        LinkedList<KVPair<String, Point>> pointsInRegion = root.regionSearch(searchRegion, worldParams);
        return pointsInRegion;
    }

}
