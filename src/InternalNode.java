
/**
 * This class handles Internal nodes of the QuadTree. In overrides the methods
 * insert,
 * TODO finish javaDoc
 * 
 * @author Ben Scoppa
 * 
 * @version 2024-03-15
 * @param <String>
 *            The name of the point
 * @param <Point>
 *            The actual point
 */
public class InternalNode implements QuadNode {

    // QuadNodes that represent the four quadrants and children of the internal
    // node.
    private QuadNode nw, ne, sw, se;

    /**
     * Creates an internal node and initializes its children to empty nodes.
     * These empty nodes are really just the same flyweight node.
     */
    public InternalNode() {
        nw = ne = sw = se = EmptyNode.getInstance();
    }


    /**
     * When insert is called on an internal node the node finds its child which
     * contains the point in the KVPair. It recursivly calls the insert function
     * one the approprite child node base its quadrant.
     * 
     * @param it
     *            the KVPair to be inserted
     * @param params
     *            object that stores the parameters of of the region
     * 
     * @return itself after one of its clidren has been recursivly called
     */
    @Override
    public QuadNode insert(KVPair<String, Point> it, Params params) {

        // get old region parameters
        int topLeftX = params.getX();
        int topLeftY = params.getY();
        int regionSize = params.getSize();

        // get the new region size for children
        int newRegionSize = regionSize / 2;
        int middleX = topLeftX + newRegionSize;
        int middleY = topLeftY + newRegionSize;

        // determine the approprite child node to call insert on then get the
        // region parameters for that child
        if (it.getValue().inRegion(topLeftX, topLeftY, newRegionSize)) {

            Params newParams = new Params(topLeftX, topLeftY, newRegionSize);
            nw = nw.insert(it, newParams);
        }

        else if (it.getValue().inRegion(middleX, topLeftY, newRegionSize)) {

            Params newParams = new Params(middleX, topLeftY, newRegionSize);
            ne = ne.insert(it, newParams);
        }

        else if (it.getValue().inRegion(topLeftX, middleY, newRegionSize)) {

            Params newParams = new Params(topLeftX, middleY, newRegionSize);
            sw = sw.insert(it, newParams);
        }

        else if (it.getValue().inRegion(middleX, middleY, newRegionSize)) {

            Params newParams = new Params(middleX, middleY, newRegionSize);
            se = se.insert(it, newParams);
        }

        // return itself
        return this;
    }


    /**
     * When dump is called on an internal node the node it outputs information
     * about itself the recursivly calls its children.
     * 
     * @param level
     *            the current level of the QuadNode
     * @param params
     *            object that stores the parameters of of the region
     * 
     * @return itself after all of its clidren have been recursivly called
     */
    @Override
    public int dump(int level, Params params) {

        // get old region parameters
        int topLeftX = params.getX();
        int topLeftY = params.getY();
        int regionSize = params.getSize();

        // get the new region size for children
        int newRegionSize = regionSize / 2;
        int middleX = topLeftX + newRegionSize;
        int middleY = topLeftY + newRegionSize;
        int newLevel = level + 1;

        // create the indent base on the level of the node
        String indent = "  ".repeat(level);

        // print out information obout this internal node
        System.out.printf("%sNode at %d, %d, %d: Internal\n", indent, topLeftX,
            topLeftY, regionSize);

        // call the dump method on each of the children in preorder traverasl
        // order
        Params newParamsNW = new Params(topLeftX, topLeftY, newRegionSize);
        int nwNodes = nw.dump(newLevel, newParamsNW);

        Params newParamsNE = new Params(middleX, topLeftY, newRegionSize);
        int neNodes = ne.dump(newLevel, newParamsNE);

        Params newParamsSW = new Params(topLeftX, middleY, newRegionSize);
        int swNodes = sw.dump(newLevel, newParamsSW);

        Params newParamsSE = new Params(middleX, middleY, newRegionSize);
        int seNodes = se.dump(newLevel, newParamsSE);

        // return the number of nodes visited through its children plus one for
        // istself
        return nwNodes + neNodes + swNodes + seNodes + 1;
    }
}
