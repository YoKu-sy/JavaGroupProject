public static void main(String[] args) {
    Candidate[][] testContext = new Candidate[3][];
    testContext[0] = CsvReader.ReadCsv("candidates_A.csv");
    testContext[1] = CsvReader.ReadCsv("candidates_B.csv");
    testContext[2] = CsvReader.ReadCsv("candidates_C.csv");

    int testNum = 1;
    for (Candidate[] currentArray : testContext) {
        long[] bubbleTimeArray = new long[100];
        long[] mergeTimeArray  = new long[100];
        long[] quickTimeArray  = new long[100];

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
        testNum++;
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