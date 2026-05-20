public class Sorting {

    public static void bubbleSort(Candidate[] arr) { // 时间复杂度：O(n^2)
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                // 调用 compareTo 方法
                // 如果结果 > 0，说明 arr[j] 的优先级比 arr[j+1] 低，需要把它往后“冒泡”
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    // 交换元素
                    Candidate temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // 如果某一趟没有发生任何交换，说明已经排好序了，直接退出
            if (!swapped) break;
        }
    }
    
    public static void quickSort(Candidate[] arr, int low, int high) { // 时间复杂度：O(n \log n)
        if (low < high) {
            // 获取分区索引
            int pi = partition(arr, low, high);
            
            // 递归排序左半部分和右半部分
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Candidate[] arr, int low, int high) {
        // 选择最后一个元素作为基准
        Candidate pivot = arr[high];
        int i = (low - 1); 

        for (int j = low; j < high; j++) {
            // 调用 compareTo 方法
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                // 交换 arr[i] 和 arr[j]
                Candidate temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // 把基准元素放到正确的位置 (i+1)
        Candidate temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void mergeSort(Candidate[] arr, int left, int right) { // 时间复杂度：O(n \log n)
        if (left < right) {
            // 找到中间点
            int mid = left + (right - left) / 2;

            // 递归排序左半部分和右半部分
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // 合并两部分
            merge(arr, left, mid, right);
        }
    }

    private static void merge(Candidate[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // 创建临时数组
        Candidate[] L = new Candidate[n1];
        Candidate[] R = new Candidate[n2];

        // 拷贝数据到临时数组
        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;

        // 合并临时数组
        while (i < n1 && j < n2) {
            // 调用 compareTo 方法进行合并判断
            if (L[i].compareTo(R[j]) <= 0) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // 拷贝剩下的元素
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    
}
