import java.util.LinkedList;

/**
 * This class handles Internal nodes of the QuadTree. overriding its methods.
 * 
 * @author Ben Scoppa
 * 
 * @version 2024-03-15
 */
public class InternalNode implements QuadNode {

    // QuadNodes that represent the four quadrants and children of the internal
    // node.
    private QuadNode nw;
    private QuadNode ne;
    private QuadNode sw;
    private QuadNode se;

    /**
     * Creates an internal node and initializes its children to empty nodes.
     * These empty nodes are really just the same flyweight node.
     */
    public InternalNode() {
        nw = EmptyNode.getInstance();
        ne = EmptyNode.getInstance();
        sw = EmptyNode.getInstance();
        se = EmptyNode.getInstance();
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


    /**
     * The remove method for an internal node. Find the child node where the
     * node to remove would exist and calls remove recursivly on this child.
     * 
     * @param pt
     *            the point to remove
     * @param params
     *            object that stores the parameters of of the region
     * 
     * @return QuadNodes recursivly
     */
    @Override
    public RemoveResult remove(Point pt, Params params, String name) {

        // get old region parameters
        int topLeftX = params.getX();
        int topLeftY = params.getY();
        int regionSize = params.getSize();

        // get the new region size for children
        int newRegionSize = regionSize / 2;
        int middleX = topLeftX + newRegionSize;
        int middleY = topLeftY + newRegionSize;

        // stores the removed point or failure if ther is none
        String removedPt = "";

        // determine the approprite child node to call remove on then get the
        // region parameters for that child
        if (pt.inRegion(topLeftX, topLeftY, newRegionSize)) {

            Params newParams = new Params(topLeftX, topLeftY, newRegionSize);
            RemoveResult nwRemoved = nw.remove(pt, newParams, name);
            nw = nwRemoved.getUpdatedNode();
            removedPt = nwRemoved.getRemovedPointName();
        }

        else if (pt.inRegion(middleX, topLeftY, newRegionSize)) {

            Params newParams = new Params(middleX, topLeftY, newRegionSize);
            RemoveResult neRemoved = ne.remove(pt, newParams, name);
            ne = neRemoved.getUpdatedNode();
            removedPt = neRemoved.getRemovedPointName();
        }

        else if (pt.inRegion(topLeftX, middleY, newRegionSize)) {

            Params newParams = new Params(topLeftX, middleY, newRegionSize);
            RemoveResult swRemoved = sw.remove(pt, newParams, name);
            sw = swRemoved.getUpdatedNode();
            removedPt = swRemoved.getRemovedPointName();
        }

        else if (pt.inRegion(middleX, middleY, newRegionSize)) {

            Params newParams = new Params(middleX, middleY, newRegionSize);
            RemoveResult seRemoved = se.remove(pt, newParams, name);
            se = seRemoved.getUpdatedNode();
            removedPt = seRemoved.getRemovedPointName();
        }

        // check to see if the internal node should become a leaf node
        LinkedList<KVPair<String, Point>> childsPoints = new LinkedList<>();

        childsPoints.addAll(nw.getPoints());
        childsPoints.addAll(ne.getPoints());
        childsPoints.addAll(sw.getPoints());
        childsPoints.addAll(se.getPoints());

        boolean sameElements = true;

        // if there are more than 3 points in all of the children
        if (childsPoints.size() > 3) {
            // if not all of the points are equal keep this internal node
            KVPair<String, Point> firstEntry = childsPoints.getFirst();
            for (KVPair<String, Point> currentPair : childsPoints) {
                if (!currentPair.equals(firstEntry)) {
                    sameElements = false;
                    break;
                }
            }
            // keep this internal node
            if (!sameElements) {
                RemoveResult result = new RemoveResult(this, removedPt);
                // return itself, and the name of removed point
                return result;
            }
        }

        // otherwise turn the internal node into a leaf node
        LeafNode newLeafNode = new LeafNode();
        for (KVPair<String, Point> currentPair : childsPoints) {
            newLeafNode.insert(currentPair, params);
        }

        // return the new leaf node and the name of the removed point
        RemoveResult result = new RemoveResult(newLeafNode, removedPt);
        return result;
    }


    /**
     * get the points contained in the internal node with is always none.
     * 
     * @return empty linked list since there are no points
     */
    @Override
    public LinkedList<KVPair<String, Point>> getPoints() {

        LinkedList<KVPair<String, Point>> childsPoints = new LinkedList<>();

        childsPoints.addAll(nw.getPoints());
        childsPoints.addAll(ne.getPoints());
        childsPoints.addAll(sw.getPoints());
        childsPoints.addAll(se.getPoints());
        return childsPoints;
    }


    /**
     * When duplicates is called on an internal node recursivly calls duplicates
     * on each of its children.
     * 
     * @return itself recursivly
     */
    @Override
    public QuadNode duplicates() {

        // call the duplicates method on each of the children in preorder
        // traverasl order
        nw.duplicates();

        ne.duplicates();

        sw.duplicates();

        se.duplicates();

        // return itself
        return this;
    }


    /**
     * When regionSearch is called on an internal node it calls regionSearch
     * recursivly on all of its children whos regions intersect with the search
     * region.
     * 
     * @param searchRegion
     *            a rectangle object that contains the region to search for
     *            points in within the QuadTree.
     * @param params
     *            object that stores the parameters of of the region
     * 
     * @return regionSearchResult contains the points in the search region and
     *         the number of points visited
     */
    @Override
    public RegionSearchResult regionSearch(
        Rectangle searchRegion,
        Params params) {

        // get old region parameters
        int topLeftX = params.getX();
        int topLeftY = params.getY();
        int regionSize = params.getSize();

        // get the new region size for children
        int newRegionSize = regionSize / 2;
        int middleX = topLeftX + newRegionSize;
        int middleY = topLeftY + newRegionSize;

        Rectangle nwRec = new Rectangle(topLeftX, topLeftY, newRegionSize,
            newRegionSize);
        Rectangle neRec = new Rectangle(middleX, topLeftY, newRegionSize,
            newRegionSize);
        Rectangle swRec = new Rectangle(topLeftX, middleY, newRegionSize,
            newRegionSize);
        Rectangle seRec = new Rectangle(middleX, middleY, newRegionSize,
            newRegionSize);

        // stores all the intersect points covered by the internal node
        LinkedList<KVPair<String, Point>> allPoints = new LinkedList<>();

        // integer keeps track of visited nodes
        int visitedNodes = 0;

        // determine the approprite child nodes to call regionSearch on then get
        // the region parameters for that child
        // also adds the points in the region within the child to the list of
        // all points in the internal node and the number of nodes visited.
        if (searchRegion.intersect(nwRec)) {

            Params newParams = new Params(topLeftX, topLeftY, newRegionSize);
            RegionSearchResult nwRegion = nw.regionSearch(searchRegion,
                newParams);
            allPoints.addAll(nwRegion.getRegionPoints());
            visitedNodes += nwRegion.getVisitedNodes();
        }

        if (searchRegion.intersect(neRec)) {

            Params newParams = new Params(middleX, topLeftY, newRegionSize);
            RegionSearchResult neRegion = ne.regionSearch(searchRegion,
                newParams);
            allPoints.addAll(neRegion.getRegionPoints());
            visitedNodes += neRegion.getVisitedNodes();
        }

        if (searchRegion.intersect(swRec)) {

            Params newParams = new Params(topLeftX, middleY, newRegionSize);
            RegionSearchResult swRegion = sw.regionSearch(searchRegion,
                newParams);
            allPoints.addAll(swRegion.getRegionPoints());
            visitedNodes += swRegion.getVisitedNodes();
        }

        if (searchRegion.intersect(seRec)) {

            Params newParams = new Params(middleX, middleY, newRegionSize);
            RegionSearchResult seRegion = se.regionSearch(searchRegion,
                newParams);
            allPoints.addAll(seRegion.getRegionPoints());
            visitedNodes += seRegion.getVisitedNodes();
        }

        // add 1 for visiting this internal node
        visitedNodes += 1;

        // return all the points in the search region covered by this internal
        // node.
        RegionSearchResult result = new RegionSearchResult(allPoints,
            visitedNodes);

        return result;
    }
}
