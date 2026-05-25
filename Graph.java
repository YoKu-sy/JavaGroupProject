import java.util.List;
import java.util.Set;

public interface Graph<T> {

    // add an edge; directed or undirected depends on implementation
    void addEdge(T from, T to, int weight);

    // return all outgoing edges of a node, empty list if not found
    List<Edge<T>> getNeighbors(T nodeId);

    // check if a node exists
    boolean containsNode(T nodeId);

    // return all node IDs, used by Dijkstra to initialize distances
    Set<T> getAllNodeIds();
}
