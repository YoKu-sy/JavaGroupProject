import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Comparator;

public class Dijkstra {

    // time complexity O((V+E)logV)
    public static <T> PathResult<T> shortestPath(Graph<T> graph, T source, T target) {
        if (!graph.containsNode(source) || !graph.containsNode(target)) {
            return new PathResult<>(new ArrayList<T>(), 0);
        }

        // start == end, return directly
        if (source.equals(target)) {
            List<T> selfPath = new ArrayList<>();
            selfPath.add(source);
            return new PathResult<>(selfPath, 0);
        }

        Map<T, Integer> dist = new HashMap<>();
        Map<T, T> prev = new HashMap<>();
        Set<T> visited = new HashSet<>();

        for (T nodeId : graph.getAllNodeIds()) {
            dist.put(nodeId, Integer.MAX_VALUE);
        }
        dist.put(source, 0);

        PriorityQueue<Entry<T>> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.dist, b.dist)
        );

        pq.offer(new Entry<>(source, 0));

        while (!pq.isEmpty()) {
            Entry<T> current = pq.poll();
            T u = current.node;

            if (visited.contains(u)) {
                continue;
            }
            visited.add(u);

            if (u.equals(target)) {
                break;
            }

            List<Edge<T>> edges = graph.getNeighbors(u);
            for (int i = 0; i < edges.size(); i++) {
                Edge<T> edge = edges.get(i);
                T v = edge.getTo();

                if (visited.contains(v)) {
                    continue;
                }

                int newDist = dist.get(u) + edge.getWeight();
                if (newDist < dist.get(v)) {
                    dist.put(v, newDist);
                    prev.put(v, u);
                    pq.offer(new Entry<>(v, newDist));
                }
            }
        }

        if (dist.get(target) == Integer.MAX_VALUE) {
            return new PathResult<>(new ArrayList<T>(), 0);
        }

        // walk back from target to source by prev map
        List<T> path = new ArrayList<>();
        T cur = target;
        while (cur != null) {
            path.add(cur);
            cur = prev.get(cur);
        }
        Collections.reverse(path);

        return new PathResult<>(path, dist.get(target));
    }

    // call shortestPath several times for each segment
    public static <T> PathResult<T> shortestPathWithWaypoints(Graph<T> graph, List<T> waypoints) {
        if (waypoints == null || waypoints.size() < 2) {
            return new PathResult<>(new ArrayList<T>(), 0);
        }

        List<T> fullPath = new ArrayList<>();
        int totalCost = 0;

        for (int i = 0; i < waypoints.size() - 1; i++) {
            T point1 = waypoints.get(i);
            T point2 = waypoints.get(i + 1);
            PathResult<T> segment = shortestPath(graph, point1, point2);

            if (!segment.isReachable()) {
                return new PathResult<>(new ArrayList<T>(), 0);
            }

            List<T> segPath = segment.getPath();
            if (i == 0) {
                fullPath.addAll(segPath);
            } else {
                // skip j=0 to avoid adding the waypoint twice
                for (int j = 1; j < segPath.size(); j++) {
                    fullPath.add(segPath.get(j));
                }
            }
            totalCost = totalCost + segment.getTotalCost();
        }

        return new PathResult<>(fullPath, totalCost);
    }

    private static class Entry<T> {
        T node;
        int dist;

        public Entry(T node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }
}