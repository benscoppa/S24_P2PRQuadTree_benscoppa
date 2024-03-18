import java.util.LinkedList;

/**
 * This class holds QuadTree class which handles the root of the QuadTree
 * calling all the QuadNode methods on the root and keeping track of the root.
 * 
 * @author Ben Scoppa
 * 
 * @version 2024-03-15
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
     * Calls the duplicates method on the QuadTree head node.
     */
    public void duplicates() {

        System.out.printf("Duplicate points:\n");
        root.duplicates();
    }


    /**
     * Calls the regionSearch method on the QuadTree head node.
     * 
     * * @param searchRegion
     * a rectangle object that contains the region to be saerched
     */
    public void regionSearch(Rectangle searchRegion) {

        System.out.printf("Points intersecting region %s:\n", searchRegion
            .toString());

        RegionSearchResult searchResult = root.regionSearch(searchRegion,
            worldParams);

        LinkedList<KVPair<String, Point>> pointsInRegion = searchResult
            .getRegionPoints();

        int nodesVisited = searchResult.getVisitedNodes();

        if (pointsInRegion.isEmpty()) {
            System.out.printf("%d quadtree nodes visited\n", nodesVisited);
            return;
        }

        // iterate through points in the region
        for (KVPair<String, Point> currentPair : pointsInRegion) {
            System.out.printf("Point found: %s\n", currentPair.toString());
        }

        System.out.printf("%d quadtree nodes visited\n", nodesVisited);

    }

}
