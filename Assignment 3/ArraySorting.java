import java.util.Arrays;
import java.util.Random;

public class ArraySorting {

    // =================== GENERATE ARRAY SORT ===================
    public static int[] generateRandomArray(int size, int maxValue) {
        Random random = new Random();
        return random.ints(size, 0, maxValue).toArray();
    }

    // =================== INSERTION SORT ===================
    public static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
    
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j = j - 1;
            }
            a[j + 1] = key;
        }
    }
    

    // =================== HEAP SORT ===================
    public static void heapSort(int[] a) {
        int n = a.length;
    
        // build heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(a, n, i);
    
        //  extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            // move current root to end
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
    
            // call max heapify on reduced heap
            heapify(a, i, 0);
        }
    }
    
    private static void heapify(int[] a, int n, int i) {
        int largest = i;    // largest as root
        int l = 2 * i + 1;
        int r = 2 * i + 2;
    
        // left child > root
        if (l < n && a[l] > a[largest])
            largest = l;
    
        // right child > largest (so far)
        if (r < n && a[r] > a[largest])
            largest = r;
    
        // largest != root
        if (largest != i) {
            int swap = a[i];
            a[i] = a[largest];
            a[largest] = swap;
    
            // heapify affected sub-tree
            heapify(a, n, largest);
        }
    }

    // =================== MERGE SORT ===================
    public static void mergeSort(int[] a) {
        int n = a.length;
        if (n < 2) {
            return;
        }
    
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];
    
        System.arraycopy(a, 0, l, 0, mid);
        System.arraycopy(a, mid, r, 0, n - mid);
    
        mergeSort(l);
        mergeSort(r);
    
        merge(a, l, r);
    }
    
    private static void merge(int[] a, int[] l, int[] r) {
        int i = 0, j = 0, k = 0;
        while (i < l.length && j < r.length) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < l.length) {
            a[k++] = l[i++];
        }
        while (j < r.length) {
            a[k++] = r[j++];
        }
    }
    
    // =================== QUICK SORT ===================
    public static void quickSort(int[] a, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(a, begin, end);
    
            quickSort(a, begin, partitionIndex - 1);
            quickSort(a, partitionIndex + 1, end);
        }
    }
    
    private static int partition(int[] a, int begin, int end) {
        int pivot = a[end];
        int i = (begin - 1);
    
        for (int j = begin; j < end; j++) {
            if (a[j] <= pivot) {
                i++;
    
                int swapTemp = a[i];
                a[i] = a[j];
                a[j] = swapTemp;
            }
        }
    
        int swapTemp = a[i + 1];
        a[i + 1] = a[end];
        a[end] = swapTemp;
    
        return i + 1;
    }

    // =================== QUICK SORT WITH CUTOFF ===================
    public static void quickSortCutoff(int[] array, int begin, int end, int cutoff) {
        if (begin < end) {
            if (end - begin <= cutoff) {
                // Insertion sort for small sub-arrays
                insertionSortForSubarray(array, begin, end);
            } else {
                int partitionIndex = partition(array, begin, end);
                quickSortCutoff(array, begin, partitionIndex - 1, cutoff);
                quickSortCutoff(array, partitionIndex + 1, end, cutoff);
            }
        }
    }
    
    private static void insertionSortForSubarray(int[] array, int begin, int end) {
        for (int i = begin + 1; i <= end; i++) {
            int key = array[i];
            int j = i - 1;
    
            while (j >= begin && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    // =================== GET TIME ===================
    public static long getSortTime(int[] array, String algorithm) {
        long startTime = System.nanoTime();
        
        switch (algorithm.toLowerCase()) {
            case "insertion":
                insertionSort(array);
                break;
            case "heap":
                heapSort(array);
                break;
            case "merge":
                mergeSort(array);
                break;
            case "quick":
                quickSort(array, 0, array.length - 1);
                break;
            case "quickcutoff10":
                quickSortCutoff(array, 0, array.length - 1, 10);
                break;
            case "quickcutoff50":
                quickSortCutoff(array, 0, array.length - 1, 50);
                break;
            case "quickcutoff200":
                quickSortCutoff(array, 0, array.length - 1, 200);
                break;
        }
        
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // =================== MAIN ===================
    public static void main(String[] args) {
        int[] sizes = {50, 500, 1000, 2000, 5000, 10000};
        for (int size : sizes) {
            int[] array = generateRandomArray(size, 10000); // using max of 10000

            System.out.println("Times for array size:\t" + size);
            System.out.println("Insertion Sort:\t\t" + getSortTime(array, "Insertion") + " ns");
            System.out.println("Heap Sort:\t\t" + getSortTime(array, "Heap") + " ns");
            System.out.println("Merge Sort:\t\t" + getSortTime(array, "Merge") + " ns");
            System.out.println("Quick Sort:\t\t" + getSortTime(array, "Quick") + " ns");
            System.out.println("Quick Sort(cutoff:10):\t" + getSortTime(array, "QuickSortCutoff10") + " ns");
            System.out.println("Quick Sort(cutoff:50):\t" + getSortTime(array, "QuickSortCutoff50") + " ns");
            System.out.println("Quick Sort(cutoff:200):\t" + getSortTime(array, "QuickSortCutoff200") + " ns");
            System.out.println();
        }
    }
}