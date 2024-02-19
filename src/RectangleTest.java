
import student.TestCase;

/**
 * This class tests the methods of Rectangle class,
 * ensuring that they work as they should.
 * 
 * @author Ben Scoppa
 * @version 2/15/2024
 */
public class RectangleTest extends TestCase {

    /**
     * Initializes rectangle objects and default
     * object to be used for the tests.
     */

    // base rectangle and identical copy
    private Rectangle rec;
    private Rectangle recCopy;

    // null rectangle
    private Rectangle nullRec;

    // default object for testing
    private Object notRec;

    // rectangles for testing to the left for rec and edge cases
    private Rectangle left;
    private Rectangle leftTouching;
    private Rectangle leftSmallIntersect;

    // rectangles for testing to the right for rec and edge cases
    private Rectangle right;
    private Rectangle rightTouching;
    private Rectangle rightSmallIntersect;

    // rectangles for testing to above rec and edge cases
    private Rectangle above;
    private Rectangle aboveTouching;
    private Rectangle aboveSmallIntersect;

    // rectangles for testing to below rec and edge cases
    private Rectangle below;
    private Rectangle belowTouching;
    private Rectangle belowSmallIntersect;

    // testing rectangles with unequal parameters
    private Rectangle diffrentXCoord;
    private Rectangle diffrentYCoord;
    private Rectangle diffrentWidth;
    private Rectangle diffrentHeight;

    // test for invalid rectangle parameters
    private Rectangle negHeight;
    private Rectangle negWidth;
    private Rectangle negXCoord;
    private Rectangle negYCoord;

    private Rectangle tooRight;
    private Rectangle touchingRight;

    private Rectangle tooDown;
    private Rectangle touchingDown;

    /**
     * Sets up the objects and initalizes them to their values
     */
    public void setUp() {

        // base rectangle for testing and identical copy
        rec = new Rectangle(500, 600, 100, 200);
        recCopy = new Rectangle(500, 600, 100, 200);

        // basic object for testing equals and intersect input
        notRec = new Object();
    }


    /**
     * test the getxCoordinate function
     */
    public void testGetxCoordinate() {

        assertEquals(rec.getxCoordinate(), 500);

    }


    /**
     * test the getyCoordinate function
     */
    public void testGetyCoordinate() {

        assertEquals(rec.getyCoordinate(), 600);

    }


    /**
     * test the getHeight function
     */
    public void testGetHeight() {

        assertEquals(rec.getHeight(), 200);

    }


    /**
     * test the getWidth function
     */
    public void testGetWidth() {

        assertEquals(rec.getWidth(), 100);

    }


    /**
     * test the intersect function for rectagles to the left, right, above
     * and below as well as intersecting rectangles. Also checks inncorrect
     * inputs. Edge cases are touching and non intersecting with a gap of 1
     */
    public void testIntersect() {

        assertFalse(rec.intersect(null)); // check null input
        assertFalse(rec.intersect(nullRec));

        // check rectangle to the left and edge cases
        left = new Rectangle(rec.getxCoordinate() - 200, rec.getyCoordinate(),
            50, 50);
        assertFalse(rec.intersect(left));

        leftTouching = new Rectangle(rec.getxCoordinate() - 200, rec
            .getyCoordinate(), 200, 50);
        assertFalse(rec.intersect(leftTouching));

        leftSmallIntersect = new Rectangle(rec.getxCoordinate() - 200, rec
            .getyCoordinate(), 201, 50);
        assertTrue(rec.intersect(leftSmallIntersect));

        // check rectangle to the right and edge cases
        right = new Rectangle(rec.getxCoordinate() + 300, rec.getyCoordinate(),
            50, 50);
        assertFalse(rec.intersect(right));

        rightTouching = new Rectangle(rec.getxCoordinate() - 200, rec
            .getyCoordinate(), 200, 50);
        assertFalse(rec.intersect(rightTouching));

        rightSmallIntersect = new Rectangle(rec.getxCoordinate() - 1, rec
            .getyCoordinate(), 200, 50);
        assertTrue(rec.intersect(rightSmallIntersect));

        // check rectangle to above and edge cases
        above = new Rectangle(rec.getxCoordinate(), rec.getyCoordinate() - 200,
            50, 50);
        assertFalse(rec.intersect(above));

        aboveTouching = new Rectangle(rec.getxCoordinate(), rec.getyCoordinate()
            - 200, 200, 200);
        assertFalse(rec.intersect(aboveTouching));

        aboveSmallIntersect = new Rectangle(rec.getxCoordinate(), rec
            .getyCoordinate() - 200, 200, 201);
        assertTrue(rec.intersect(aboveSmallIntersect));

        // check rectangle to above and edge cases
        below = new Rectangle(rec.getxCoordinate(), rec.getyCoordinate() + 300,
            50, 50);
        assertFalse(rec.intersect(below));

        belowTouching = new Rectangle(rec.getxCoordinate(), rec.getyCoordinate()
            + 200, 200, 200);
        assertFalse(rec.intersect(belowTouching));

        belowSmallIntersect = new Rectangle(rec.getxCoordinate(), rec
            .getyCoordinate() + 199, 200, 50);
        assertTrue(rec.intersect(belowSmallIntersect));

        // check that identical rectangles intersect
        assertTrue(rec.intersect(recCopy));

    }


