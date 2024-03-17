
public class RemoveResult {

    private QuadNode updatedNode;
    private String removedPointName;

    public RemoveResult(QuadNode updatedNode, String removedPointName) {
        this.updatedNode = updatedNode;
        this.removedPointName = removedPointName;
    }


    public QuadNode getUpdatedNode() {
        return updatedNode;
    }


    public String getRemovedPointName() {
        return removedPointName;
    }
}
