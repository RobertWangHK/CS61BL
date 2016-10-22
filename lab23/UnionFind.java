import java.util.ArrayList;

/** A simple implementation of the UnionFind abstract data structure with path
 *  compression. This UnionFind structure only holds integer and there are two
 *  critical operations: union and find. When unioning two elements, the element
 *  contained in a tree of smaller size is placed as a subtree to the root
 *  vertex of the larger tree. Meanwhile finding an element implements path
 *  compression. When a vertex an element is traversed, it is automatically
 *   connected to the root of that tree.
 *
 *  Using the union find data structure allows for a fast implementation of
 *  Kruskal's algorithm as well as other set based operations.
 *
 *  @author
 *  @since
 */
public class UnionFind {

    /** Instance variables go here? */
    int[] arr;
    ArrayList<Integer> list = new ArrayList<>();


    /** Returns a UnionFind data structure holding N vertices. Initially, all
     *  vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO implement
        if(n<=0) {
            throw new IllegalArgumentException();
        }
        arr = new int[n+1];
        for(int i=0; i<arr.length; i++){
            arr[i] = -1;
        }
        //return new UnionFind();
    }

    /** Returns the size of the set V1 belongs to. */
    public int sizeOf(int v1) {
        // TODO implement
        if(v1 < 0 || v1 >= arr.length) {
            throw new IllegalArgumentException();
        }
        int temp = v1;
        while(arr[temp]>=0) {
            temp = arr[temp];
        }
        return -arr[temp];
    }

    /** Returns true if nodes V1 and V2 are connected. */
    public boolean isConnected(int v1, int v2) {
        // TODO implement
        if(v1 < 0 || v1 >= arr.length || v2 < 0 || v2 >= arr.length) {
            throw new IllegalArgumentException();
        }
        int temp1 = v1;
        int temp2 = v2;

        if(find(temp1)==find(temp2)){
            return true;
        }
        return false;
    }

    /** Remember that each disjoint set is represented as a tree. Find returns
     *  the root of the set VERTEX belongs to. Path-compression, where the
     *  vertices along the search path from VERTEX to its root are linked
     *  directly to the root, is employed allowing for fast search-time. */
    public int find(int vertex) {
        // TODO implement
        list.clear();
        if(vertex < 0 || vertex >= arr.length) {
            throw new IllegalArgumentException();
        }
        int root = Helpfind(vertex);
        for (int a: list) {
            arr[a] = root;
        }
        return root;
    }

    public int Helpfind(int vertex){
        if(arr[vertex]<0){
            return vertex;
        }
        list.add(vertex);
        return Helpfind(arr[vertex]);
    }

    /** Connects two elements V1 and V2 together in the UnionFind structure. V1
     *  and V2 can be any element and a union-by-size heurisitic is used. */
    public void union(int v1, int v2) {
        // TODO implement
        if(v1 < 0 || v1 >= arr.length || v2 < 0 || v2 >= arr.length) {
            throw new IllegalArgumentException();
        }
        int root1 = find(v1);
        int root2 = find(v2);
        if(root1!=root2){
            int size1 = sizeOf(v1);
            int size2 = sizeOf(v2);
            if(size1 <= size2) {
                arr[root1] = root2;
                arr[root2] -= size1;
            }
            else {
                arr[root2] = root1;
                arr[root1] -= size2;
            }
        }
    }
}
