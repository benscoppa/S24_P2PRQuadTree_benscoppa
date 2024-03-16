
/**
 * This class handles Internal nodes of the QuadTree. In overrides the methods
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
public class InternalNode<K extends Comparable<? super K>, V>
    implements QuadNode<K, V> {

    // QuadNodes that represent the four quadrants and children of the internal
    // node.
    private QuadNode<K, V> nw, ne, sw, se;

    /**
     * Creates an internal node and initializes its children to empty nodes.
     * These empty nodes are really just the same flyweight node.
     */
    public InternalNode() {
        nw = ne = sw = se = EmptyNode.getInstance();
    }


    /**
     * When insert is called on an internal node the node finds its child which
     * contains the point in the KVPair. It recursivly calls the
     * 
     * @param it
     *            the KVPair to be inserted
     * @param params
     *            object that stores the parameters of of the region
     */
    @Override
    public QuadNode<K, V> insert(KVPair<K, V> it, Params params) {
        
        int topLeftX = params.getX();
        int topLeftY = params.getY();
        int regionSize = params.getSize();
        
        int newRegionSize = regionSize/2;
        
        

        return null;
    }

}
