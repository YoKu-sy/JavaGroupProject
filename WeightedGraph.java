import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class WeightedGraph<T> implements Graph<T> {

    // store all nodes here, key is id, value is node itself
    // use protected so other graph can use it
    protected final Map<T, Node<T>> nodes = new HashMap<>();

    // check if node inside, if not make a new one
    protected Node<T> getOrCreateNode(T id) {
        // do not use computeIfAbsent, just basic if logic
        if (nodes.containsKey(id) == false) {
            Node<T> newNode = new Node<>(id); // create new
            nodes.put(id, newNode);
        }
        return nodes.get(id);
    }

    @Override
    public List<Edge<T>> getNeighbors(T nodeId) {
        Node<T> node = nodes.get(nodeId);

        // if no node find, return empty list
        if (node == null) {
            return new ArrayList<>();
        } else {
            return node.getEdges();
        }
    }

    @Override
    public boolean containsNode(T nodeId) {
        if (nodes.containsKey(nodeId) == true) {
            return true;
        }
        return false;
    }

    @Override
    public Set<T> getAllNodeIds() {
        return nodes.keySet();
    }

    public int size() {
        return nodes.size();
    }
}