import java.util.LinkedList;

/**
 * This class handles Empty nodes of the QuadTree. It overrides its methods.
 * 
 * @author Ben Scoppa
 * 
 * @version 2024-03-15
 */
public class EmptyNode implements QuadNode {

    private static final EmptyNode THE_FLYNODE = new EmptyNode();

    /**
     * Empty node cannot be initialized outside of the one flynode
     */
    private EmptyNode() {

    }


    /**
     * Gets a pointer instance to the flynode.
     * 
     * @return The Flynode
     */
    public static EmptyNode getInstance() {

        return THE_FLYNODE;
    }


    /**
     * Creates a new Leaf node passing in the KVPair
     * 
     * @param it
     *            the KVPair to be inserted
     * @param params
     *            object that stores the parameters of of the region
     * 
     * @return the new leaf node
     */
    @Override
    public QuadNode insert(KVPair<String, Point> it, Params params) {

        LeafNode newLeafNode = new LeafNode();
        return newLeafNode.insert(it, params);
    }


    /**
     * When dump is called on an empty node the node there is no output it just
     * returns itself
     * 
     * @param level
     *            the current level of the QuadNode
     * @param params
     *            object that stores the parameters of of the region
     * 
     * @return return one since a node was visited
     */
    @Override
    public int dump(int level, Params params) {

        // get old region parameters
        int topLeftX = params.getX();
        int topLeftY = params.getY();
        int regionSize = params.getSize();

        // create the indent base on the level of the node
        String indent = "  ".repeat(level);

        // print out information obout this internal node
        System.out.printf("%sNode at %d, %d, %d: Empty\n", indent, topLeftX,
            topLeftY, regionSize);

        return 1;
    }


    /**
     * When the remove method is called on an empty node it means point was not
     * found and cannot be removed.
     * 
     * @param pt
     *            the point to remove
     * @param params
     *            object that stores the parameters of of the region
     * 
     * @return QuadNodes recursivly
     */
    @Override
    public RemoveResult remove(Point pt, Params params, String name) {

        RemoveResult result = new RemoveResult(this, "0");
        return result;
    }


    /**
     * get the points contained in the empty node with is always none.
     * 
     * @return empty linked list since there are no points
     */
    @Override
    public LinkedList<KVPair<String, Point>> getPoints() {

        LinkedList<KVPair<String, Point>> empty = new LinkedList<>();
        return empty;
    }


    /**
     * When duplicates is called on an empty node it just returns itself since
     * there are no points.
     * 
     * @return itself recursivly
     */
    @Override
    public QuadNode duplicates() {

        return this;
    }


    /**
     * When regionSearch is called on an empty returns an empty linked list
     * since there are no points in the empty node.
     * 
     * @param searchRegion
     *            a rectangle object that contains the region to search for
     *            points in within the QuadTree.
     * @param params
     *            object that stores the parameters of of the region
     * 
     * @return regionSearchResult an empty linked list and one node visited
     */
    @Override
    public RegionSearchResult regionSearch(
        Rectangle searchRegion,
        Params params) {

        LinkedList<KVPair<String, Point>> empty = new LinkedList<>();

        int node = 1;

        RegionSearchResult result = new RegionSearchResult(empty, node);

        return result;
    }
}
