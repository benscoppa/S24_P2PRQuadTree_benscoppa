
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import student.TestCase;

/**
 * This Class test the methods of the QuadTree class ensuring the work properly.
 * This includes insert,
 * TODO finish javaDoc
 * 
 * @author Ben Scoppa
 * @version 3/16/2024
 */
public class QuadTreeTest extends TestCase {

    QuadTree tree;

    private final ByteArrayOutputStream outContent =
        new ByteArrayOutputStream();

    Point p1;
    Point p2;
    Point p3;
    Point p4;
    Point p5;
    Point p6;
    Point p7;
    Point p8;
    Point p9;
    Point p10;
    Point p11;

    KVPair<String, Point> p1Pair;
    KVPair<String, Point> p2Pair;
    KVPair<String, Point> p3Pair;
    KVPair<String, Point> p4Pair;
    KVPair<String, Point> p5Pair;
    KVPair<String, Point> p6Pair;
    KVPair<String, Point> p7Pair;
    KVPair<String, Point> p8Pair;
    KVPair<String, Point> p9Pair;
    KVPair<String, Point> p10Pair;
    KVPair<String, Point> p11Pair;

    /**
     * Sets up Points, KVPairs and QuadTree to be used for testing along with
     * the output for debuging
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

        // sytem output
        String output = systemOut().getHistory();

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
            + "  Node at 0, 0, 512: Internal\n"
            + "    Node at 0, 0, 256:\n"
            + "    (p1, 1, 2)\n"
            + "    (p5, 1, 2)\n"
            + "    (p6, 1, 10)\n"
            + "    Node at 256, 0, 256: Empty\n"
            + "    Node at 0, 256, 256: Empty\n"
            + "    Node at 256, 256, 256:\n"
            + "    (p7, 400, 400)\n"
            + "    (p8, 500, 500)\n"
            + "  Node at 512, 0, 512:\n"
            + "  (p2, 1000, 2)\n"
            + "  (p9, 1000, 10)\n"
            + "  Node at 0, 512, 512:\n"
            + "  (p3, 1, 1000)\n"
            + "  (p10, 400, 1000)\n"
            + "  Node at 512, 512, 512:\n"
            + "  (p4, 1000, 1000)\n"
            + "  (p11, 900, 900)\n"
            + "9 quadtree nodes printed\n";

        // make sure expected output matches the real output
        assertFuzzyEquals(expectedOutput, outContent.toString());
    }

}
