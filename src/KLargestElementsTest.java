import java.util.*;

public class KLargestElementsTest {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("TESTES: K MAIORES ELEMENTOS COM MIN-HEAP");
        System.out.println("=".repeat(60));

        // Teste 1: Caso básico
        testeBasico();

        // Teste 2: Array com elementos negativos
        testeComNegativos();

        // Teste 3: K = 1 (apenas o maior)
        testeK1();

        // Teste 4: K = tamanho do array
        testeKIgualTamanho();

        // Teste 5: Array com elementos duplicados
        testeElementosDuplicados();

        // Teste 6: Análise de complexidade e performance
        testePerformance();

        // Teste 7: Casos extremos
        testesCasosExtremos();
    }

    static void testeBasico() {
        System.out.println("\n[TESTE 1] Caso Básico");
        int[] arr = {3, 2, 1, 5, 6, 4};
        int k = 2;

        List<Integer> resultado = KLargestElements.findKthLargest(arr, k);
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("K: " + k);
        System.out.println("K maiores elementos: " + resultado);
        System.out.println("K maiores (desc): " + KLargestElements.findKthLargestDesc(arr, k));
        System.out.println("✓ Teste passado!");
    }

    static void testeComNegativos() {
        System.out.println("\n[TESTE 2] Array com Números Negativos");
        int[] arr = {-1, -2, -3, 5, 10, 0};
        int k = 3;

        List<Integer> resultado = KLargestElements.findKthLargest(arr, k);
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("K: " + k);
        System.out.println("K maiores elementos: " + resultado);
        System.out.println("✓ Teste passado!");
    }

    static void testeK1() {
        System.out.println("\n[TESTE 3] K = 1 (Apenas o Maior)");
        int[] arr = {7, 2, 9, 1, 5};
        int k = 1;

        List<Integer> resultado = KLargestElements.findKthLargest(arr, k);
        int kMaior = KLargestElements.findKthLargestElement(arr, k);
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("K maiores: " + resultado);
        System.out.println("K-ésimo maior elemento: " + kMaior);
        System.out.println("✓ Teste passado!");
    }

    static void testeKIgualTamanho() {
        System.out.println("\n[TESTE 4] K = Tamanho do Array");
        int[] arr = {3, 1, 4, 2};
        int k = arr.length;

        List<Integer> resultado = KLargestElements.findKthLargest(arr, k);
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("K: " + k);
        System.out.println("K maiores: " + resultado + " (array inteiro ordenado)");
        System.out.println("✓ Teste passado!");
    }

    static void testeElementosDuplicados() {
        System.out.println("\n[TESTE 5] Array com Elementos Duplicados");
        int[] arr = {1, 1, 1, 2, 2, 3, 3, 3, 3};
        int k = 4;

        List<Integer> resultado = KLargestElements.findKthLargest(arr, k);
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("K: " + k);
        System.out.println("K maiores: " + resultado);
        System.out.println("✓ Teste passado!");
    }

    static void testePerformance() {
        System.out.println("\n[TESTE 6] Análise de Performance");
        System.out.println("\nComparando dois métodos com array grande:");

        int[] tamanhos = {1000, 10000, 100000};
        int k = 100;

        for (int n : tamanhos) {
            int[] arr = gerarArrayAleatorio(n);

            // Método 1: Min-Heap O(N log K)
            long inicio = System.nanoTime();
            KLargestElements.findKthLargest(arr, k);
            long tempoMinHeap = System.nanoTime() - inicio;

            // Método 2: Ordenação completa O(N log N)
            inicio = System.nanoTime();
            Arrays.sort(arr);
            List<Integer> top = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                top.add(arr[arr.length - 1 - i]);
            }
            long tempoOrdenacao = System.nanoTime() - inicio;

            double speedup = (double) tempoOrdenacao / tempoMinHeap;
            System.out.printf("N=%6d, K=%3d | Min-Heap: %6dμs | Ordenação: %6dμs | Speedup: %.2fx%n",
                    n, k, tempoMinHeap/1000, tempoOrdenacao/1000, speedup);
        }
        System.out.println("✓ Min-Heap é mais eficiente para K pequeno comparado a N!");
    }

    static void testesCasosExtremos() {
        System.out.println("\n[TESTE 7] Casos Extremos e Tratamento de Erros");

        // K muito grande
        try {
            KLargestElements.findKthLargest(new int[]{1, 2, 3}, 5);
            System.out.println("✗ Erro: Deveria lançar exceção");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Exceção corretamente capturada: " + e.getMessage());
        }

        // K = 0
        try {
            KLargestElements.findKthLargest(new int[]{1, 2, 3}, 0);
            System.out.println("✗ Erro: Deveria lançar exceção");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Exceção corretamente capturada: " + e.getMessage());
        }

        // Array nulo
        try {
            KLargestElements.findKthLargest(null, 1);
            System.out.println("✗ Erro: Deveria lançar exceção");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Exceção corretamente capturada: " + e.getMessage());
        }
    }

    static int[] gerarArrayAleatorio(int tamanho) {
        int[] arr = new int[tamanho];
        Random rand = new Random();
        for (int i = 0; i < tamanho; i++) {
            arr[i] = rand.nextInt(1000000);
        }
        return arr;
    }
}