
import java.util.regex.Pattern;

/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project spec.
 * 
 * @author CS Staff
 * @author Ben Scoppa
 * 
 * @version 2024-02-16
 */
public class CommandProcessor {

    // the database object to manipulate the
    // commands that the command processor
    // feeds to it
    private Database data;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     * 
     */
    public CommandProcessor() {
        data = new Database();
    }


    /**
     * This method parses keywords in the line and calls methods in the
     * database as required. Each line command will be specified by one of the
     * keywords to perform the actions. These actions are performed on
     * specified objects and include insert, remove, regionsearch, search, and
     * dump. If the command in the file line is not one of these, an
     * appropriate message will be written in the console. This processor
     * method is called for each line in the file. Note that the methods
     * called will themselves write to the console, this method does not,
     * only calling methods that do.
     * 
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {
        // converts the string of the line into an
        // array of its space (" ") delimited elements
        String[] arr = line.split("\\s{1,}");
        String command = arr[0]; // the command will be the first of these
                                 // elements

        // name = rectangle name (key)
        // x = xCoordinate
        // y = yCoordinate
        // w = width
        // h = height

        // calls the insert function and passes the correct
        // parameters by converting the string integers into
        // their Integer equivalent, trimming the whitespace
        if (command.equals("insert")) {
            // Calls insert
            if (arr.length == 4) { // expected parameters: name x y
                try {
                    String name = arr[1];
                    int x = Integer.parseInt(arr[2]);
                    int y = Integer.parseInt(arr[3]);

                    // create a point object object
                    Point pt = new Point(x, y);
                    KVPair<String, Point> pair = new KVPair<>(name, pt);
                    data.insert(pair);

                }
                catch (NumberFormatException e) {
                    System.out.printf("Invalid parameters "
                        + "for 'insert' command%n");
                    e.printStackTrace();
                }
            }
            else {
                System.out.printf("Incorrect number of "
                    + "parameters for 'insert' command%n");
            }
        }
        // calls the appropriate remove method based on the
        // number of white space delimited strings in the line
        else if (command.equals("remove")) {
            // checks the number of white space delimited strings in the line
            // to determine if remove by name or by value
            if (arr.length == 2) { // expect paramter: name
                // Calls remove by name
                String name = arr[1];
                data.remove(name);

            }
            else if (arr.length == 3) { // expected parameters: x, y
                // Calls remove by coordinate, converting string
                // integers into their Integer equivalent minus whitespace
                try {
                    int x = Integer.parseInt(arr[1]);
                    int y = Integer.parseInt(arr[2]);

                    data.remove(x, y);

                }
                catch (NumberFormatException e) {
                    System.out.printf("Invalid parameters "
                        + "for 'remove' command%n");
                    e.printStackTrace();
                }
            }
            else {
                System.out.printf("Incorrect number of parameters "
                    + "for 'remove' command%n");
            }
        }
        else if (command.equals("regionsearch")) {
            // calls the regionsearch method for a set of coordinates
            // the string integers in the line will be trimmed of whitespace
            if (arr.length == 5) { // expect parameters: x, y, w, h
                try {
                    int x = Integer.parseInt(arr[1]);
                    int y = Integer.parseInt(arr[2]);
                    int w = Integer.parseInt(arr[3]);
                    int h = Integer.parseInt(arr[4]);

                    data.regionsearch(x, y, w, h);

                }
                catch (NumberFormatException e) {
                    System.out.printf("Invalid parameters "
                        + "for 'regionsearch' command%n");
                    e.printStackTrace();
                }
            }
            else {
                System.out.printf("Incorrect number of parameters "
                    + "for 'regionsearch' command%n");
            }
        }
        else if (command.equals("duplicates")) {
            // calls the duplicates method, no parameters to be passed
            // (see the duplicates JavaDoc in the Database class for
            // more information)
            if (arr.length == 1) { // no expected parameters
                data.duplicates();
            }
            else {
                System.out.printf("Incorrect number of parameters "
                    + "for 'duplicates' command%n");
            }

        }
        else if (command.equals("search")) {
            // calls the search method for a name of object
            if (arr.length == 2) { // expected parameter: name
                String name = arr[1];
                data.search(name);
            }
            else {
                System.out.printf("Incorrect number of parameters "
                    + "for 'search' command%n");
            }

        }
        else if (command.equals("dump")) {
            // calls the dump method for the database
            if (arr.length == 1) { // no expected paramters
                data.dump();
            }
            else {
                System.out.printf("Incorrect number of parameters "
                    + "for 'dump' command%n");
            }

        }
        else {
            // the first white space delimited string in the line is not
            // one of the commands which can manipulate the database,
            // a message will be written to the console
            System.out.printf("Unrecognized command%n");
        }
    }

}
