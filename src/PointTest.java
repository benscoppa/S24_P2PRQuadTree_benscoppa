
import student.TestCase;

/**
 * This class tests the methods of Point class,
 * ensuring that they work as they should.
 * 
 * @author Ben Scoppa
 * @version 3/15/2024
 */
public class PointTest extends TestCase {

    // base point and identical copy
    private Point pt;
    private Point ptCopy;

    // non point object
    private Object notPt;

    // testing points with unequal parameters
    private Point diffrentXCoord;
    private Point diffrentYCoord;

    // test invalid points and valid edge cases
    private Point negXCoord;
    private Point negYCoord;

    private Point tooRight;
    private Point touchingRight;

    private Point tooDown;
    private Point touchingDown;
    
    // test points not in region and edge cases
    private Point leftRegion;
    private Point aboveRegion;
    
    private Point rightRegion;
    private Point touchingRightRegion;

    private Point belowRegion;
    private Point touchingBottomRegion;

    /**
     * Sets up the point objects and initalizes them to their values
     */
    public void setUp() {

        // base rectangle for testing and identical copy
        pt = new Point(500, 600);
        ptCopy = new Point(500, 600);

        // basic object for testing equals and intersect input
        notPt = new Object();
    }


    /**
     * test the getxCoordinate function
     */
    public void testGetxCoordinate() {

        assertEquals(pt.getxCoordinate(), 500);

    }


    /**
     * test the getyCoordinate function
     */
    public void testGetyCoordinate() {

        assertEquals(pt.getyCoordinate(), 600);

    }


    /**
     * test the equals function for points with diffrent parameters as well
     * as rpoints that are the same. Also checks inccorect inputs.
     */
    public void testEquals() {

        assertTrue(pt.equals(pt)); // point equal to itself
        assertTrue(pt.equals(ptCopy)); // point copy

        assertFalse(pt.equals(null)); // check null input
        assertFalse(pt.equals(notPt)); // check non rectangle object input

        // check diffrent x coordinate
        diffrentXCoord = new Point(pt.getxCoordinate() + 1, pt
            .getyCoordinate());
        assertFalse(pt.equals(diffrentXCoord));

        // check diffrent y coordinate
        diffrentYCoord = new Point(pt.getxCoordinate(), pt.getyCoordinate()
            + 1);
        assertFalse(pt.equals(diffrentYCoord));

    }


    /**
     * test the toString function
     */
    public void testToString() {

        assertFuzzyEquals(pt.toString(), "500, 600");

    }


    /**
     * test the isInvalid function for points that violate the word box as
     * Also test points that are valid.
     */
    public void testIsInvalid() {

        // test negative coordinates
        negXCoord = new Point(-1, 1);
        assertTrue(negXCoord.isInvalid());

        negYCoord = new Point(1, -1);
        assertTrue(negYCoord.isInvalid());

        // test worldbox violations
        tooRight = new Point(1025, 1);
        assertTrue(tooRight.isInvalid());

        touchingRight = new Point(1024, 1);
        assertFalse(touchingRight.isInvalid());

        tooDown = new Point(1, 1025);
        assertTrue(tooDown.isInvalid());

        touchingDown = new Point(1, 1024);
        assertFalse(touchingDown.isInvalid());
    }


    /**
     * test the inRegion function for points that violate are and are not in the
     * region
     */
    public void testInRegion() {
        
        // test points outside of region and edge cases
        leftRegion = new Point(1, 15);
        assertFalse(leftRegion.inRegion(10, 10, 10));

        aboveRegion = new Point(15, 1);
        assertFalse(aboveRegion.inRegion(10, 10, 10));

        rightRegion = new Point(40, 15);
        assertFalse(rightRegion.inRegion(10, 10, 10));

        touchingRightRegion = new Point(20, 15);
        assertTrue(touchingRightRegion.inRegion(10, 10, 10));

        belowRegion = new Point(15, 40);
        assertFalse(belowRegion.inRegion(10, 10, 10));

        touchingBottomRegion = new Point(15, 20);
        assertTrue(touchingBottomRegion.inRegion(10, 10, 10));
    }
}
