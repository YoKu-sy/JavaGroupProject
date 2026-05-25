public class Edge <T> {

    private final T from;
    private final T to;
    private final int weight;

    public Edge(T from, T to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public T getFrom() {
        return from;
    }

    public T getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return from + " -> " + to + " (" + weight + ")";
    }
}