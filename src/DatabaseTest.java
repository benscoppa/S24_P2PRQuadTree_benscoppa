
import org.junit.Test;
import org.junit.Before;
import student.TestCase;

/**
 * This class tests the methods of Database class
 * 
 * @author Ben Scoppa
 * 
 * @version 2024-02-18
 */

public class DatabaseTest extends TestCase {

    private Database data;

    private Rectangle invalidRec;
    private Rectangle validRec;

    private KVPair<String, Rectangle> invalidPair;
    private KVPair<String, Rectangle> validPair;

    /***
     * Sets up Rectangles, KVPairs and ArrayList to be
     * used for testing
     */
    @Before
    public void setUp() {

        data = new Database();

        invalidRec = new Rectangle(-1, -1, -1, -1);
        validRec = new Rectangle(1, 1, 1, 1);

        invalidPair = new KVPair<>("Invalid", invalidRec);
        validPair = new KVPair<>("Valid", validRec);
    }


    /***
     * Test the insert method of the database class
     */
    @Test
    public void testInsert() {

        data.insert(validPair);

        // sytem output
        String output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains(String.format("Rectangle inserted: %s\n",
            validPair.toString())));

        data.insert(invalidPair);

        // sytem output
        output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains(String.format("Rectangle rejected: %s\n",
            invalidPair.toString())));
    }


    /***
     * Test the remove method of the database class
     */
    @Test
    public void testRemove() {

        // remove an name that is in skiplist
        data.insert(validPair);
        data.remove("Valid");

        // sytem output
        String output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains(String.format("Rectangle removed: %s\n",
            validPair.toString())));

        // remove name not in skiplist
        data.remove("Valid");

        // sytem output
        output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains("Rectangle not found: (Valid)"));
    }

}