    /**
     * test the equals function for rectangles with diffrent parameters as well
     * as rectangles that are the same. Also checks inccorect inputs.
     */
    public void testEquals() {

        assertTrue(rec.equals(rec)); // rectangle equal to itself
        assertTrue(rec.equals(recCopy)); // rectangle copy

        assertFalse(rec.equals(null)); // check null input
        assertFalse(rec.intersect(nullRec));
        assertFalse(rec.equals(notRec)); // check non rectangle object input

        // check diffrent x coordinate
        diffrentXCoord = new Rectangle(rec.getxCoordinate() + 1, rec
            .getyCoordinate(), rec.getWidth(), rec.getHeight());
        assertFalse(rec.equals(diffrentXCoord));

        // check diffrent y coordinate
        diffrentYCoord = new Rectangle(rec.getxCoordinate(), rec
            .getyCoordinate() + 1, rec.getWidth(), rec.getHeight());
        assertFalse(rec.equals(diffrentYCoord));

        // check diffrent width
        diffrentWidth = new Rectangle(rec.getxCoordinate(), rec
            .getyCoordinate(), rec.getWidth() + 1, rec.getHeight());
        assertFalse(rec.equals(diffrentWidth));

        // check diffren height
        diffrentHeight = new Rectangle(rec.getxCoordinate(), rec
            .getyCoordinate(), rec.getWidth(), rec.getHeight() + 1);
        assertFalse(rec.equals(diffrentHeight));

    }


    /**
     * test the toString function
     */
    public void testToString() {

        assertFuzzyEquals(rec.toString(), "500, 600, 100, 200");

    }


    /**
     * test the isInvalid function for rectagles that violate the word box as
     * well as rectangles with negative height or width. Also test rectangles
     * that are valid.
     */
    public void testIsInvalid() {

        // test negative paremter values
        negHeight = new Rectangle(1, 1, 1, -1);
        assertTrue(negHeight.isInvalid());

        negWidth = new Rectangle(1, 1, -1, 1);
        assertTrue(negWidth.isInvalid());

        negXCoord = new Rectangle(-1, 1, 1, 1);
        assertTrue(negXCoord.isInvalid());

        negYCoord = new Rectangle(1, -1, 1, 1);
        assertTrue(negYCoord.isInvalid());

        // test worldbox violations
        tooRight = new Rectangle(1, 1, 1024, 1);
        assertTrue(tooRight.isInvalid());

        touchingRight = new Rectangle(1, 1, 1023, 1);
        assertFalse(touchingRight.isInvalid());

        tooDown = new Rectangle(1, 1, 1, 1024);
        assertTrue(tooDown.isInvalid());

        touchingDown = new Rectangle(1, 1, 1, 1023);
        assertFalse(touchingDown.isInvalid());
    }
   
}
