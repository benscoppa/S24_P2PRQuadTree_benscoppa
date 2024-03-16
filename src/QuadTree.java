
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
     * Initializes the root of the tree point to the flynode which is an empty node
     */
    public QuadTree() {
        root = EmptyNode.getInstance();
    }

    // world box paramters
    private static final int WORLD_TOP_LEFT_Y = 0;
    private static final int WORLD_TOP_LEFT_X = 0;
    private static final int WORLD_SIZE = 1024;

    // paramter object that stores the world box parameters
    Params worldParams = new Params(WORLD_TOP_LEFT_Y, WORLD_TOP_LEFT_X,
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
     * Calls the dump method on the QuadTree heaad node.
     */
    public void dump() {
        
        System.out.printf("QuadTree dump:\n");
        
        int nodeCount = 0;
        nodeCount = root.dump(ROOT_LEVEL, worldParams);
        System.out.printf("%d quadtree nodes printed", nodeCount);
    }
}
