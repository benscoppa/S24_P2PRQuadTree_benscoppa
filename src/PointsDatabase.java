
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * On my honor:
 * - I have not used source code obtained from another student,
 * or any other unauthorized source, either modified or unmodified.
 * - All source code and documentation used in my program is
 * either my original work, or was derived by me from the
 * source code published in the textbook for this course.
 * - I have not discussed coding details about this project with
 * anyone other than the instructor, ACM/UPE tutors or the TAs assigned
 * to this course. I understand that I may discuss the concepts
 * of this program with other students, and that another student
 * may help me debug my program so long as neither of us writes
 * anything during the discussion or modifies any computer file
 * during the discussion. I have violated neither the spirit nor
 * letter of this restriction.
 * 
 * Ben Scoppa
 */

/**
 * This program implements a database that uses a skiplist and a quadtree
 * that both individually store points as a key value pair. This class creates
 * command processor object and reads in an input file to input commands. The
 * command processor determines the command that was read. The commands are
 * insert which adds apoint to the skiplist and the quadtree. remove which is
 * called by name (key) or by value and removes the rectangle from the skiplist
 * and quadtree if found. regionsearch which prints out the points that are
 * found inside the specified region rectangle. duplicates which outputs all the
 * duplicate points in the quadtree. search which prints all the rectangles with
 * a specified key using the skiplist. Last is dump which prints the depth of
 * each node and its value for the skiplist and the nodes of the quadtree with
 * information, formatting or level and the number of nodes. Database acts as
 * the interface between the command processor and the skiplist and quadtree.
 * The skiplist is design to work on all KVPairs not just point however the
 * quadtree only works on string, point KVPairs. Skiplist is the actual skiplist
 * that works on all KVPairs. QuadTree is the quadtree that handles the root of
 * the quadtree. QuadNode is an interface for the three types of quadnodes,
 * empty node which is a flyweight, internal node which has four children and no
 * data and leaf node which can hold up to three points. KVPair handles creating
 * and retriving key and value from KVPairs. The Rectngle class hangles the
 * rectangle objects and determining if rectangles are equal or intersect.
 * The Point class handles the creation od points and determining if they are
 * equal or in a region. RemoveResult is a return class used by the remove
 * method in the quadtree. RegionSearchResult is a return class used by the
 * regionSearch method in the quadtree. All of these classes also have test
 * classes that test all of the methods inside them to ensure they work
 * properly.
 */

/**
 * The class containing the main method, the entry point of the application. It
 * will take a command line file argument which include the commands to be read
 * and creates the appropriate SkipList and PRQuadTree objects and outputs the
 * correct results to the console as specified in the file.
 *
 * @author CS Staff
 * 
 * @version 2024-01-22
 */
public class PointsDatabase {

    /**
     * The entry point of the application.
     *
     * @param args
     *            The name of the command file passed in as a command line
     *            argument.
     */
    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println(
                "Invalid file. No filename in command line arguments");
            return;
        }

        // the file containing the commands
        File file = null;

        // Attempts to open the file and scan through it
        try {

            // takes the first command line argument and opens that file
            file = new File(args[0]);

            // creates a scanner object
            Scanner scanner = new Scanner(file);

            // creates a command processor object
            CommandProcessor cmdProc = new CommandProcessor();

            // reads the entire file and processes the commands
            // line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // determines if the file has more lines to read
                if (!line.trim().isEmpty()) {
                    cmdProc.processor(line.trim());
                }
            }
            // closes the scanner
            scanner.close();
        }
        // catches the exception if the file cannot be found
        // and outputs the correct information to the console
        catch (FileNotFoundException e) {
            System.out.println("Invalid file");
            e.printStackTrace();
        }

    }
}
