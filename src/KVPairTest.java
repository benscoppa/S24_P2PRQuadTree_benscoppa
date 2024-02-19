
import student.TestCase;

/**
 * This class tests the KVPair class so that the member methods work properly
 * and that the expected behavior occurs.
 * 
 * @author CS Staff
 * 
 * @version 2024.1
 */
public class KVPairTest extends TestCase {

    // rectangle and KVPair used to test KVPair class
    private KVPair<String, Rectangle> testPair;
    private Rectangle testRec;

    /**
     * set up variables for the KVPair tests
     */
    public void setUp() {

        testRec = new Rectangle(1, 2, 3, 4);
        testPair = new KVPair<>("A", testRec);
    }


    /**
     * test the getKey method
     */
    public void testGetKey() {

        assertEquals(testPair.getKey(), "A");
    }


    /**
     * test the getKey method
     */
    public void testGetValue() {

        assertEquals(testPair.getValue(), testRec);
    }


    /**
     * test the toString method
     */
    public void testToString() {

        assertEquals(testPair.toString(), "(A, 1, 2, 3, 4)");
    }

}
