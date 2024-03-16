
/**
 * This class handles Empty nodes of the QuadTree. In overrides the methods
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
public class EmptyNode<K extends Comparable<? super K>, V>
    implements QuadNode<K, V> {

    private static final EmptyNode<?, ?> THE_FLYNODE = new EmptyNode<>();

    /**
     * Empty node cannot be initialized
     */
    private EmptyNode() {

    }


    /**
     * Gets a pointer instance to the flynode.
     * 
     * @return The Flynode
     */
    @SuppressWarnings("unchecked")
    public static <K extends Comparable<? super K>, V> EmptyNode<K, V> getInstance() {

        return (EmptyNode<K, V>)THE_FLYNODE;
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
    public QuadNode<K, V> insert(KVPair<K, V> it, Params params) {

        LeafNode<K, V> newLeafNode = new LeafNode<>(it);
        return newLeafNode;
    }
}
