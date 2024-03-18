
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import student.TestCase;

/**
 * This Class test the methods of the QuadTree class ensuring the work properly.
 * 
 * @author Ben Scoppa
 * @version 3/16/2024
 */
public class QuadTreeTest extends TestCase {

    private QuadTree tree;

    private final ByteArrayOutputStream outContent =
        new ByteArrayOutputStream();

    // point objects
    private Point p1;
    private Point p2;
    private Point p3;
    private Point p4;
    private Point p5;
    private Point p6;
    private Point p7;
    private Point p8;
    private Point p9;
    private Point p10;
    private Point p11;
    private Point p12;
    private Point p13;

    // rectange object for regionSearch
    private Rectangle region;

    // KVPair objects with a name and point value
    private KVPair<String, Point> p1Pair;
    private KVPair<String, Point> p2Pair;
    private KVPair<String, Point> p3Pair;
    private KVPair<String, Point> p4Pair;
    private KVPair<String, Point> p5Pair;
    private KVPair<String, Point> p6Pair;
    private KVPair<String, Point> p7Pair;
    private KVPair<String, Point> p8Pair;
    private KVPair<String, Point> p9Pair;
    private KVPair<String, Point> p10Pair;
    private KVPair<String, Point> p11Pair;
    private KVPair<String, Point> p12Pair;
    private KVPair<String, Point> p13Pair;

    /**
     * Sets up Points, KVPairs and QuadTree to be used for testing along with
     * the output for debuging and a rectangle for regionSearch.
     */
    @Before
    public void setUp() {

        System.setOut(new PrintStream(outContent));

        tree = new QuadTree();

        p1 = new Point(1, 2);
        p2 = new Point(1000, 2);
        p3 = new Point(1, 1000);
        p4 = new Point(1000, 1000);
        p5 = new Point(1, 2);
        p6 = new Point(1, 10);
        p7 = new Point(400, 400);
        p8 = new Point(500, 500);
        p9 = new Point(1000, 10);
        p10 = new Point(400, 1000);
        p11 = new Point(900, 900);
        p12 = new Point(1, 2);
        p13 = new Point(1, 2);

        p1Pair = new KVPair<String, Point>("p1", p1);
        p2Pair = new KVPair<String, Point>("p2", p2);
        p3Pair = new KVPair<String, Point>("p3", p3);
        p4Pair = new KVPair<String, Point>("p4", p4);
        p5Pair = new KVPair<String, Point>("p5", p5);
        p6Pair = new KVPair<String, Point>("p6", p6);
        p7Pair = new KVPair<String, Point>("p7", p7);
        p8Pair = new KVPair<String, Point>("p8", p8);
        p9Pair = new KVPair<String, Point>("p9", p9);
        p10Pair = new KVPair<String, Point>("p10", p10);
        p11Pair = new KVPair<String, Point>("p11", p11);
        p12Pair = new KVPair<String, Point>("p12", p12);
        p13Pair = new KVPair<String, Point>("p13", p13);
    }


    /**
     * Restores the system output.
     */
    @After
    public void restoreStreams() {
        System.setOut(System.out);
    }


    /**
     * Test the dump method on an empty Quad tree
     */
    @Test
    public void testDump() {

        tree.dump();

        // expected output
        String expectedOutput = "QuadTree dump:\n"
            + "Node at 0, 0, 1024: Empty\n" + "1 quadtree nodes printed\n";

        assertFuzzyEquals(outContent.toString(), expectedOutput);
    }


    /**
     * Test the insert method and dump on a QuadTree with many pointa inserted
     */
    @Test
    public void testInsert() {

        // insert all of the KVPairs
        tree.insert(p1Pair);
        tree.insert(p2Pair);
        tree.insert(p3Pair);
        tree.insert(p4Pair);
        tree.insert(p5Pair);
        tree.insert(p6Pair);
        tree.insert(p7Pair);
        tree.insert(p8Pair);
        tree.insert(p9Pair);
        tree.insert(p10Pair);
        tree.insert(p11Pair);

        tree.dump();

        // expected output
        String expectedOutput = "QuadTree dump:\n"
            + "Node at 0, 0, 1024: Internal\n"
            + "  Node at 0, 0, 512: Internal\n" + "    Node at 0, 0, 256:\n"
            + "    (p1, 1, 2)\n" + "    (p5, 1, 2)\n" + "    (p6, 1, 10)\n"
            + "    Node at 256, 0, 256: Empty\n"
            + "    Node at 0, 256, 256: Empty\n"
            + "    Node at 256, 256, 256:\n" + "    (p7, 400, 400)\n"
            + "    (p8, 500, 500)\n" + "  Node at 512, 0, 512:\n"
            + "  (p2, 1000, 2)\n" + "  (p9, 1000, 10)\n"
            + "  Node at 0, 512, 512:\n" + "  (p3, 1, 1000)\n"
            + "  (p10, 400, 1000)\n" + "  Node at 512, 512, 512:\n"
            + "  (p4, 1000, 1000)\n" + "  (p11, 900, 900)\n"
            + "9 quadtree nodes printed\n";

        // make sure expected output matches the real output
        assertFuzzyEquals(expectedOutput, outContent.toString());
    }


