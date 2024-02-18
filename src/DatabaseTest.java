
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
    private Rectangle validRec2;

    private KVPair<String, Rectangle> invalidPair;
    private KVPair<String, Rectangle> validPair;
    private KVPair<String, Rectangle> validPair2;

    /***
     * Sets up Rectangles, KVPairs and ArrayList to be
     * used for testing
     */
    @Before
    public void setUp() {

        data = new Database();

        invalidRec = new Rectangle(-1, -1, -1, -1);
        validRec = new Rectangle(1, 1, 1, 1);
        validRec2 = new Rectangle(2, 2, 2, 2);

        invalidPair = new KVPair<>("Invalid", invalidRec);
        validPair = new KVPair<>("Valid", validRec);
        validPair2 = new KVPair<>("Valid", validRec2);
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


    /***
     * Test the remove by parameters method of the database class
     */
    @Test
    public void testRemoveByValue() {

        // remove rectangle parameters in skiplist
        data.insert(validPair);
        data.remove(1, 1, 1, 1);

        // sytem output
        String output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains(String.format("Rectangle removed: %s\n",
            validPair.toString())));

        // remove rectangle parameters not in skiplist
        data.remove(1, 1, 1, 1);

        // sytem output
        output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains("Rectangle not found: (1, 1, 1, 1)"));
        
        // test removing invalid rectangle
        data.remove(-1, -1, -1, -1);
        
        output = systemOut().getHistory();
        
        // verify system output
        assertTrue(output.contains("Rectangle Rejected: (-1, -1, -1, -1)\n"));
    }


    /***
     * Test the remove by parameters method of the database class
     */
    @Test
    public void testSearch() {

        // search for keys that are in the list
        data.insert(validPair);
        data.insert(validPair2);
        data.search("Valid");

        // sytem output
        String output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains("Rectangles found matching \"Valid\":\n"));

        assertTrue(output.contains(String.format("%s\n", validPair
            .toString())));

        assertTrue(output.contains(String.format("%s\n", validPair2
            .toString())));

        // search for key that is not in the list
        data.search("Invalid");

        // sytem output
        output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains("Rectangle not found: Invalid"));

    }
    
    /***
     * Test the remove by parameters method of the database class
     */
    @Test
    public void testDump() {
        
        // insert rectangle and call dump
        data.insert(validPair);
        data.insert(validPair2);
        data.dump();
        
        // sytem output
        String output = systemOut().getHistory();
        
        // verify system output
        assertTrue(output.contains("SkipList dump:\n"));
        assertTrue(output.contains("Value (null)\n"));
        assertTrue(output.contains(String.format("Value %s\n", validPair.toString())));
        assertTrue(output.contains(String.format("Value %s\n", validPair2.toString())));
        assertTrue(output.contains("SkipList size is: 2\n"));
        
    }

}
