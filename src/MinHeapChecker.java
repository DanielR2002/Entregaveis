public class MinHeapChecker {

    public static boolean isMinHeap(int[] arr) {
        int n = arr.length;

        // Percorre todos os nós que têm ao menos 1 filho
        for (int i = 0; i < n; i++) {

            int left = 2 * i + 1;
            int right = 2 * i + 2;

            // Verifica filho esquerdo
            if (left < n && arr[i] > arr[left]) {
                return false;
            }

            // Verifica filho direito
            if (right < n && arr[i] > arr[right]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] heapValido = {1, 3, 5, 7, 9, 8};
        int[] heapInvalido = {10, 3, 5, 7, 9, 8};

        System.out.println(isMinHeap(heapValido));   // true
        System.out.println(isMinHeap(heapInvalido)); // false
    }
}
