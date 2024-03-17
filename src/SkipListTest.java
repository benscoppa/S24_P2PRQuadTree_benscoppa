import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;
import org.junit.Before;
import student.TestCase;
import student.TestableRandom;

/**
 * This class tests the methods of SkipList class
 * 
 * @author CS Staff
 * @author Ben Scoppa
 * 
 * @version 2024-02-17
 */

public class SkipListTest extends TestCase {

    private SkipList<String, Rectangle> sl;

    // rectangles used to test methods
    private Rectangle a1;
    private Rectangle a2;
    private Rectangle b1;
    private Rectangle b2;
    private Rectangle c1;
    private Rectangle c2;

    // KVPairs containing the rectangles
    private KVPair<String, Rectangle> a1Pair;
    private KVPair<String, Rectangle> a2Pair;
    private KVPair<String, Rectangle> b1Pair;
    private KVPair<String, Rectangle> b2Pair;
    private KVPair<String, Rectangle> c1Pair;
    private KVPair<String, Rectangle> c2Pair;

    // ArrayLists for testing search
    private ArrayList<KVPair<String, Rectangle>> aMatches;
    private ArrayList<KVPair<String, Rectangle>> empty;

    /***
     * Sets up Rectangles, KVPairs and ArrayList to be
     * used for testing
     */
    @Before
    public void setUp() {

        // initialize skiplist
        sl = new SkipList<String, Rectangle>();

        // initialize rectangles
        a1 = new Rectangle(1, 2, 3, 4);
        b1 = new Rectangle(5, 6, 7, 8);
        c1 = new Rectangle(9, 10, 11, 12);
        a2 = new Rectangle(13, 14, 15, 16);
        b2 = new Rectangle(21, 22, 23, 24);
        c2 = new Rectangle(17, 18, 19, 20);

        // initialize KVPairs
        a1Pair = new KVPair<>("A", a1);
        b1Pair = new KVPair<>("B", b1);
        c1Pair = new KVPair<>("C", c1);
        a2Pair = new KVPair<>("A", a2);
        b2Pair = new KVPair<>("B", b2);
        c2Pair = new KVPair<>("C", c2);

        // initialize array lists
        aMatches = new ArrayList<>();
        aMatches.add(a2Pair);
        aMatches.add(a1Pair);

        empty = new ArrayList<>();

    }


    /**
     * Example 1: Test `randomLevel` method with
     * predetermined random values using `TestableRandom`
     */
    @Test
    public void testRandomLevelOne() {
        TestableRandom.setNextBooleans(false);
        sl = new SkipList<String, Rectangle>();
        int randomLevelValue = sl.randomLevel();

        // This returns 1 because the first preset
        // random boolean is `false` which breaks
        // the `while condition inside the `randomLevel` method
        int expectedLevelValue = 1;

        // Compare the values
        assertEquals(expectedLevelValue, randomLevelValue);
    }


    /**
     * Example 2: Test `randomLevel` method with
     * predetermined random values using `TestableRandom`
     */
    @Test
    public void testRandomLevelFour() {
        TestableRandom.setNextBooleans(true, true, true, false, true, false);
        sl = new SkipList<String, Rectangle>();
        int randomLevelValue = sl.randomLevel();

        // This returns 4 because the fourth preset
        // random boolean is `false` which breaks
        // the `while condition inside the `randomLevel` method
        int expectedLevelValue = 4;

        // Compare the values
        assertEquals(expectedLevelValue, randomLevelValue);
    }


