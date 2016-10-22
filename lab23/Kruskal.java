import java.util.*;

/** A class that runs Kruskal's algorithm on a Graph. Given a graph G, Kruskal's
 *  algorithm constructs a new graph T such T is a spanning tree of G and the
 *  sum of its edge weights is less than the sum of the edge weights for
 *  every possible spanning tree T* of G. This is called the Minimum Spanning
 *  Tree (MST).
 *
 *  @author
 */
public class Kruskal {

    /** Returns the MST of INPUT using a naive isConnected implementation. */
    public static Graph minSpanTree(Graph input) {
        // TODO implement!
        //TreeSet<Edge> edges = input.getAllEdges();//how sorted
        //TreeSet<Integer> vertex = input.getAllVertices();//how sorted
        //Graph graph = new Graph();
        //for(int i=0; i<vertex.size(); i++){
        //    Edge e = edges.pollFirst();
        //    int a = e.getSource();
        //    int b = e.getDest();
        //    if(!Kruskal.isConnected(input,a,b)){
        //        graph.addEdge(e);
        //    }
        //}
        //return graph;
        return minSpanTreeFast(input);
    }

    /** Returns the MST of INPUT using the Union Find datastructure. */
    public static Graph minSpanTreeFast(Graph input) {
        // TODO implement!
        TreeSet<Integer> nodes = input.getAllVertices();
        TreeSet<Edge> edges = input.getAllEdges();//how sorted
        TreeSet<Integer> vertex = input.getAllVertices();//how sorted
        UnionFind union = new UnionFind(nodes.size());
        Graph graph = new Graph();
        for (Edge e : edges) {
            //Edge e = edges.pollFirst();
            int a = e.getSource();
            int b = e.getDest();
            if(!(union.find(a)==union.find(b))){
                graph.addEdge(e);
                union.union(a,b);
            }
        }
        return graph;
    }

    /** A naive implementation of BFS to check if two nodes are connected. */
    public static boolean isConnected(Graph g, int v1, int v2) {
        // TODO implement!
        HashSet<Integer> known = new HashSet<>();
        Queue<Integer> queue = new PriorityQueue<>();

        known.add(v1);
        queue.add(v1);
        int curr;

        while(!queue.isEmpty()){
            curr = queue.poll();
            known.add(curr);
            TreeSet<Integer> neighbors = g.getNeighbors(curr);
            if(neighbors.contains(v2)){
                return true;
            }
            for (int a: neighbors) {
                if(!known.contains(a)&&!queue.contains(a)){
                    queue.add(a);
                }
            }
        }
        return known.contains(v2);
    }
}
