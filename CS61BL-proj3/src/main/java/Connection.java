/**
 * Created by cs61bl-jr on 7/16/16.
 */
public class Connection {
    private GraphNode from;
    private GraphNode to;
    private Point fromPoint;
    private Point toPoint;

    public Connection(Point from, Point to) {
        this.fromPoint = from;
        this.toPoint = to;
    }

    public Connection(GraphNode from, GraphNode to) {
        this.from = from;
        this.to = to;
    }

    public GraphNode getTo() {
        return to;
    }

    public Point getToPoint() {
        return toPoint;
    }
}
