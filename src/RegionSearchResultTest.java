
import java.util.LinkedList;
import student.TestCase;

/**
 * This class tests the methods of RegionSearchResult class,
 * ensuring that they work as they should.
 * 
 * @author Ben Scoppa
 * @version 2/17/2024
 */
public class RegionSearchResultTest extends TestCase {

    private RegionSearchResult regionSearchResult;
    private LinkedList<KVPair<String, Point>> regionLinkedList;
    private int integer;

    /**
     * Sets up the objects and initalizes them to their values
     */
    public void setUp() throws Exception {

        integer = 10;
        regionLinkedList = new LinkedList<>();
        regionSearchResult = new RegionSearchResult(regionLinkedList, integer);
    }


    /**
     * test the getter for region points
     */
    public void testGetRegionPoints() {

        assertEquals(regionSearchResult.getRegionPoints(), regionLinkedList);
    }


    /**
     * test the getter for visited nodes
     */
    public void testGetVisitedNodes() {

        assertEquals(regionSearchResult.getVisitedNodes(), integer);
    }

}
