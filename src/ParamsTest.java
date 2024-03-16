
import student.TestCase;

/**
 * This class tests the methods of Params class,
 * ensuring that they work as they should.
 * 
 * @author Ben Scoppa
 * @version 3/15/2024
 */
public class ParamsTest extends TestCase{
    
    Params params;
    
    /**
     * Sets up the objects and initalizes them to their values
     */
    public void setUp() {
        params = new Params(1, 2, 3);
    }

    /**
     * test the getX function
     */
    public void testGetX() {

        assertEquals(params.getX(), 1);

    }


    /**
     * test the getY function
     */
    public void testGetY() {

        assertEquals(params.getY(), 2);

    }
    
    /**
     * test the getSize function
     */
    public void testGetSize() {

        assertEquals(params.getSize(), 3);

    }

}
