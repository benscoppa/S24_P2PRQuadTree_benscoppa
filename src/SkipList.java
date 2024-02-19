import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import student.TestableRandom;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author CS Staff
 * 
 * @version 2024-01-22
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class SkipList<K extends Comparable<? super K>, V>
    implements Iterable<KVPair<K, V>> {
    private SkipNode head; // First element (Sentinel Node)
    private int size; // number of entries in the Skip List
    private Random rng;

    /**
     * Initializes the fields head, size and level
     */
    public SkipList() {
        head = new SkipNode(null, 1);
        size = 0;
        this.rng = new TestableRandom();
    }


    /**
     * returns a random level (using geometric distribution), minimum of 1
     * Ideally, you should call this method inside other methods
     * keep this method private. Since, we do not have any methods to call
     * this method at this time, we keep this publicly accessible and
     * 
     * @return level
     *         the random level generated
     */
    // testable.
    public int randomLevel() {
        int level = 1;
        while (rng.nextBoolean())
            level++;
        return level;
    }


    /**
     * Searches for the KVPair using the key which is a Comparable object.
     * 
     * @param key
     *            key to be searched for
     * 
     * @return null
     */
    public ArrayList<KVPair<K, V>> search(K key) {

        // create an array to search matches
        ArrayList<KVPair<K, V>> matches = new ArrayList<>();
        SkipNode searchNode = head;

        // traverse the skiplist to find the key
        for (int i = head.level; i >= 0; i--) {
            while (searchNode.forward[i] != null && searchNode.forward[i].pair
                .getKey().compareTo(key) < 0) {
                searchNode = searchNode.forward[i];
            }
        }

        // move to the next node
        searchNode = searchNode.forward[0];

        // add all of the matching keys to the matches array list
        while (searchNode != null && searchNode.pair.getKey().equals(key)) {
            matches.add(searchNode.pair);
            searchNode = searchNode.forward[0];
        }

        // return the array list of matches
        return matches;
    }


    /**
     * @return the size of the SkipList
     */
    public int size() {
        return size;
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by its lexicoragraphical order.
     * 
     * @param it
     *            the KVPair to be inserted
     */
    @SuppressWarnings("unchecked")
    public void insert(KVPair<K, V> it) {

        int newLevel = randomLevel();

        // adjust the head node level if needed
        if (newLevel > head.level) {
            adjustHead(newLevel);
        }

        // create a new update array
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, head.level + 1);

        // start search at the head node
        SkipNode searchNode = head;

        // Find insert position by comparing the key of it to those in the list
        for (int i = searchNode.level; i >= 0; i--) {
            while ((searchNode.forward[i] != null) && (searchNode.forward[i]
                .element().getKey().compareTo(it.getKey()) < 0)) {
                searchNode = searchNode.forward[i];
            }
            update[i] = searchNode;
        }

        // create a new skipnode for the KVPair
        SkipNode newNode = new SkipNode(it, newLevel);
        // insert the node into the list and update pointers
        for (int i = 0; i <= newLevel; i++) {
            newNode.forward[i] = update[i].forward[i]; // who newNode points to
            update[i].forward[i] = newNode; // who points to newNode
        }
        size++; // increment size
    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices than the head.
     * 
     * @param newLevel
     *            the number of levels to be added to head
     */
    @SuppressWarnings("unchecked")
    public void adjustHead(int newLevel) {

        // create new head node with more levels
        SkipNode temp = head;
        SkipNode newHead = new SkipNode(null, newLevel);

        // copy over the forward pointers from the old head to the new head
        // up to the level of the old head
        for (int i = 0; i <= temp.level; i++) {
            newHead.forward[i] = temp.forward[i];
        }

        // update head to be new head
        head = newHead;
    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     * 
     * @param key
     *            the KVPair key to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */
    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {

        // create a new update array
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, head.level + 1);

        // start the search at the head
        SkipNode searchNode = head;

        // find key to remove position by comparing the key of it to those in
        // the list and add to the upadte array
        for (int i = searchNode.level; i >= 0; i--) {
            while ((searchNode.forward[i] != null) && (searchNode.forward[i]
                .element().getKey().compareTo(key) < 0)) {
                searchNode = searchNode.forward[i];
            }
            update[i] = searchNode;
        }

        // check next node
        searchNode = searchNode.forward[0];
        if (searchNode != null && searchNode.pair.getKey().equals(key)) {
            // remove the node and update pointers
            for (int i = 0; i <= head.level; i++) {
                // make sure the pointer being overwritten
                // is the node to remove
                if (update[i].forward[i] == searchNode) {
                    update[i].forward[i] = searchNode.forward[i];
                }
            }
            size--; // deacrease size of skip list
            return searchNode.element(); // return removed KVPair
        }

        return null; // return null if invalid
    }


    /**
     * Removes a KVPair with the specified value.
     * 
     * @param val
     *            the value of the KVPair to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */
    @SuppressWarnings("unchecked")
    public KVPair<K, V> removeByValue(V val) {

        // create a new update array
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, head.level + 1);

        // start the search at the head
        SkipNode searchNode = head;

        // find value to remove position by comparing the key of it to those in
        // the list and add to the update array
        for (int i = searchNode.level; i >= 0; i--) {
            while ((searchNode.forward[i] != null) && (!searchNode.forward[i]
                .element().getValue().equals(val))) {
                searchNode = searchNode.forward[i];
            }
            update[i] = searchNode;
        }

        // check next node
        searchNode = searchNode.forward[0];
        if (searchNode != null) {
            // remove the node and update pointers
            for (int i = 0; i <= head.level; i++) {
                // make sure the pointer being overwritten
                // is the node to remove
                if (update[i].forward[i] == searchNode) {
                    update[i].forward[i] = searchNode.forward[i];
                }
            }
            size--; // deacrease size of skip list
            return searchNode.element(); // return removed KVPair
        }

        return null; // return null if invalid
    }


    /**
     * Prints out the SkipList in a human readable format to the console.
     */
    public void dump() {
        
        // header message
        System.out.printf("SkipList dump:%n");

        SkipNode current = head;

        // traverse the list from the head node
        while (current != null) {
            // print node depth and value to console
            if (current.pair == null) {
                System.out.printf("Node has depth %d, Value (null)%n",
                    current.level);
            }
            else {
                // print the node depth and value.
                System.out.printf("Node has depth %d, Value %s%n",
                    current.level, current.pair.toString());
            }
            // move to the next node at base level
            current = current.forward[0];
        }

        // print skip list size
        System.out.printf("SkipList size is: %d%n", size);
    }

    /**
     * This class implements a SkipNode for the SkipList data structure.
     * 
     * @author CS Staff
     * 
     * @version 2016-01-30
     */
    private class SkipNode {

        // the KVPair to hold
        private KVPair<K, V> pair;
        // An array of pointers to subsequent nodes
        private SkipNode[] forward;
        // the level of the node
        private int level;

        /**
         * Initializes the fields with the required KVPair and the number of
         * levels from the random level method in the SkipList.
         * 
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            pair = tempPair;
            forward = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,
                level + 1);
            this.level = level;
        }


        /**
         * Returns the KVPair stored in the SkipList.
         * 
         * @return the KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }

    }


    private class SkipListIterator implements Iterator<KVPair<K, V>> {
        private SkipNode current;

        public SkipListIterator() {
            current = head;
        }


        @Override
        public boolean hasNext() {
            
            return current.forward[0] != null;
        }


        @Override
        public KVPair<K, V> next() {
            
            KVPair<K, V> elem = current.forward[0].element();
            current = current.forward[0];
            return elem;
        }

    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        
        return new SkipListIterator();
    }

}
