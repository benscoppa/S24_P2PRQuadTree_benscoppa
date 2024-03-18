import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList and QuadTree. The responsibility of this class is to further
 * interpret variations of commands and do some error checking of those
 * commands. This class further interpreting the command means that the two
 * types of remove will be overloaded methods for if we are removing by name or
 * by coordinates. Many of these methods will simply call the appropriate
 * version of the SkipList and/or QuadTree method after some preparation.
 * 
 * @author CS Staff
 * @author Ben Scoppa
 * 
 * @version 2024-03-16
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the point and then
    // a point object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, Point> list;
    private QuadTree tree;

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, Point>();
        tree = new QuadTree();
    }


    /**
     * Inserts the KVPair in the SkipList and QuadTree if the Point has valid
     * coordinates and dimensions, that is that the coordinates are non-negative
     * and inside of the worldbox (1024, 1024) This insert will add the KVPair
     * specified into the sorted SkipList appropriately. It also adds the KVPair
     * to the QuadTree.
     * 
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, Point> pair) {

        // get the point object
        Point pt = pair.getValue();
        // check rectangle validity and print error message if needed
        if (pt.isInvalid()) {
            System.out.printf("Point rejected: %s%n", pair.toString());
            return;
        }

        // add rectangle to the list and quadtree if valid
        list.insert(pair);
        tree.insert(pair);
        System.out.printf("Point inserted: %s%n", pair.toString());
    }


    /**
     * Removes a Point with the name "name" if available. If not an error
     * message is printed to the console. Removed from both the skiplist and
     * and the quadtree. Point is first removed from the skiplist in order
     * to get the point value to use for removinf from the quadtree.
     * 
     * @param name
     *            the name of the rectangle to be removed
     */
    public void remove(String name) {

        // attempt to remove the rectangle from list
        KVPair<String, Point> removed = list.remove(name);

        // check if the remove failed and print error message if needed
        if (removed == null) {
            System.out.printf("Point not removed: %s%n", name);
            return;
        }
        // remove the point from the quadtree also verifying the name
        tree.remove(removed.getValue(), name);

        // print success message
        System.out.printf("Point removed: %s%n", removed.toString());
    }


    /**
     * Removes a point with the specified coordinates if available. If not
     * an error message is printed to the console. Remove the point from
     * both the skiplist and quadtree. Point is removed from the quadtree
     * first in order to get the name of the point to use for removing from
     * the skiplist.
     * 
     * @param x
     *            x-coordinate of the point to be removed
     * @param y
     *            x-coordinate of the point to be removed
     */
    public void remove(int x, int y) {

        // make a point object with the given parameters
        Point pt = new Point(x, y);

        // print error message if point invaild
        if (pt.isInvalid()) {

            System.out.printf("Point rejected: (%d, %d)\n", x, y);
            return;
        }

        // remove the point from the quad tree by value first and getting the
        // name "0" denotes the fact there is no name associated with the point
        String name = tree.remove(pt, "0");

        // check if the remove failed and print error message if needed
        if (name.equals("0")) {
            System.out.printf("Point not found: (%d, %d)\n", x, y);
            return;
        }

        // remove the point from the skiplist also
        KVPair<String, Point> removed = list.removeByValue(pt, name);

        // print success message
        System.out.printf("Point removed: %s\n", removed.toString());
    }


    /**
     * Displays all the points inside the specified region. The point
     * must be inside the area that is created by the region rectangle,
     * meaning, points that only touch a side or corner of the region
     * specified will be said to be in the region.
     * 
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public void regionsearch(int x, int y, int w, int h) {

        // check for valid height and with and print error message if needed
        if (w <= 0 || h <= 0) {
            System.out.printf("rectangle rejected: (%d, %d, %d, %d)\n", x, y, w,
                h);
            return;
        }

        // rectangle that the demensions of the search region
        Rectangle regionRec = new Rectangle(x, y, w, h);

        // call regionsearch on the QuadTree
        tree.regionSearch(regionRec);
    }


    /**
     * Prints out all of the duplicate points within the QuadTree.
     */
    public void duplicates() {

        tree.duplicates();
    }


    /**
     * Prints out all the points with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {

        // get a list of keys that match the search name
        ArrayList<KVPair<String, Point>> matches = list.search(name);

        // print error message if no matching keys found
        if (matches.isEmpty()) {
            System.out.printf("Point not found: %s\n", name);
            return;
        }

        // print out all the rectangles matching the search key name
        for (KVPair<String, Point> point : matches) {
            System.out.printf("Found: %s\n", point.toString());
        }
    }


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList. Also prints a dump of the quadtree
     * which prints node information as well and is formatted by node depth.
     */
    public void dump() {

        // delegate dump to SkipList and the QuadTree
        list.dump();
        tree.dump();
    }

}
