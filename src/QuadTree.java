import java.util.Iterator;

/**
 * This class holds QuadTree class which handles the head of the QuadTree and
 * TODO finish JavaDoc
 * 
 * @author Ben Scoppa
 * 
 * @version 2024-03-15
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class QuadTree<K extends Comparable<? super K>, V>
    implements Iterable<KVPair<K, V>> {

    private int level;
    // TODO initialize head

    /**
     * Initializes the fields head level
     */
    public QuadTree() {
        level = 0;
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
     * @param worldParams
     *            object that stores the parameters of of the world box
     */
    public void insert(KVPair<K, V> it, Params worldParams) {

    }


    @Override
    public Iterator<KVPair<K, V>> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

}