    /**
     * Test the remove method on the tree only removing by value.
     */
    @Test
    public void testRemove() {

        // insert all of the KVPairs
        tree.insert(p1Pair);
        tree.insert(p2Pair);
        tree.insert(p3Pair);
        tree.insert(p4Pair);
        tree.insert(p5Pair);
        tree.insert(p6Pair);
        tree.insert(p7Pair);
        tree.insert(p8Pair);
        tree.insert(p9Pair);
        tree.insert(p10Pair);
        tree.insert(p11Pair);

        // remove some nodes
        assertEquals("p6", tree.remove(p6, "0"));
        assertEquals("p7", tree.remove(p7, "0"));
        assertEquals("p3", tree.remove(p3, "0"));
        assertEquals("p10", tree.remove(p10, "0"));

        // attempt to remove a point not in the tree
        assertEquals("0", tree.remove(p3, "0"));

        tree.dump();

        // expected output
        String expectedOutput = "QuadTree dump:\n"
            + "Node at 0, 0, 1024: Internal\n" + "  Node at 0, 0, 512:\n"
            + "  (p1, 1, 2)\n" + "  (p5, 1, 2)\n" + "  (p8, 500, 500)\n"
            + "  Node at 512, 0, 512:\n" + "  (p2, 1000, 2)\n"
            + "  (p9, 1000, 10)\n" + "  Node at 0, 512, 512: Empty\n"
            + "  Node at 512, 512, 512:\n" + "  (p4, 1000, 1000)\n"
            + "  (p11, 900, 900)\n" + "5 quadtree nodes printed\n";

        // make sure expected output matches the real output
        assertFuzzyEquals(expectedOutput, outContent.toString());
    }


    /**
     * Test the remove method while also inputing a name to check the remove.
     */
    @Test
    public void testRemoveByName() {

        // insert three identical points with diffrent names
        tree.insert(p1Pair);
        tree.insert(p5Pair);
        tree.insert(p12Pair);

        assertEquals("p1", tree.remove(p5, "p1"));

        tree.insert(p1Pair);

        // edge case removing a point and name checking another
        assertEquals("p12", tree.remove(p5, "p12"));

        tree.insert(p12Pair);

        assertEquals("p5", tree.remove(p1, "p5"));
    }


    /**
     * Check that more than three points can be added to a region as long as
     * they are the same point
     */
    @Test
    public void testInsertManyIdentical() {
        // insert three identical points with diffrent names
        tree.insert(p1Pair);
        tree.insert(p5Pair);
        tree.insert(p12Pair);
        tree.insert(p13Pair);

        tree.dump();

        // expected output
        String expectedOutput = "QuadTree dump:\n" + "Node at 0, 0, 1024:\n"
            + "(p1, 1, 2)\n" + "(p5, 1, 2)\n" + "(p12, 1, 2)\n"
            + "(p13, 1, 2)\n" + "1 quadtree nodes printed\n";

        // make sure expected output matches the real output
        assertFuzzyEquals(expectedOutput, outContent.toString());
    }


    /**
     * Test the duplicates method of the QuadTree interface.
     */
    @Test
    public void testDuplicates() {

        tree.insert(p1Pair);
        tree.insert(p2Pair);
        tree.insert(p3Pair);
        tree.insert(p4Pair);
        tree.insert(p5Pair);
        tree.insert(p6Pair);
        tree.insert(p7Pair);
        tree.insert(p8Pair);
        tree.insert(p9Pair);
        tree.insert(p10Pair);
        tree.insert(p11Pair);

        tree.duplicates();

        // expected output
        String expectedOutput = "Duplicate points:\n" + "(1, 2)\n";

        // make sure expected output matches the real output
        assertFuzzyEquals(expectedOutput, outContent.toString());
    }


    /**
     * Test the regionSearch method of the QuadTree interface.
     */
    @Test
    public void testRegionSearch() {

        region = new Rectangle(300, 300, 600, 600);

        tree.insert(p1Pair);
        tree.insert(p2Pair);
        tree.insert(p3Pair);
        tree.insert(p4Pair);
        tree.insert(p5Pair);
        tree.insert(p6Pair);
        tree.insert(p7Pair);
        tree.insert(p8Pair);
        tree.insert(p9Pair);
        tree.insert(p10Pair);
        tree.insert(p11Pair);

        tree.regionSearch(region);

        // expected output
        String expectedOutput =
            "points intersecting region (300, 300, 600, 600):\n"
                + "Point found: (p7, 400, 400)\n"
                + "Point found: (p8, 500, 500)\n"
                + "Point found: (p11, 900, 900)\n" + "6 quadtree nodes visited";

        // make sure expected output matches the real output
        assertFuzzyEquals(expectedOutput, outContent.toString());
    }

}
