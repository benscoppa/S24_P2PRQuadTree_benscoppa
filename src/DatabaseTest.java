
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

    // rectangles and pairs for testing methods that are in skiplist also
    private Point invalidPt;
    private Point validPt;
    private Point validPt2;

    private KVPair<String, Point> invalidPair;
    private KVPair<String, Point> validPair;
    private KVPair<String, Point> validPair2;

    // rectangles and pairs for testing searchRegion
    private Point inRegionPt1;
    private Point inRegionPt2;
    private Point notInRegionPt;

    private KVPair<String, Point> inRegionPair1;
    private KVPair<String, Point> inRegionPair2;
    private KVPair<String, Point> notInRegionPair;

    // rectangles and pairs for testing duplicates
    private Point duplicatesPt1;
    private Point duplicatesPt2;
    private Point duplicatesPt3;

    private KVPair<String, Point> duplicatesPair1;
    private KVPair<String, Point> duplicatesPair2;
    private KVPair<String, Point> duplicatesPair3;

    /***
     * Sets up Rectangles, KVPairs and ArrayList to be
     * used for testing
     */
    @Before
    public void setUp() {

        data = new Database();

        invalidPt = new Point(-1, -1);
        validPt = new Point(1, 1);
        validPt2 = new Point(2, 2);

        invalidPair = new KVPair<>("Invalid", invalidPt);
        validPair = new KVPair<>("Valid", validPt);
        validPair2 = new KVPair<>("Valid", validPt2);

    }


    /***
     * Test the insert method of the database class with an invalid and valid
     * rectangle
     */
    @Test
    public void testInsert() {

        // valid rectangle
        data.insert(validPair);

        // sytem output
        String output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains(String.format("Point inserted: %s\n",
            validPair.toString())));

        // clear output history
        systemOut().clearHistory();

        // invalid rectangle
        data.insert(invalidPair);

        // sytem output
        output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains(String.format("Point rejected: %s\n",
            invalidPair.toString())));
    }


    /***
     * Test the remove method of the database class on a rectangle not in
     * skiplist and one in skiplist
     */
    @Test
    public void testRemove() {

        // remove an name that is in skiplist
        data.insert(validPair);

        // clear output history
        systemOut().clearHistory();

        data.remove("Valid");

        // sytem output
        String output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains(String.format("Point removed: %s\n",
            validPair.toString())));

        // clear output history
        systemOut().clearHistory();

        // remove name not in skiplist
        data.remove("Valid");

        // sytem output
        output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains("Point not removed: Valid"));
    }


    /***
     * Test the remove by parameters method of the database class on a rectangle
     * not in
     * skiplist and one in skiplist. Also test attempt to remove invalid
     * rectangle.
     */
    @Test
    public void testRemoveByValue() {

        // remove point parameters in skiplist
        data.insert(validPair);
        data.remove(1, 1);

        // sytem output
        String output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains(String.format("Point removed: %s\n",
            validPair.toString())));

        // clear output history
        systemOut().clearHistory();

        // remove point parameters not in skiplist
        data.remove(1, 1);

        // sytem output
        output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains("Point not found: (1, 1)"));

        // clear output history
        systemOut().clearHistory();

        // test removing invalid rectangle
        data.remove(-1, -1);

        output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains("Point rejected: (-1, -1)\n"));
    }


    /***
     * Test the dump method of the database class to ensure lines are printed
     * properly to the console
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
        assertTrue(output.contains(String.format("Value %s\n", validPair
            .toString())));
        assertTrue(output.contains(String.format("Value %s\n", validPair2
            .toString())));
        assertTrue(output.contains("SkipList size is: 2\n"));

        assertTrue(output.contains("QuadTree dump:\n"));
        assertTrue(output.contains("Node at 0, 0, 1024:\n"));
        assertTrue(output.contains(String.format("%s\n", validPair
            .toString())));
        assertTrue(output.contains(String.format("%s\n", validPair2
            .toString())));
        assertTrue(output.contains("1 quadtree nodes printed\n"));

    }


    /***
     * Test the search method of the database class for key in list and one not
     * in list
     */
    @Test
    public void testSearch() {

        // search for keys that are in the list
        data.insert(validPair);
        data.insert(validPair2);
        data.search("Valid");

        // sytem output
        String output = systemOut().getHistory();

        assertTrue(output.contains(String.format("Found: %s\n", validPair
            .toString())));

        assertTrue(output.contains(String.format("Found: %s\n", validPair2
            .toString())));

        // clear output history
        systemOut().clearHistory();

        // search for key that is not in the list
        data.search("Invalid");

        // sytem output
        output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains("Point not found: Invalid"));

    }


    /***
     * Test the regionSearch method with invlaid input height and width
     */
    @Test
    public void testRegionSearchInvalid() {

        // test invalid search region width
        data.regionsearch(5, 5, -1, 5);

        // sytem output
        String output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains("rectangle rejected: (5, 5, -1, 5)"));

        // clear output history
        systemOut().clearHistory();

        // test invalid region search height
        data.regionsearch(5, 5, 5, -1);

        // sytem output
        output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains("rectangle rejected: (5, 5, 5, -1)"));

    }


    /***
     * Test the regionSearch method with valid regions
     */
    @Test
    public void testRegionSearch() {

        // initialize rectangles and pairs
        inRegionPt1 = new Point(0, 0);
        inRegionPt2 = new Point(150, 150);
        notInRegionPt = new Point(700, 700);

        inRegionPair1 = new KVPair<>("In region", inRegionPt1);
        inRegionPair2 = new KVPair<>("In region", inRegionPt2);
        notInRegionPair = new KVPair<>("Not in region", notInRegionPt);

        // insert two pairs in the search region and one not
        data.insert(inRegionPair1);
        data.insert(inRegionPair2);
        data.insert(notInRegionPair);

        // clear output history
        systemOut().clearHistory();

        // search a region that contains the inRegion pairs and does not contain
        // notInRegionPair
        data.regionsearch(0, 0, 150, 150);

        // sytem output
        String output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains(
            "Points intersecting region 0, 0, 150, 150:"));
        assertTrue(output.contains(inRegionPair1.toString()));
        assertTrue(output.contains(inRegionPair2.toString()));
        // should not contain an output for not in region pair
        assertFalse(output.contains(notInRegionPair.toString()));

    }


    /***
     * Test the regionSearch method on empty skiplist
     */
    @Test
    public void testRegionSearchEmpty() {

        // valid search region
        data.regionsearch(50, 50, 100, 100);

        // sytem output
        String output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains(
            "Points intersecting region 50, 50, 100, 100:"));

    }


    /***
     * Test the regionSearch method with a region that that contains no
     * rectangles
     */
    @Test
    public void testNotInRegionSearch() {

        // initialize rectangles and pairs
        notInRegionPt = new Point(700, 700);

        notInRegionPair = new KVPair<>("Not in region", notInRegionPt);

        // insert two pairs in the search region and one not
        data.insert(notInRegionPair);

        // clear output history
        systemOut().clearHistory();

        // search a region that contains the inRegion pairs and does not contain
        // notInRegionPair
        data.regionsearch(50, 50, 150, 150);

        // sytem output
        String output = systemOut().getHistory();

        // verify system output
        assertFalse(output.contains(notInRegionPair.toString()));

    }


    /***
     * Test the duplicates method with an empty skiplist
     */
    @Test
    public void testDuplicatesEmpty() {

        data.duplicates();

        // sytem output
        String output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains("Duplicate points:"));

    }


    /***
     * Test the intersections method with some intersecting rectangles
     */
    @Test
    public void testDuplicates() {

        // three dupliacte points
        duplicatesPt1 = new Point(0, 0);
        duplicatesPt2 = new Point(0, 0);
        duplicatesPt3 = new Point(0, 0);

        duplicatesPair1 = new KVPair<>("A", duplicatesPt1);
        duplicatesPair2 = new KVPair<>("B", duplicatesPt2);
        duplicatesPair3 = new KVPair<>("C", duplicatesPt3);

        // add pairs to skiplist and quadtree
        data.insert(duplicatesPair1);
        data.insert(duplicatesPair2);
        data.insert(duplicatesPair3);

        // clear output history
        systemOut().clearHistory();

        // call intersections
        data.duplicates();

        // sytem output
        String output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains("Duplicate points:"));
        assertTrue(output.contains(duplicatesPt1.toString()));
    }
}
