import java.util.*;

public class MergeKSortedLists {

    static class Node {
        int value;
        int listIndex;
        int elementIndex;

        Node(int value, int listIndex, int elementIndex) {
            this.value = value;
            this.listIndex = listIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static List<Integer> mergeKSortedLists(List<List<Integer>> lists) {
        List<Integer> result = new ArrayList<>();

        PriorityQueue<Node> minHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.value, b.value)
        );

        for (int i = 0; i < lists.size(); i++) {
            if (!lists.get(i).isEmpty()) {
                minHeap.add(new Node(lists.get(i).get(0), i, 0));
            }
        }

        while (!minHeap.isEmpty()) {
            Node current = minHeap.poll();
            result.add(current.value);

            int nextIndex = current.elementIndex + 1;
            if (nextIndex < lists.get(current.listIndex).size()) {
                int nextValue = lists.get(current.listIndex).get(nextIndex);
                minHeap.add(new Node(nextValue, current.listIndex, nextIndex));
            }
        }

        return result;
    }

    // Teste rápido
    public static void main(String[] args) {
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(Arrays.asList(1, 4, 5));
        lists.add(Arrays.asList(1, 3, 4));
        lists.add(Arrays.asList(2, 6));

        System.out.println(mergeKSortedLists(lists));
    }
}
