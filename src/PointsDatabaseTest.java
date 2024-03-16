
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import student.TestCase;

/**
 * This class tests the methods of SkipListProject class
 * which serves as the entry point of the command line
 * program.
 * 
 * @author CS Staff
 * 
 * @version 2024-01-22
 */
public class PointsDatabaseTest extends TestCase {

    // ----------------------------------------------------------
    /**
     * Read contents of a file into a string
     * 
     * @param path
     *            File name
     * @return the string
     * @throws IOException
     */
    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }


    /**
     * Example 1: Tests the main method when the file name is invalid.
     */
    public void testMainNoArgs() {
        String[] testInput = {};
        PointsDatabase.main(testInput);
        assertTrue(fuzzyContains(systemOut().getHistory(), "Invalid file"));
    }


    /**
     * Example 2: Tests the main method when the file name is valid.
     */
    /**
     * This method tests the main functionality of the SkipListProject class.
     * It creates an instance of SkipListProject, calls the toString() method,
     * sets the expected output, sets the test input, and calls the main method
     * of SkipListProject with the test input. Finally, it asserts that the
     * expected output matches the system output.
     * 
     * @throws IOException
     */

    public void testMain() throws IOException {

        // Creates an instance of SkipListProject
        PointsDatabase project = new PointsDatabase();
        project.toString();

        // Sets the test input file.
        // This file contains a list of commands to be executed.
        // Type is array of Strings to match the `main` argument
        String[] testInput = { "solutionTestData/SyntaxTest.txt" };
        // Type is String to match type of `readFile` method
        String testOutput = "solutionTestData/SyntaxTestOutput.txt";

        // TODO Calls the main method of SkipListProject with the test input.
        // This should print out all the necessary console output.
        // The main method does NOT print anything console output right now
        // because in its current state it is missing all the necessary methods.
        // Those methods are for you to implement. :)
        PointsDatabase.main(testInput);

        // TODO Sets the expected output.
        // Change this to the file that contains expected output.
        // Then, you can read the output file and set the expected output from
        // that.
        String expected = readFile(testOutput);

        // asserts that the expected output matches the system output
        // generated by the main method of SkipListProject.
        assertFuzzyEquals(expected, systemOut().getHistory());
    }
}
