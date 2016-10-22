import java.util.ArrayList;

public class QuadTree {
    QTreeNode root;
    ArrayList<QTreeNode> list;
    private int row;
    private int column;


    public QuadTree(double rootullat, double rootullon, double rootlrlat, double rootlrlon) {
        Point upper = new Point(rootullat, rootullon);
        Point lower = new Point(rootlrlat, rootlrlon);
        root = new QTreeNode(0, upper, lower, "");
        list = new ArrayList<>();
        generateTree(root);

    }

    public ArrayList<QTreeNode> testSearch(double rasterUlLat,
                                       double rasterUlLon, double rasterLrLat,
                                       double rasterLrLon, int depth) {

        Point upper = new Point(rasterUlLat, rasterUlLon);
        Point upperright = new Point(rasterUlLat, rasterLrLon);
        Point lower = new Point(rasterLrLat, rasterLrLon);
        Point lowerleft = new Point(rasterLrLat, rasterUlLon);
        //int depth = 0;

        //calculate depth
        double height = rasterUlLat - rasterLrLat;
        double width = rasterLrLon - rasterUlLon;

        helpSearch(depth, upper, upperright, lower, lowerleft, root); //clockwise

        if (list.size() != 0) {
            list.sort((a, b) -> b.compareTo(a));
            findRowColum(list);
            return list;
        }
        return null;
    }

    private void findRowColum(ArrayList<QTreeNode> nodes) {
        QTreeNode start = new QTreeNode(nodes.get(0));
        double initialLangtitude = start.getUpper().getLatitude();
        double initialLongtitude = start.getUpper().getLongitude();
        row = 0;
        column = 0;
        for (QTreeNode a : nodes) {
            if (a.getUpper().getLatitude() == initialLangtitude) {
                column++;
            }
            if (a.getUpper().getLongitude() == initialLongtitude) {
                row++;
            }
        }
    }

    //l2 lower left, r1, upper right
    private void generateTree(QTreeNode node) {

        if (node.getDepth() == 7) {
            return;
        }

        String l1 = node.getName() + "1";
        String r1 = node.getName() + "2";
        String l2 = node.getName() + "3";
        String r2 = node.getName() + "4";
        int childDepth = node.getDepth() + 1;

        Point center = node.getCenter();
        Point upperCenter = node.getUpperCenter();
        Point lowerCenter = node.getLowerCenter();
        Point leftCenter = node.getLeftCenter();
        Point rightCenter = node.getLRightCenter();

        node.setL1(new QTreeNode(childDepth, node.getUpper(), center, l1));
        node.setL2(new QTreeNode(childDepth, leftCenter, lowerCenter, l2));
        node.setR1(new QTreeNode(childDepth, upperCenter, rightCenter, r1));
        node.setR2(new QTreeNode(childDepth, center, node.getLower(), r2));

        generateTree(node.getL1());
        generateTree(node.getL2());
        generateTree(node.getR1());
        generateTree(node.getR2());

    }
    //upper is range upper, lower is range lower
    private void helpSearch(int depth, Point upper, Point upperright,
                            Point lower, Point lowerleft, QTreeNode node) {
        if (node.getDepth() < depth) {
            if (intersect(node, upper, lower)) {
                helpSearch(depth, upper, upperright, lower, lowerleft, node.getL1());
                helpSearch(depth, upper, upperright, lower, lowerleft, node.getL2());
                helpSearch(depth, upper, upperright, lower, lowerleft, node.getR1());
                helpSearch(depth, upper, upperright, lower, lowerleft, node.getR2());
            }
        } else if (node.getDepth() == depth) {
            if (intersect(node, upper, lower)) {
                list.add(node);
            }
        }
    }

    public boolean intersect(QTreeNode node, Point upper, Point lower) {

        double x1 = node.getUpper().getLongitude();
        double y1 = node.getUpper().getLatitude();
        double x2 = node.getLower().getLongitude();
        double y2 = node.getLower().getLatitude();

        double x3 = upper.getLongitude();
        double y3 = upper.getLatitude();
        double x4 = lower.getLongitude();
        double y4 = lower.getLatitude();

        return (Math.abs(x4 - x1) <= Math.abs(x4 - x3) + Math.abs(x2 - x1))
                && (Math.abs(x2 - x3) <= Math.abs(x4 - x3) + Math.abs(x2 - x1))
                && (Math.abs(y1 - y4) <= Math.abs(y1 - y2) + Math.abs(y3 - y4))
                && (Math.abs(y2 - y3) <= Math.abs(y1 - y2) + Math.abs(y3 - y4));
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
