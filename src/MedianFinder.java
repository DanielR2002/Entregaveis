import java.util.PriorityQueue;
import java.util.Collections;

public class MedianFinder {

    private PriorityQueue<Integer> lower;

    private PriorityQueue<Integer> higher;

    public MedianFinder() {
        lower = new PriorityQueue<>(Collections.reverseOrder()); // Max-Heap
        higher = new PriorityQueue<>(); // Min-Heap
    }

    public void addNum(int num) {

        if (lower.isEmpty() || num <= lower.peek()) {
            lower.add(num);
        } else {
            higher.add(num);
        }

        if (lower.size() > higher.size() + 1) {
            higher.add(lower.poll());
        } else if (higher.size() > lower.size()) {
            lower.add(higher.poll());
        }
    }

    public double findMedian() {

        if (lower.size() == higher.size()) {
            return (lower.peek() + higher.peek()) / 2.0;
        }


        return lower.peek();
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();

        mf.addNum(5);
        mf.addNum(2);
        mf.addNum(10);
        mf.addNum(3);

        System.out.println(mf.findMedian()); // 4.0
    }
}
