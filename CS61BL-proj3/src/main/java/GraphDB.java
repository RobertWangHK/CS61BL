import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Wraps the parsing functionality of the MapDBHandler as an example.
 * You may choose to add to the functionality of this class if you wish.
 * @author Alan Yao
 */
public class GraphDB {
    /**
     * Example constructor shows how to create and start an XML parser.
     * @param dbPath Path to the XML file to be parsed.
     */

    //GraphNode start;
    private HashMap<Long, GraphNode> firstIteration = new HashMap<>();
    private HashMap<Long, GraphNode> secondIteration = new HashMap<>();
    //private HashMap<Long, LinkedList<Connection>> connection = new HashMap<>();
    private HashMap<Long, LinkedList<GraphNode>> connectPoint = new HashMap<>();
    private ArrayList<Long> indexList = new ArrayList<>();
    private Trie trie = new Trie();


    public GraphDB(String dbPath) {
        //start = new GraphNode();
        try {
            File inputFile = new File(dbPath);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            MapDBHandler maphandler = new MapDBHandler(this);
            saxParser.parse(inputFile, maphandler);
            complete(trie);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        clean();
    }

    /**
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

    /**
     *  Remove nodes with no connections from the graph.
     *  While this does not guarantee that any two nodes in the remaining graph are connected,
     *  we can reasonably assume this since typically roads are connected.
     */
    private void clean() {
    }

    public List<String> getLocationsByPrefix(String prefix) {
        List<String> nameArray = new ArrayList<>();
        ArrayList<Long> result = trie.lookupDefinition(prefix);
        for (long a: result) {
            GraphNode node = firstIteration.get(a);
            String name = node.getName();
            nameArray.add(name);
        }
        return nameArray;
    }

    public ArrayList<GraphNode> getLocations(String prefix) {
        ArrayList<GraphNode> array = new ArrayList<>();
        ArrayList<Long> result = trie.lookupDefinition(prefix);
        for (long a: result) {
            GraphNode node = firstIteration.get(a);
            array.add(node);
        }
        return array;
    }

    private void complete(Trie trietemp) {
        Set set = firstIteration.keySet();
        Iterator<Long> iterator = set.iterator();
        while (iterator.hasNext()) {
            long c = iterator.next();
            GraphNode child = firstIteration.get(c);
            String name = child.getName();
            long id = child.getId();
            if (name != null) {
                name = cleanString(name);
                //System.out.println(name);
                trietemp.addDefinition(name, id);
            }
        }
    }

    public GraphNode nearestNode(Point node) {
        double distance = 99999;
        long index = 0L;
        for (long a : indexList) {
            GraphNode curr = secondIteration.get(a);
            if (getDistance(curr, node) < distance) {
                index = a;
                distance = getDistance(curr, node);
            }
        }
        return secondIteration.get(index);
    }

    public ArrayList<GraphNode> route(GraphNode startNode, GraphNode endNode) {

        HashSet<GraphNode> known = new HashSet<>();
        HashMap<GraphNode, Double> distance = new HashMap<>();
        HashMap<GraphNode, Double> hValue = new HashMap<>();
        HashMap<GraphNode, GraphNode> route = new HashMap<>();
        PriorityQueue<GraphNode> next = new PriorityQueue<>((b, a)->
                ((distance.get(a) + getDistance(a, endNode))
                > (distance.get(b) + getDistance(b, endNode))) ? -1 : 1);
        LinkedList<GraphNode> neighbors = new LinkedList<>();
        ArrayList<GraphNode> list = new ArrayList<>();

        GraphNode curr;
        GraphNode prev;
        next.add(startNode);
        distance.put(startNode, 0.0);

        while (!known.contains(endNode)) {
            curr = next.poll();
            double abc = curr.getId();
            known.add(curr);

            neighbors = connectPoint.get(curr.getId());
            for (GraphNode a: neighbors) {
                if (!distance.containsKey(a)) {
                    distance.put(a, distance.get(curr) + getDistance(curr, a));
                    route.put(a, curr);
                } else {
                    if (distance.get(a) > distance.get(curr) + getDistance(curr, a)) {
                        distance.put(a, distance.get(curr) + getDistance(curr, a));
                        route.put(a, curr);
                    }
                }

                if (!next.contains(a) && !known.contains(a)) {
                    next.add(a);
                }
            }

        }

        prev = endNode;
        while (prev != startNode) {
            list.add(prev);
            prev = route.get(prev);
        }
        list.add(prev);
        Collections.reverse(list);

        return list;

    }

    public double getDistance(GraphNode a, GraphNode b) {
        return Math.pow(Math.pow(Math.abs(a.getLat() - b.getLat()), 2)
                + Math.pow(Math.abs(a.getLon() - b.getLon()), 2), 0.5);
    }

    public double getDistance(GraphNode a, Point b) {
        return Math.pow(Math.pow(Math.abs(a.getLat() - b.getLatitude()), 2)
                + Math.pow(Math.abs(a.getLon() - b.getLongitude()), 2), 0.5);
    }

    public void addFirst(long id, GraphNode node) {
        firstIteration.put(id, node);
    }

    public void addSecond(long id) {
        secondIteration.put(id, firstIteration.get(id));
    }

    public void addConnection(long id1, long id2) {
        Connection tempConnect = new Connection(firstIteration.get(id1), firstIteration.get(id2));
        GraphNode tempNode = firstIteration.get(id2);

        if (connectPoint.containsKey(id1)) {
            connectPoint.get(id1).add(tempNode);
        } else {
            LinkedList<GraphNode> tempList = new LinkedList<>();
            tempList.add(tempNode);
            connectPoint.put(id1, tempList);
        }
    }

    public void addIndex(long index) {
        if (!indexList.contains(index)) {
            indexList.add(index);
        }
    }

    public HashMap<Long, GraphNode> getFirstIteration() {
        return firstIteration;
    }

    public HashMap<Long, GraphNode> getSecondIteration() {
        return secondIteration;
    }

    public HashMap<Long, LinkedList<GraphNode>> getConnectPoint() {
        return connectPoint;
    }

    public Trie getTrie() {
        return trie;
    }
}
