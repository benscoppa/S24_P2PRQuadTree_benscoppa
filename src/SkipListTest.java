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
    private Rectangle A1;
    private Rectangle A2;
    private Rectangle B1;
    private Rectangle B2;
    private Rectangle C1;
    private Rectangle C2;

    // KVPairs containing the rectangles
    private KVPair<String, Rectangle> A1Pair;
    private KVPair<String, Rectangle> A2Pair;
    private KVPair<String, Rectangle> B1Pair;
    private KVPair<String, Rectangle> B2Pair;
    private KVPair<String, Rectangle> C1Pair;
    private KVPair<String, Rectangle> C2Pair;

    // ArrayLists for testing search
    private ArrayList<KVPair<String, Rectangle>> AMatches;
    private ArrayList<KVPair<String, Rectangle>> Empty;

    @Before
    public void setUp() {

        // initialize skiplist
        sl = new SkipList<String, Rectangle>();

        // initialize rectangles
        A1 = new Rectangle(1, 2, 3, 4);
        B1 = new Rectangle(5, 6, 7, 8);
        C1 = new Rectangle(9, 10, 11, 12);
        A2 = new Rectangle(13, 14, 15, 16);
        B2 = new Rectangle(21, 22, 23, 24);
        C2 = new Rectangle(17, 18, 19, 20);

        // initialize KVPairs
        A1Pair = new KVPair<>("A", A1);
        B1Pair = new KVPair<>("B", B1);
        C1Pair = new KVPair<>("C", C1);
        A2Pair = new KVPair<>("A", A2);
        B2Pair = new KVPair<>("B", B2);
        C2Pair = new KVPair<>("C", C2);

        // initialize array lists
        AMatches = new ArrayList<>();
        AMatches.add(A2Pair);
        AMatches.add(A1Pair);

        Empty = new ArrayList<>();

    }


    /***
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


    /***
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


    /***
     * Test insert and dump methods to check their
     * expected the expected output of dump
     */
    @Test
    public void testInsertAndDump() {
        
        // insert into the skip list
        sl.insert(B1Pair);
        // insert before B
        sl.insert(A1Pair);
        // insert after B
        sl.insert(C1Pair);
        // insert same as A
        sl.insert(A2Pair);
        // insert same as C
        sl.insert(C2Pair);
        // insert same as B
        sl.insert(B2Pair);

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


    /***
     * Test the remove method that reomves by key.
     */
    @Test
    public void testRemove() {

        // insert into the skip list
        sl.insert(B1Pair);
        // insert before B
        sl.insert(A1Pair);
        // insert after B
        sl.insert(C1Pair);
        // insert same as A
        sl.insert(A2Pair);
        // insert same as C
        sl.insert(C2Pair);
        // insert same as B
        sl.insert(B2Pair);

        // check the size
        assertEquals(sl.size(), 6);

        // remove all of the "2" versions of B and C
        assertEquals(sl.remove("B"), B2Pair);
        assertEquals(sl.remove("C"), C2Pair);

        // remove C1
        assertEquals(sl.remove("C"), C1Pair);

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


    /***
     * Test the remove by value method that reomves
     * from the list based on the value.
     */
    @Test
    public void testRemoveByValue() {

        // insert into the skip list
        sl.insert(B1Pair);
        // insert before B
        sl.insert(A1Pair);
        // insert after B
        sl.insert(C1Pair);

        sl.insert(A2Pair);
        // insert same as C1
        sl.insert(C1Pair);
        sl.insert(B2Pair);

        // check the size
        assertEquals(sl.size(), 6);

        // remove B1 and one copy of C1
        assertEquals(B1Pair, sl.removeByValue(B1));
        assertEquals(C1Pair, sl.removeByValue(C1));

        // remove second copy of C1
        assertEquals(C1Pair, sl.removeByValue(C1));

        // attempt to remove entry not in list
        assertEquals(null, sl.removeByValue(C1));

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
        assertEquals(sl.removeByValue(A1), null);
    }


    /***
     * Test the remove and removeByValue on a skip
     * list removing the only entry
     */
    @Test
    public void testRemoveSingleKey() {

        // test remove by key
        sl.insert(A1Pair);

        // remove entry not in list
        assertEquals(sl.remove("B"), null);

        // remove single entry
        assertEquals(sl.remove("A"), A1Pair);

        // make sure that the list is empty again
        assertEquals(sl.size(), 0);

        // test remove by value
        sl.insert(A1Pair);

        // remove entry not in list
        assertEquals(sl.removeByValue(B1), null);

        // remove single entry
        assertEquals(sl.removeByValue(A1), A1Pair);

        // make sure that the list is empty again
        assertEquals(sl.size(), 0);

    }


    /***
     * Test the search method
     */
    @Test
    public void testSearch() {

        // insert A1 and A2 into the skip list
        sl.insert(A1Pair);
        sl.insert(A2Pair);
        // insert extra entries
        sl.insert(C1Pair);
        sl.insert(C2Pair);

        // test key in list
        assertEquals(sl.search("A"), AMatches);

        // test key not present in list
        assertEquals(sl.search("B"), Empty);

    }

}
