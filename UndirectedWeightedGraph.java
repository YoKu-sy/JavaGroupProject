public class UndirectedWeightedGraph<T> extends WeightedGraph<T> {

    @Override
    public void addEdge(T from, T to, int weight) {
        Node<T> fromNode = getOrCreateNode(from);
        Node<T> toNode   = getOrCreateNode(to);

        // add edge in both directions
        fromNode.addEdge(new Edge<>(from, to, weight));
        toNode.addEdge(new Edge<>(to, from, weight));
    }
}
