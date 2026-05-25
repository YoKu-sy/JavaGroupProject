import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class WeightedGraph<T> implements Graph<T> {

    // all nodes, key is id
    protected final Map<T, Node<T>> nodes = new HashMap<>();

    protected Node<T> getOrCreateNode(T id) {
        if (!nodes.containsKey(id)) {
            Node<T> newNode = new Node<>(id);
            nodes.put(id, newNode);
        }
        return nodes.get(id);
    }

    @Override
    public List<Edge<T>> getNeighbors(T nodeId) {
        Node<T> node = nodes.get(nodeId);
        if (node == null) {
            return new ArrayList<>();
        }
        return node.getEdges();
    }

    @Override
    public boolean containsNode(T nodeId) {
        return nodes.containsKey(nodeId);
    }

    @Override
    public Set<T> getAllNodeIds() {
        return nodes.keySet();
    }

    public int size() {
        return nodes.size();
    }
}