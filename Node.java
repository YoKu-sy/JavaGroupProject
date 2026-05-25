import java.util.ArrayList;
import java.util.List;

public class Node<T> {

    private final T id;
    private final List<Edge<T>> edges;

    public Node(T id) {
        this.id = id;
        this.edges = new ArrayList<>();
    }

    public T getId() {
        return id;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public void addEdge(Edge<T> edge) {
        edges.add(edge);
    }

    @Override
    public String toString() {
        return "Node{" + id + ", degree=" + edges.size() + "}";
    }
}