import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Candidate[][] testContext = new Candidate[3][];
        // read csv file by lambda
        testContext[0] = CsvReader.ReadCsv("src/candidates_A.csv",
                row -> new Candidate(row[0], Integer.parseInt(row[1]))
        ).toArray(new Candidate[0]);
        testContext[1] = CsvReader.ReadCsv("src/candidates_B.csv",
                row -> new Candidate(row[0], Integer.parseInt(row[1]))
        ).toArray(new Candidate[0]);
        testContext[2] = CsvReader.ReadCsv("src/candidates_C.csv",
                row -> new Candidate(row[0], Integer.parseInt(row[1]))
        ).toArray(new Candidate[0]);

        String[][] top10Ids = new String[3][10]; // put top 10 here
        String[] datasetNames = {"A", "B", "C"};
        int testNum = 1;

        // loop 3 times for 3 files
        for (int index = 0; index < 3; index++) {
            Candidate[] currentArray = testContext[index];
            long[] bubbleTimeArray = new long[100];
            long[] mergeTimeArray = new long[100];
            long[] quickTimeArray = new long[100];

            // test 100 times to get average
            for (int i = 0; i < 100; i++) {
                // clone it or the old array will be sorted
                Candidate[] copy = currentArray.clone();
                long startTime = System.nanoTime();
                Sorting.bubbleSort(copy);
                long stopTime = System.nanoTime();
                bubbleTimeArray[i] = stopTime - startTime;

                copy = currentArray.clone();
                startTime = System.nanoTime();
                Sorting.mergeSort(copy, 0, copy.length - 1);
                stopTime = System.nanoTime();
                mergeTimeArray[i] = stopTime - startTime;

                copy = currentArray.clone();
                startTime = System.nanoTime();
                Sorting.quickSort(copy, 0, copy.length - 1);
                stopTime = System.nanoTime();
                quickTimeArray[i] = stopTime - startTime;
            }

            System.out.println("now doing test " + testNum);
            System.out.println("bubble sort average time cost: " + average(bubbleTimeArray) + "ns");
            System.out.println("merge sort average time cost: " + average(mergeTimeArray) + "ns");
            System.out.println("quick sort average time cost: " + average(quickTimeArray) + "ns");
            System.out.println("");

            // get final sorted array for top 10
            Candidate[] sorted = currentArray.clone();
            Sorting.mergeSort(sorted, 0, sorted.length - 1);

            System.out.println("Here is top 10 locations in dataset " + datasetNames[testNum - 1] + " :");
            for (int i = 0; i < 10; i++) {
                top10Ids[testNum - 1][i] = sorted[i].getLocationId();
                System.out.println("rank " + (i + 1) + " is: " + sorted[i].toString());
            }
            System.out.println(""); // just print empty line
            testNum = testNum + 1;

        }

        // task B graph shortest path
        System.out.println("start task B here");
        System.out.println("");

        // load graph from path.csv
        List<Edge<String>> edges = CsvReader.ReadCsv("src/paths.csv",
                row -> new Edge<>(row[0], row[1], Integer.parseInt(row[2])));
        UndirectedWeightedGraph<String> graph = new UndirectedWeightedGraph<>();

        for (int i = 0; i < edges.size(); i++) {
            Edge<String> e = edges.get(i);
            graph.addEdge(e.getFrom(), e.getTo(), e.getWeight());
        }

        System.out.println("Success build graph. total node: " + graph.size() + ", total edge: " + edges.size());
        System.out.println("");

        // get the start and end point from top 10 array
        String a1  = top10Ids[0][0];
        String a10 = top10Ids[0][9];
        String b1  = top10Ids[1][0];
        String b5  = top10Ids[1][4];
        String c1  = top10Ids[2][0];
        String c5  = top10Ids[2][4];

        // test case 1
        System.out.println("Case 1:");
        PathResult<String> case1 = Dijkstra.shortestPath(graph, a1, a1);
        System.out.println("start node is " + a1);
        System.out.println("end node is " + a1);
        String result1 = case1.toString();
        System.out.println("result is " + result1);
        System.out.println("");

        // test case 2
        System.out.println("Case 2:");
        PathResult<String> case2 = Dijkstra.shortestPath(graph, a1, a10);
        System.out.println("start node is " + a1);
        System.out.println("end node is " + a10);
        System.out.println("result is " + case2.toString());
        System.out.println("");

        // test case 3
        System.out.println("Case 3:");
        PathResult<String> case3 = Dijkstra.shortestPathWithWaypoints(graph,
                Arrays.asList(a1, b5, b1));
        System.out.println("start=" + a1);
        System.out.println("way point=" + b5);
        System.out.println("end=" + b1);
        System.out.println("final path: " + case3.toString());
        System.out.println("");

        // test case 4
        System.out.println("Case 4:");
        PathResult<String> case4 = Dijkstra.shortestPathWithWaypoints(graph,
                Arrays.asList(a1, b5, c5, c1));
        System.out.println("start=" + a1);
        System.out.println("way point 1=" + b5);
        System.out.println("way point 2=" + c5);
        System.out.println("end=" + c1);
        System.out.println("final path: " + case4.toString());
        System.out.println("");
    }

    // function to calculate average time
    public static double average(long[] arr) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        double result = (double) sum / arr.length;
        return result;
    }
}