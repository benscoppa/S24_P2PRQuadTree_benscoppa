
import java.util.LinkedList;

/**
 * This class handles Leaf nodes of the QuadTree. In overrides the methods
 * insert,
 * TODO finish javaDoc
 * 
 * @author Ben Scoppa
 * 
 * @version 2024-03-15
 * @param <String>
 *            The name of the point
 * @param <Point>
 *            The actual point
 */
public class LeafNode implements QuadNode {

    // the skiplist for storeing points and an iterator
    private LinkedList<KVPair<String, Point>> region;

    /**
     * Initializes a skiplist for storing points in the region 
     */
    public LeafNode() {
        region = new LinkedList<>();
    }


    /**
     * Adds another KVPair to the region if there is room. Otherwise cretates a
     * new Internal node.
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

        // insert the point into the region
        region.add(it);

        boolean sameElements = true;

        // if there are mpre than 3 points in the region
        if (region.size() > 3) {
            // if not all of the points are equal create new internal node
            for (KVPair<String, Point> currentPair : region) {
                if (!currentPair.equals(it)) {
                    sameElements = false;
                    break;
                }
            }
            if (!sameElements) {
                // cretae a new internal node
                InternalNode newInternalNode = new InternalNode();

                // insert each point from the region into the new internal node
                for (KVPair<String, Point> KVPair : region) {
                    newInternalNode.insert(KVPair, params);
                }

                return newInternalNode;
            }
        }

        // otherwise return this leaf node
        return this;
    }


    /**
     * When dump is called on a leaf node it outputs information
     * about itself and the points stored inside it.
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
        System.out.printf("%sNode at %d, %d, %d:\n", indent, topLeftX, topLeftY,
            regionSize);

        // print each of points stored in this leaf node
        for (KVPair<String, Point> currentPair : region) {
            System.out.printf("%s(%s, %s)%n", indent, currentPair.getKey(),
                currentPair.getValue().toString());
        }

        return 1;
    }

}
