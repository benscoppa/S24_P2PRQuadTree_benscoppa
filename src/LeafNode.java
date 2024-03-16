
/**
 * This class handles Leaf nodes of the QuadTree. In overrides the methods
 * insert,
 * TODO finish javaDoc
 * 
 * @author Ben Scoppa
 * 
 * @version 2024-03-15
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class LeafNode<K extends Comparable<? super K>, V>
    implements QuadNode<K, V> {

    private SkipList<K, V> region;

    /**
     * Initializes a skiplist for storing points in the region and adds the
     * first KVPair
     */
    public LeafNode(KVPair<K, V> it) {
        region = new SkipList<K, V>();
        region.insert(it);
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
    public QuadNode<K, V> insert(KVPair<K, V> it, Params params) {
        
        // insert the point into the region
        region.insert(it);
        
        // if there are mpre than 3 points in the region
        if (region.size() > 3) {
            // if not all of the points are equal create new internal node
            if (region.search(it.getKey()).size() != region.size()) {
                // cretae a new internal node
                InternalNode<K, V> newInternalNode = new InternalNode<>();
                
                // insert each point from the region into the new internal node
                for (KVPair<K, V> KVPair : region) { 
                    newInternalNode.insert(KVPair, params);
                }
                
                return newInternalNode;
            }
        }
       
        // otherwise return this leaf node
        return this;
    }

}
