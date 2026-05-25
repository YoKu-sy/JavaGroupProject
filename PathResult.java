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

    // check if we can go there or not
    public boolean isReachable() {
        if (path == null) {
            return false;
        }
        if (path.isEmpty() == true) {
            return false;
        }
        return true; // if not null and not empty, it means we can reach
    }

    @Override
    public String toString() {
        if (isReachable() == false) {
            return "No path found ! cost is infinity";
        }

        // make string to show the path result
        String finalResultString = "";

        for (int i = 0; i < path.size(); i++) {
            finalResultString = finalResultString + path.get(i);

            // add arrow if it is not the last one
            if (i != path.size() - 1) {
                finalResultString = finalResultString + " -> ";
            }
        }

        finalResultString = finalResultString + " (cost = " + totalCost + ")";

        return finalResultString;
    }
}