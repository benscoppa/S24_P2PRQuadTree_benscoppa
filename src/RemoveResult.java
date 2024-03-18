/**
 * This class is a return object that allows both a QuadNode and a string to be
 * returned together recursivly.
 * 
 * @author Ben Scoppa
 * 
 * @version 2024-03-16
 */
public class RemoveResult {

    // the QuadNode and Name
    private QuadNode updatedNode;
    private String removedPointName;

    /**
     * Creates an object with the values to the parameters given in the
     * updateNode, removedPointName
     * 
     * @param updatedNode
     *            the updated QuadNode to be returned recursivly
     * @param removedPointName
     *            the name of the point that was removed
     */
    public RemoveResult(QuadNode updatedNode, String removedPointName) {
        this.updatedNode = updatedNode;
        this.removedPointName = removedPointName;
    }


    /**
     * Getter for the updateNode
     * 
     * @return the updatedNode
     */
    public QuadNode getUpdatedNode() {
        return updatedNode;
    }


    /**
     * Getter for the removedPointName
     * 
     * @return the removedPointName
     */
    public String getRemovedPointName() {
        return removedPointName;
    }
}
