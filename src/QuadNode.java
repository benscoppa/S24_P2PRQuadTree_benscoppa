
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

}
