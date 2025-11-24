import java.util.*;

class KLargestElements {

    public static List<Integer> findKthLargest(int[] arr, int k) {

        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array não pode ser nulo ou vazio");
        }
        if (k <= 0 || k > arr.length) {
            throw new IllegalArgumentException("K deve estar entre 1 e tamanho do array");
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();


        for (int num : arr) {

            if (minHeap.size() < k) {
                minHeap.offer(num);
            }

            else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }

        List<Integer> result = new ArrayList<>(minHeap);
        Collections.sort(result);

        return result;
    }

    public static List<Integer> findKthLargestDesc(int[] arr, int k) {
        List<Integer> result = findKthLargest(arr, k);
        Collections.reverse(result);
        return result;
    }

    public static int findKthLargestElement(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : arr) {
            if (minHeap.size() < k) {
                minHeap.offer(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }

        return minHeap.peek();
    }
}