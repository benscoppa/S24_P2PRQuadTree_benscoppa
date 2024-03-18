
import java.util.LinkedList;
import student.TestCase;

/**
 * This class tests the methods of RemoveResult class,
 * ensuring that they work as they should.
 * 
 * @author Ben Scoppa
 * @version 2/17/2024
 */
public class RemoveResultTest extends TestCase {

    private RemoveResult removeResult;
    private QuadNode updatedNode;
    private String string;

    /**
     * Sets up the objects and initalizes them to their values
     */

    public void setUp() throws Exception {

        updatedNode = EmptyNode.getInstance();
        string = "string";
        removeResult = new RemoveResult(updatedNode, string);
    }


    /**
     * test the getter for update node
     */
    public void testGetUpdatedNode() {

        assertEquals(removeResult.getUpdatedNode(), updatedNode);
    }


    /**
     * test the getter for removed point name
     */
    public void testGetRemovedPointName() {

        assertEquals(removeResult.getRemovedPointName(), string);
    }

}
