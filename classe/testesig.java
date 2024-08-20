package classe;

// Arquivo: TesteSig.java
public class testesig {
    public static void main(String[] args) {
        int[] arr = { 64, 25, 12, 22, 11 };

        // Teste do Bubble Sort
        sig.bubblesort(arr);
        System.out.println("Array ordenado com Bubble Sort:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Teste do Quicksort
        int[] arr2 = { 64, 25, 12, 22, 11 };
        sig.quicksort(arr2, 0, arr2.length - 1);
        System.out.println("Array ordenado com Quicksort:");
        for (int num : arr2) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Teste do Merge Sort
        int[] arr3 = { 64, 25, 12, 22, 11 };
        sig.mergesort(arr3, 0, arr3.length - 1);
        System.out.println("Array ordenado com Merge Sort:");
        for (int num : arr3) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Teste do Heapsort
        int[] arr4 = { 64, 25, 12, 22, 11 };
        sig.heapsort(arr4);
        System.out.println("Array ordenado com Heapsort:");
        for (int num : arr4) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Teste da Pesquisa Binária
        int chave = 22;
        int[] arr5 = { 11, 12, 22, 25, 64 }; // Deve estar ordenado
        int resultado = sig.pesquisaBinaria(arr5, chave);
        if (resultado != -1) {
            System.out.println("Chave " + chave + " encontrada no índice " + resultado);
        } else {
            System.out.println("Chave " + chave + " não encontrada.");
        }
    }
}
