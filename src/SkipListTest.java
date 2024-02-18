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

    @Before
    public void setUp() {
     
        sl = new SkipList<String, Rectangle>();
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
        sl.insert(new KVPair<>("B", new Rectangle(5, 6, 7, 8)));
        // insert before B
        sl.insert(new KVPair<>("A", new Rectangle(1, 2, 3, 4)));
        // insert after B
        sl.insert(new KVPair<>("C", new Rectangle(9, 10, 11, 12)));
        // insert same as A
        sl.insert(new KVPair<>("A", new Rectangle(13, 14, 15, 16)));
        // insert same as C
        sl.insert(new KVPair<>("C", new Rectangle(17, 18, 19, 20)));
        // insert same as B
        sl.insert(new KVPair<>("B", new Rectangle(21, 22, 23, 24)));
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

}
