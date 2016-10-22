
/**
 * Created by rober on 7/21/2016.
 */
public class QTreeNode implements Comparable<QTreeNode> {
    private Point upper;
    private Point lower;

    private int depth;
    private String name;

    //l2 is lower left
    private QTreeNode l1;
    private QTreeNode l2;
    private QTreeNode r1;
    private QTreeNode r2;

    //public QTreeNode(String name) {name = name;}

    public QTreeNode(int depth, Point upper, Point lower, String name) {
        this.depth = depth;
        this.upper = upper;
        this.lower = lower;
        this.name = name;
    }

    public QTreeNode(QTreeNode temp) {
        this.upper = temp.getUpper();
        this.lower = temp.getLower();
        this.depth = temp.getDepth();
        this.name = temp.getName();
    }

    @Override
    public int compareTo(QTreeNode another) {
        //compare latitude
        if (this.getUpper().getLatitude()
                != another.getUpper().getLatitude()) {
            if ((this.getUpper().getLatitude()
                    - another.getUpper().getLatitude()) > 0) {
                return 1;
            } else {
                return -1;
            }
        } else if (this.getLower().getLongitude()
                != another.getLower().getLongitude()) {
            if ((this.getLower().getLongitude()
                    - another.getLower().getLongitude()) < 0) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }

    public void setL1(QTreeNode l1) {
        this.l1 = l1;
    }

    public void setL2(QTreeNode l2) {
        this.l2 = l2;
    }

    public void setR1(QTreeNode r1) {
        this.r1 = r1;
    }

    public void setR2(QTreeNode r2) {
        this.r2 = r2;
    }

    public String getName() {
        return name;
    }

    public Point getUpper() {
        return upper;
    }

    public Point getLower() {
        return lower;
    }

    public Point getCenter() {
        Point result = upper.average(lower);
        return result;
    }

    public Point getUpperCenter() {
        Point result = new Point(upper.getLatitude(), 0.5
                * (upper.getLongitude() + lower.getLongitude()));
        return result;
    }

    public Point getLowerCenter() {
        Point result = new Point(lower.getLatitude(), 0.5
                * (upper.getLongitude() + lower.getLongitude()));
        return result;
    }

    public Point getLeftCenter() {
        Point result = new Point(0.5
                * (upper.getLatitude() + lower.getLatitude()), upper.getLongitude());
        return result;
    }

    public Point getLRightCenter() {
        Point result = new Point(0.5
                * (upper.getLatitude() + lower.getLatitude()), lower.getLongitude());
        return result;
    }

    public int getDepth() {
        return depth;
    }

    public QTreeNode getL1() {
        return l1;
    }

    public QTreeNode getL2() {
        return l2;
    }

    public QTreeNode getR1() {
        return r1;
    }

    public QTreeNode getR2() {
        return r2;
    }

    ////test if picture of inupper and inlower is inside this
    //public boolean Contains(Point inUpper, Point inLower) {
    //    if(Point.isInside(inUpper, this.upper, this.lower)
    //            && Point.isInside(inLower, this.upper, this.lower)) {
    //        return true;
    //    }
    //    return false;
    //}
    ////test if a single point is inside this
    //public boolean ContainsPoint(Point test) {
    //    if(Point.isInside(test, this.upper, this.lower)) {
    //        return true;
    //    }
    //    return false;
    //}
    ////check if the node itself is within the range
    //public boolean IsInside(Point inUpper, Point inLower) {
    //    if(Point.isInside(this.upper, inUpper, inLower)
    //            && Point.isInside(this.lower, inUpper, inLower)) {
    //        return true;
    //    }
    //    return false;
    //}
    ////check if any of its point is inside the range
    //public boolean hasPointInside(Point inUpper, Point inLower) {
    //    Point rightupper = new Point(this.upper.getLatitude(), this.lower.getLongitude());
    //    Point leftlower = new Point(this.lower.getLatitude(), this.upper.getLongitude());
    //    if(Point.isInside(upper, inUpper, inLower)
    //            || Point.isInside(rightupper, inUpper, inLower)
    //            || Point.isInside(leftlower, inUpper, inLower)
    //            || Point.isInside(lower, inUpper, inLower)) {
    //        return true;
    //    }
    //    return false;
    //}
}
