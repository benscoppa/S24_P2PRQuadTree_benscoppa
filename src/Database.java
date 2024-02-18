import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * Also note that the Database class will have a clearer role in Project2,
 * where we will have two data structures. The Database class will then
 * determine which command should be directed to which data structure.
 * 
 * @author CS Staff
 * @author Ben Scoppa
 * 
 * @version 2024-02-16
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, Rectangle> list;

    // This is an Iterator object over the SkipList to loop through it from
    // outside the class.
    // You will need to define an extra Iterator for the intersections method.
    private Iterator<KVPair<String, Rectangle>> itr1;

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, Rectangle>();
    }


    /**
     * Inserts the KVPair in the SkipList if the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will
     * add the KVPair specified into the sorted SkipList appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, Rectangle> pair) {
        // Delegates the decision mostly to SkipList, only
        // writing the correct message to the console from
        // that
        Rectangle rec = pair.getValue();
        // check rectangle validity and print error message if needed
        if (rec.isInvalid()) {
            System.out.printf("Rectangle rejected: %s\n", pair.toString());
            return;
        }

        // add rectangle to the list
        list.insert(pair);
        System.out.printf("Rectangle inserted: %s\n", pair.toString());
    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     * 
     * @param name
     *            the name of the rectangle to be removed
     */
    public void remove(String name) {

        // attempt to remove the rectangle from list
        KVPair<String, Rectangle> removed = list.remove(name);

        // check if the remove failed and print error message if needed
        if (removed == null) {
            System.out.printf("Rectangle not found: %s\n", name);
            return;
        }

        // print success message
        System.out.printf("Rectangle removed: %s\n", removed.toString());
    }


    /**
     * Removes a rectangle with the specified coordinates if available. If not
     * an error message is printed to the console.
     * 
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     * @param w
     *            width of the rectangle to be removed
     * @param h
     *            height of the rectangle to be removed
     */
    public void remove(int x, int y, int w, int h) {

        // make a rectange object with the given parameters
        Rectangle rec = new Rectangle(x, y, w, h);

        // print error message if rectangle invaild
        if (rec.isInvalid()) {

            System.out.printf("Rectangle Rejected: (%d, %d, %d, %d)\n", x, y, w,
                h);
            return;
        }

        // attempt to remove the rectangle from list
        KVPair<String, Rectangle> removed = list.removeByValue(rec);

        // check if the remove failed and print error message if needed
        if (removed == null) {
            System.out.printf("Rectangle not found: (%d, %d, %d, %d)\n", x, y,
                w, h);
            return;
        }

        // print success message
        System.out.printf("Rectangle removed: %s\n", removed.toString());
    }


    /**
     * Displays all the rectangles inside the specified region. The rectangle
     * must have some area inside the area that is created by the region,
     * meaning, Rectangles that only touch a side or corner of the region
     * specified will not be said to be in the region.
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

    }


    /**
     * Prints out all the rectangles that intersect each other. Note that
     * it is better not to implement an intersections method in the
     * SkipList class as the SkipList needs to be agnostic about the fact
     * that it is storing Rectangles.
     */
    public void intersections() {

    }


    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {

        // get a list of keys that match the search name
        ArrayList<KVPair<String, Rectangle>> matches = list.search(name);

        // print error message if no matching keys found
        if (matches.isEmpty()) {
            System.out.printf("Rectangle not found: %s", name);
            return;
        }

        // print out all the rectangles matching the search key name
        System.out.printf("Rectangles found matching \"a\":\n");
        for (KVPair<String, Rectangle> rec : matches) {
            System.out.printf("%s\n", rec.toString());
        }

    }


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList.
     */
    public void dump() {

        // delegate dump to SkipList
        list.dump();
    }

}