    /**
     * Test the dump method on an empty skiplist
     */
    @Test
    public void testDumpEmpty() {

        sl.dump();

        // sytem output
        String output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains("SkipList dump:"));
        assertTrue(output.contains("Node has depth 1, Value (null)"));
        assertTrue(output.contains("SkipList size is: 0"));

    }


    /**
     * Test insert and dump methods to check their
     * expectedto the output of dump
     */
    @Test
    public void testInsertAndDump() {

        // insert into the skip list
        sl.insert(b1Pair);
        // insert before B
        sl.insert(a1Pair);
        // insert after B
        sl.insert(c1Pair);
        // insert same as A
        sl.insert(a2Pair);
        // insert same as C
        sl.insert(c2Pair);
        // insert same as B
        sl.insert(b2Pair);

        // call dump
        sl.dump();

        // sytem output for dump
        String dumpOutput = systemOut().getHistory();

        // check the dump for proper output
        assertTrue(dumpOutput.contains("SkipList dump:\n"));
        assertTrue(dumpOutput.contains("Value (null)\n"));
        assertTrue(dumpOutput.contains("Value (A, 1, 2, 3, 4)\n"));
        assertTrue(dumpOutput.contains("Value (B, 5, 6, 7, 8)\n"));
        assertTrue(dumpOutput.contains("Value (C, 9, 10, 11, 12)\n"));
        assertTrue(dumpOutput.contains("Value (A, 13, 14, 15, 16)\n"));
        assertTrue(dumpOutput.contains("Value (C, 17, 18, 19, 20)\n"));
        assertTrue(dumpOutput.contains("Value (B, 21, 22, 23, 24)\n"));
        assertTrue(dumpOutput.contains("SkipList size is: 6\n"));
    }


    /**
     * Test the remove by key method to make sure the return type is correct and
     * nodes are properly removed also test that size is updated properly
     */
    @Test
    public void testRemove() {

        // insert into the skip list
        sl.insert(b1Pair);
        // insert before B
        sl.insert(a1Pair);
        // insert after B
        sl.insert(c1Pair);
        // insert same as A
        sl.insert(a2Pair);
        // insert same as C
        sl.insert(c2Pair);
        // insert same as B
        sl.insert(b2Pair);

        // check the size
        assertEquals(sl.size(), 6);

        // remove all of the "2" versions of B and C
        assertEquals(sl.remove("B"), b2Pair);
        assertEquals(sl.remove("C"), c2Pair);

        // remove C1
        assertEquals(sl.remove("C"), c1Pair);

        // attempt to remove value not in list
        assertEquals(sl.remove("C"), null);

        // check the size
        assertEquals(sl.size(), 3);

        // call dump
        sl.dump();

        // sytem output for dump
        String dumpOutput = systemOut().getHistory();

        // check the dump for proper output
        assertTrue(dumpOutput.contains("SkipList dump:\n"));
        assertTrue(dumpOutput.contains("Value (null)\n"));
        assertTrue(dumpOutput.contains("Value (A, 1, 2, 3, 4)\n"));
        assertTrue(dumpOutput.contains("Value (B, 5, 6, 7, 8)\n"));
        // removed from list
        assertFalse(dumpOutput.contains("Value (C, 9, 10, 11, 12)\n"));
        assertTrue(dumpOutput.contains("Value (A, 13, 14, 15, 16)\n"));
        // removed from list
        assertFalse(dumpOutput.contains("Value (C, 17, 18, 19, 20)\n"));
        // removed from list
        assertFalse(dumpOutput.contains("Value (B, 21, 22, 23, 24)\n"));
        assertTrue(dumpOutput.contains("SkipList size is: 3\n"));

    }


    /**
     * Test the remove by value method to make sure the return type is correct
     * and nodes are properly removed also test that size is updated properly
     */
    @Test
    public void testRemoveByValue() {

        // insert into the skip list
        sl.insert(b1Pair);
        // insert before B
        sl.insert(a1Pair);
        // insert after B
        sl.insert(c1Pair);

        sl.insert(a2Pair);
        // insert same as C1
        sl.insert(c2Pair);
        sl.insert(b2Pair);

        // check the size
        assertEquals(sl.size(), 6);

        // remove B1 and one copy of C1
        assertEquals(b1Pair, sl.removeByValue(b1, "B"));
        assertEquals(c1Pair, sl.removeByValue(c1, "C"));

        // remove second copy of C1
        assertEquals(c2Pair, sl.removeByValue(c2, "C"));

        // attempt to remove entry not in list
        assertEquals(null, sl.removeByValue(c1, "C"));

        // check the size
        assertEquals(sl.size(), 3);

        // call dump
        sl.dump();

        // sytem output for dump
        String dumpOutput = systemOut().getHistory();

        // check the dump for proper output
        assertTrue(dumpOutput.contains("SkipList dump:\n"));
        assertTrue(dumpOutput.contains("Value (null)\n"));
        assertTrue(dumpOutput.contains("Value (A, 1, 2, 3, 4)\n"));
        // removed from list
        assertFalse(dumpOutput.contains("Value (B, 5, 6, 7, 8)\n"));
        // removed from list
        assertFalse(dumpOutput.contains("Value (C, 9, 10, 11, 12)\n"));
        assertTrue(dumpOutput.contains("Value (A, 13, 14, 15, 16)\n"));
        assertTrue(dumpOutput.contains("Value (B, 21, 22, 23, 24)\n"));
        assertTrue(dumpOutput.contains("SkipList size is: 3\n"));
    }


    /***
     * Test the remove and removeByValue on an empty
     * skip list
     */
    @Test
    public void testRemoveFromEmptyList() {

        // remove by key
        assertEquals(sl.remove("A"), null);

        // remove by value
        assertEquals(sl.removeByValue(a1, "A"), null);
    }


    /***
     * Test the remove and removeByValue on a skip
     * list removing the only entry
     */
    @Test
    public void testRemoveSingleKey() {

        // test remove by key
        sl.insert(a1Pair);

        // remove entry not in list
        assertEquals(sl.remove("B"), null);

        // remove single entry
        assertEquals(sl.remove("A"), a1Pair);

        // make sure that the list is empty again
        assertEquals(sl.size(), 0);

        // test remove by value
        sl.insert(a1Pair);

        // remove entry not in list
        assertEquals(sl.removeByValue(b1, "B"), null);

        // remove single entry
        assertEquals(sl.removeByValue(a1, "A"), a1Pair);

        // make sure that the list is empty again
        assertEquals(sl.size(), 0);

    }


    /***
     * Test the search method to ensure the correct KVPairs are found
     */
    @Test
    public void testSearch() {

        // insert A1 and A2 into the skip list
        sl.insert(a1Pair);
        sl.insert(a2Pair);
        // insert extra entries
        sl.insert(c1Pair);
        sl.insert(c2Pair);

        // check that both entries are found
        assertTrue(sl.search("A").size() == 2);

        // test key in list
        assertEquals(sl.search("A"), aMatches);

        // test key not present in list
        assertEquals(sl.search("B"), empty);

    }
}
