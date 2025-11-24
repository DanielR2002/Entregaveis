import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DualPriorityQueue {

    public static class dualPriorityQueue {

        private PriorityQueue<Integer> minHeap;
        private PriorityQueue<Integer> maxHeap;
        private Map<Integer, Integer> count;

        public dualPriorityQueue() {
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
            count = new HashMap<>();
        }

        private void addCount(int x) {
            count.put(x, count.getOrDefault(x, 0) + 1);
        }

        private void removeCount(int x) {
            Integer c = count.get(x);
            if (c == null) return;
            if (c <= 1) count.remove(x);
            else count.put(x, c - 1);
        }

        // Remove elementos "mortos" do heap
        private void clean(PriorityQueue<Integer> heap) {
            while (!heap.isEmpty()) {
                Integer top = heap.peek();
                Integer c = count.get(top);
                if (c == null || c == 0) {
                    heap.poll();
                } else {
                    break;
                }
            }
        }

        public void insert(int value) {
            minHeap.add(value);
            maxHeap.add(value);
            addCount(value);
        }

        public Integer getMin() {
            clean(minHeap);
            if (minHeap.isEmpty()) return null;
            return minHeap.peek();
        }

        public Integer getMax() {
            clean(maxHeap);
            if (maxHeap.isEmpty()) return null;
            return maxHeap.peek();
        }

        public Integer removeMin() {
            clean(minHeap);
            if (minHeap.isEmpty()) return null;

            int val = minHeap.poll();
            removeCount(val);
            return val;
        }

        public Integer removeMax() {
            clean(maxHeap);
            if (maxHeap.isEmpty()) return null;

            int val = maxHeap.poll();
            removeCount(val);
            return val;
        }

        public boolean isEmpty() {
            clean(minHeap);
            clean(maxHeap);
            return minHeap.isEmpty() && maxHeap.isEmpty();
        }
    }

    // Test
    public static void main(String[] args) {
        dualPriorityQueue dpq = new dualPriorityQueue();

        dpq.insert(5);
        dpq.insert(1);
        dpq.insert(9);
        dpq.insert(3);
        dpq.insert(9);

        System.out.println("Min: " + dpq.getMin());
        System.out.println("Max: " + dpq.getMax());

        System.out.println("removeMin -> " + dpq.removeMin());
        System.out.println("removeMax -> " + dpq.removeMax());

        System.out.println("Max agora -> " + dpq.getMax());
        System.out.println("removeMax -> " + dpq.removeMax());

        System.out.println("Min agora -> " + dpq.getMin());
        System.out.println("removeMin -> " + dpq.removeMin());
        System.out.println("removeMin -> " + dpq.removeMin());

        System.out.println("Está vazio? " + dpq.isEmpty());
    }
}
