public class Main {
    public static void main(String[] args) {
        Candidate[][] testContext = new Candidate[3][];
        testContext[0] = CsvReader.ReadCsv("candidates_A.csv",
                row -> new Candidate(row[0], Integer.parseInt(row[1]))
        ).toArray(new Candidate[0]);
        testContext[1] = CsvReader.ReadCsv("candidates_B.csv",
                row -> new Candidate(row[0], Integer.parseInt(row[1]))
        ).toArray(new Candidate[0]);
        testContext[2] = CsvReader.ReadCsv("candidates_C.csv",
                row -> new Candidate(row[0], Integer.parseInt(row[1]))
        ).toArray(new Candidate[0]);
        String[][] top10Ids = new String[3][10];//used to store 10 locations
        String[] datasetNames = {"A", "B", "C"};
        int testNum = 1;
        for (Candidate[] currentArray : testContext) {
            long[] bubbleTimeArray = new long[100];
            long[] mergeTimeArray = new long[100];
            long[] quickTimeArray = new long[100];

            for (int i = 0; i < 100; i++) {
                // clone before sort for each function
                Candidate[] copy = currentArray.clone();
                long startTime = System.nanoTime();
                Sorting.bubbleSort(copy);
                long stopTime = System.nanoTime();
                bubbleTimeArray[i] = stopTime - startTime;

                copy = currentArray.clone();
                startTime = System.nanoTime();
                Sorting.mergeSort(copy, 0, copy.length - 1);   // length - 1
                stopTime = System.nanoTime();
                mergeTimeArray[i] = stopTime - startTime;

                copy = currentArray.clone();
                startTime = System.nanoTime();
                Sorting.quickSort(copy, 0, copy.length - 1);   // length - 1
                stopTime = System.nanoTime();
                quickTimeArray[i] = stopTime - startTime;
            }

            System.out.println("test " + testNum + ":");
            System.out.println("bubble avg = " + average(bubbleTimeArray) + " ns");
            System.out.println("merge avg = " + average(mergeTimeArray) + " ns");
            System.out.println("quick avg = " + average(quickTimeArray) + " ns");
            System.out.println();

            Candidate[] sorted = currentArray.clone();
            Sorting.mergeSort(sorted, 0, sorted.length - 1);

            System.out.println("Top 10 selected locations from Dataset " + datasetNames[testNum - 1] + ":");
            for (int i = 0; i < 10; i++) {
                top10Ids[testNum - 1][i] = sorted[i].getLocationId();
                System.out.println("  " + (i + 1) + ". " + sorted[i]);
            }
            System.out.println();
            testNum++;//Task 1 finished here

        }
    }

    //used to calculate average time
    public static double average(long[] arr) {
        long sum = 0;
        for (long value : arr) {
            sum += value;
        }
        return (double) sum / arr.length;
    }
}