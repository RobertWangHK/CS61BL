import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cs61bl-jr on 7/16/16.
 */
public class GraphNode {

    private long id;
    private Point point;
    private int version;
    private String name;
    private ArrayList<HashMap<String, String>> tags = new ArrayList<>();
    private ArrayList<GraphNode> connected = new ArrayList<>();

    public GraphNode() { }

    public GraphNode(long id, Point point, int version) {
        this.id = id;
        this.point = point;
        this.version = version;
    }

    public GraphNode(long id, Point point) {
        this.point = point;
        this.id = id;
    }

    public GraphNode(String name, long id, Point point) {
        this.name = name;
        this.id = id;
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public long getId() {
        return id;
    }

    public double getLat() {
        return point.getLatitude();
    }

    public double getLon() {
        return point.getLongitude();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConnected(GraphNode node) {
        connected.add(node);
    }
}
