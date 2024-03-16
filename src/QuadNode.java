
/**
 * This class holds the base QuadNode interface which is inherited by the type
 * specific node classes. It contains an insert,
 * TODO finish javadoc
 * 
 * @author Ben Scoppa
 * 
 * @version 2024-03-15
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public interface QuadNode<K extends Comparable<? super K>, V> {
    
    /**
     * Inserts a KV pair into the QuadTree by being called recursivly on various
     * node types.
     * 
     * @param it
     *            the KVPair to be inserted
     * @param params
     *            object that stores the parameters of of the region
     * @return 
     */
    QuadNode<K, V> insert(KVPair<K, V> it, Params params);

}