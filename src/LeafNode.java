
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
                if (!(currentPair.getValue().equals(it.getValue()))) {
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


    /**
     * When the remove method is called on a leaf node the point is remove from
     * the node if it is found.
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

        String ptName = "Not Found";

        // remove the point making sure the name matches as well
        if (!name.equals("0")) {
            for (KVPair<String, Point> currentPair : region) {
                if (currentPair.getValue().equals(pt)) {
                    ptName = currentPair.getKey();
                    // double check the name also
                    if (name.equals(ptName)) {
                        // remove the pair from the point
                        region.remove(currentPair);
                        break;
                    }
                }
            }
        }
        else {
            // try to find the point base on value alone
            for (KVPair<String, Point> currentPair : region) {
                if (currentPair.getValue().equals(pt)) {
                    ptName = currentPair.getKey();
                    // remove the pair from the point
                    region.remove(currentPair);
                    break;
                }
            }
        }

        // if the region is now empty replace the leaf node with an empty node
        if (region.isEmpty()) {
            QuadNode newEmptyNode = EmptyNode.getInstance();
            // return the new empty node, removed point name and empty list of
            // points
            RemoveResult result = new RemoveResult(newEmptyNode, ptName);
            return result;
        }

        // otherwise return this leaf node, the removed point name and the
        // points in this region
        RemoveResult result = new RemoveResult(this, ptName);
        return result;
    }


    /**
     * get the points contained in the leaf node.
     * 
     * @return the points contained within this region
     */
    @Override
    public LinkedList<KVPair<String, Point>> getPoints() {

        return region;
    }


    /**
     * When duplicates is called on a leaf node it checks for duplicates in the
     * leaf and prints them to console.
     * 
     * @return itself recursivly
     */
    @Override
    public QuadNode duplicates() {
        // stores the dupliacte points
        LinkedList<Point> printedDuplicates = new LinkedList<>();

        // outer loop that getas a pair from the region
        for (KVPair<String, Point> currentPair : region) {
            
            // make sure the duplicate wasnt already printed
            if (printedDuplicates.contains(currentPair.getValue())) {
                continue;
            }
            
            // compare this pair to every other point in the region
            for (KVPair<String, Point> comparePair : region) {
                // if the values are duplicate but it is not itself
                if (currentPair.getValue().equals(comparePair.getValue())
                    && !currentPair.equals(comparePair)) {
                    
                    // add to list of duplicate points
                    printedDuplicates.add(currentPair.getValue());

                    // print the dupliacte to console
                    System.out.printf("%s\n", currentPair.getValue()
                        .toString());
                    break;
                }
            }
        }

        // return itself recursivly
        return this;
    }

}
