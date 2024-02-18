import student.TestCase;
import student.TestableRandom;

/**
 * This class tests the CommandProcessor class.
 * Test each possible command on its bounds,
 * if applicable to ensure they work properly.
 * Also test passing improper command to ensure
 * all class functionalities work as intended.
 * 
 * @author Ben Scoppa
 * @version 2/16/2024
 */
public class CommandProcessorTest extends TestCase {

    private CommandProcessor cmdProc;

    /**
     * The setUp() method will be called automatically before
     * each test and reset whatever the test modified. For this
     * test class, only a new database object is needed, so
     * creat a database here for use in each test case.
     */
    public void setUp() {

        cmdProc = new CommandProcessor();

    }


    public void testInsert() {

        // inserting a valid rectangle
        String validCommand = "insert recName 10 20 30 40";
        cmdProc.processor(validCommand);

        // sytem output
        String output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains(
            "Rectangle inserted: (recName, 10, 20, 30, 40)"));

        // test incorret number of parameters
        String invalidCommand = "insert recName 10 20 30";
        cmdProc.processor(invalidCommand);

        // sytem output
        output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains(
            "Incorrect number of parameters for 'insert' command\n"));
        
        // test invalid parameters
        invalidCommand = "insert recName A B C D";
        cmdProc.processor(invalidCommand);
        
        // sytem output
        output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains(
            "Invalid parameters for 'insert' command\n"));

    }
    
    public void testRemove() {
        
        // call remove by name
        String validCommand = "remove recName";
        cmdProc.processor(validCommand);
        
        // sytem output
        String output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains(
            "Rectangle not found: (recName)"));
        
        // call remove by parameters
        validCommand = "remove 1 1 1 1";
        cmdProc.processor(validCommand);
        
        // sytem output
        output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains("Rectangle not found: (1, 1, 1, 1)"));
        
        // test incorret number of parameters
        String invalidCommand = "remove recName A";
        cmdProc.processor(invalidCommand);

        // sytem output
        output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains(
            "Incorrect number of parameters for 'remove' command\n"));
        
        // test invalid parameters
        invalidCommand = "remove A B C D";
        cmdProc.processor(invalidCommand);
        
        // sytem output
        output = systemOut().getHistory();

        // verify system output
        assertTrue(output.contains(
            "Invalid parameters for 'remove' command\n"));
        
    }

}
