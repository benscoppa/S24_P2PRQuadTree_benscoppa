//import student.TestCase;
//import student.TestableRandom;
//
///**
// * This class tests the CommandProcessor class.
// * Test each possible command on its bounds,
// * if applicable to ensure they work properly.
// * Also test passing improper command to ensure
// * all class functionalities work as intended.
// * 
// * @author Ben Scoppa
// * @version 2/16/2024
// */
//public class CommandProcessorTest extends TestCase {
//
//    private CommandProcessor cmdProc;
//
//    /**
//     * The setUp() method will be called automatically before
//     * each test and reset whatever the test modified. For this
//     * test class, only a new database object is needed, so
//     * creat a database here for use in each test case.
//     */
//    public void setUp() {
//
//        cmdProc = new CommandProcessor();
//
//    }
//
//
//    /**
//     * test the insert command in processor checking command parsing and
//     * formatting
//     */
//    public void testInsert() {
//
//        // inserting a valid rectangle
//        String validCommand = "insert recName 10 20 30 40";
//        cmdProc.processor(validCommand);
//
//        // sytem output
//        String output = systemOut().getHistory();
//
//        // verify system output
//        assertTrue(output.contains(
//            "Rectangle inserted: (recName, 10, 20, 30, 40)"));
//
//        // clear output history
//        systemOut().clearHistory();
//
//        // test incorret number of parameters
//        String invalidCommand = "insert recName 10 20 30";
//        cmdProc.processor(invalidCommand);
//
//        // sytem output
//        output = systemOut().getHistory();
//
//        // verify system output
//        assertTrue(output.contains(
//            "Incorrect number of parameters for 'insert' command"));
//
//        // clear output history
//        systemOut().clearHistory();
//
//        // test invalid parameters
//        invalidCommand = "insert recName A B C D";
//        cmdProc.processor(invalidCommand);
//
//        // sytem output
//        output = systemOut().getHistory();
//
//        // verify system output
//        assertTrue(output.contains("Invalid parameters for 'insert' command"));
//
//    }
//
//
//    /**
//     * test the remove command in processor checking command parsing and
//     * formatting
//     */
//    public void testRemove() {
//
//        // test remove by name with valid command
//        String validCommand = "remove recName";
//        cmdProc.processor(validCommand);
//
//        // sytem output
//        String output = systemOut().getHistory();
//
//        // verify system output
//        assertTrue(output.contains("Rectangle not found: (recName)"));
//
//        // clear output history
//        systemOut().clearHistory();
//
//        // call remove by parameters
//        validCommand = "remove 1 1 1 1";
//        cmdProc.processor(validCommand);
//
//        // sytem output
//        output = systemOut().getHistory();
//
//        // verify system output
//        assertTrue(output.contains("Rectangle not found: (1, 1, 1, 1)"));
//
//        // clear output history
//        systemOut().clearHistory();
//
//        // test incorret number of parameters
//        String invalidCommand = "remove recName A";
//        cmdProc.processor(invalidCommand);
//
//        // sytem output
//        output = systemOut().getHistory();
//
//        // verify system output
//        assertTrue(output.contains(
//            "Incorrect number of parameters for 'remove' command"));
//
//        // clear output history
//        systemOut().clearHistory();
//
//        // test invalid parameters
//        invalidCommand = "remove A B C D";
//        cmdProc.processor(invalidCommand);
//
//        // sytem output
//        output = systemOut().getHistory();
//
//        // verify system output
//        assertTrue(output.contains("Invalid parameters for 'remove' command"));
//
//    }
//
//
//    /**
//     * test the search command in processor checking command parsing and
//     * formatting
//     */
//    public void testSearch() {
//
//        // test valid search command
//        String validCommand = "search recName";
//        cmdProc.processor(validCommand);
//
//        // sytem output
//        String output = systemOut().getHistory();
//
//        // verify system output
//        assertTrue(output.contains("Rectangle not found: recName"));
//
//        // clear output history
//        systemOut().clearHistory();
//
//        // test incorret number of parameters
//        String invalidCommand = "search recName A";
//        cmdProc.processor(invalidCommand);
//
//        // sytem output
//        output = systemOut().getHistory();
//
//        // verify system output
//        assertTrue(output.contains(
//            "Incorrect number of parameters for 'search' command"));
//
//    }
//
//
//    /**
//     * test the dump command in processor checking command parsing and
//     * formatting
//     */
//    public void testDump() {
//
//        // test valid dump command
//        String validCommand = "dump";
//        cmdProc.processor(validCommand);
//
//        // sytem output
//        String output = systemOut().getHistory();
//
//        // verify system output
//        assertTrue(output.contains("SkipList dump:"));
//        assertTrue(output.contains("Node has depth 1, Value (null)"));
//        assertTrue(output.contains("SkipList size is: 0"));
//
//        // clear output history
//        systemOut().clearHistory();
//
//        // test incorret number of parameters
//        String invalidCommand = "dump A";
//        cmdProc.processor(invalidCommand);
//
//        // sytem output
//        output = systemOut().getHistory();
//
//        // verify system output
//        assertTrue(output.contains(
//            "Incorrect number of parameters for 'dump' command"));
//    }
//
//
//    /**
//     * test the regionsearch command in processor checking command parsing and
//     * formatting
//     */
//    public void testRegionSearch() {
//
//        // valid search region command
//        String validCommand = "regionsearch 50 50 100 100";
//        cmdProc.processor(validCommand);
//
//        // sytem output
//        String output = systemOut().getHistory();
//
//        // verify system output
//        assertTrue(output.contains(
//            "Rectangles intersecting region (50, 50, 100, 100):"));
//
//        // clear output history
//        systemOut().clearHistory();
//
//        // test incorret number of parameters
//        String invalidCommand = "regionsearch 10 20 30";
//        cmdProc.processor(invalidCommand);
//
//        // sytem output
//        output = systemOut().getHistory();
//
//        // verify system output
//        assertTrue(output.contains(
//            "Incorrect number of parameters for 'regionsearch' command"));
//
//        // clear output history
//        systemOut().clearHistory();
//
//        // test invalid parameters
//        invalidCommand = "regionsearch A B C D";
//        cmdProc.processor(invalidCommand);
//
//        // sytem output
//        output = systemOut().getHistory();
//
//        // verify system output
//        assertTrue(output.contains(
//            "Invalid parameters for 'regionsearch' command"));
//    }
//
//
//    /**
//     * test the intersection command in processor checking command parsing and
//     * formatting
//     */
//    public void testIntersections() {
//
//        // test valid intersections command
//        String validCommand = "intersections";
//        cmdProc.processor(validCommand);
//
//        // sytem output
//        String output = systemOut().getHistory();
//
//        // verify system output
//        assertTrue(output.contains("Intersection pairs:"));
//
//        // clear output history
//        systemOut().clearHistory();
//
//        // test incorret number of parameters
//        String invalidCommand = "intersections A";
//        cmdProc.processor(invalidCommand);
//
//        // sytem output
//        output = systemOut().getHistory();
//
//        // verify system output
//        assertTrue(output.contains(
//            "Incorrect number of parameters for 'intersections' command"));
//    }
//
//
//    /**
//     * test the handling of unrecognized commands
//     */
//    public void testUnrecognizedCommand() {
//
//        // test unrecognized command
//        String unrecognizedCommand = "unrecognized";
//        cmdProc.processor(unrecognizedCommand);
//
//        // sytem output
//        String output = systemOut().getHistory();
//
//        // verify system output
//        assertTrue(output.contains("Unrecognized command"));
//    }
//
//}
