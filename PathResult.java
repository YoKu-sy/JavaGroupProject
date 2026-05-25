import java.util.List;

public class PathResult<T> {

    private final List<T> path;
    private final int totalCost;

    public PathResult(List<T> path, int totalCost) {
        this.path = path;
        this.totalCost = totalCost;
    }

    public List<T> getPath() {
        return path;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public boolean isReachable() {
        return path != null && !path.isEmpty();
    }

    @Override
    public String toString() {
        if (!isReachable()) {
            return "No path found ! cost is infinity";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i));
            if (i != path.size() - 1) {
                sb.append(" -> ");
            }
        }
        sb.append(" (cost = ").append(totalCost).append(")");
        return sb.toString();
    }
}