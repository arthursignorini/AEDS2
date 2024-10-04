import java.util.Arrays;
import java.util.Random;

public class QuickSortVariacoes {

    // QuickSort com o primeiro elemento como pivô
    public static void quickSortPrimeiroPivo(int[] array, int esquerda, int direita) {
        if (esquerda < direita) {
            int indicePivo = particionaPrimeiroPivo(array, esquerda, direita);
            quickSortPrimeiroPivo(array, esquerda, indicePivo - 1);
            quickSortPrimeiroPivo(array, indicePivo + 1, direita);
        }
    }

    private static int particionaPrimeiroPivo(int[] array, int esquerda, int direita) {
        int pivo = array[esquerda];
        int i = esquerda + 1;

        for (int j = esquerda + 1; j <= direita; j++) {
            if (array[j] < pivo) {
                trocar(array, i, j);
                i++;
            }
        }
        trocar(array, esquerda, i - 1);
        return i - 1;
    }

    // QuickSort com o último elemento como pivô
    public static void quickSortUltimoPivo(int[] array, int esquerda, int direita) {
        if (esquerda < direita) {
            int indicePivo = particionaUltimoPivo(array, esquerda, direita);
            quickSortUltimoPivo(array, esquerda, indicePivo - 1);
            quickSortUltimoPivo(array, indicePivo + 1, direita);
        }
    }

    private static int particionaUltimoPivo(int[] array, int esquerda, int direita) {
        int pivo = array[direita];
        int i = esquerda - 1;

        for (int j = esquerda; j < direita; j++) {
            if (array[j] < pivo) {
                i++;
                trocar(array, i, j);
            }
        }
        trocar(array, i + 1, direita);
        return i + 1;
    }

    // QuickSort com pivô aleatório
    public static void quickSortPivoAleatorio(int[] array, int esquerda, int direita) {
        if (esquerda < direita) {
            int indiceAleatorio = new Random().nextInt(direita - esquerda + 1) + esquerda;
            trocar(array, indiceAleatorio, direita);
            int indicePivo = particionaUltimoPivo(array, esquerda, direita);
            quickSortPivoAleatorio(array, esquerda, indicePivo - 1);
            quickSortPivoAleatorio(array, indicePivo + 1, direita);
        }
    }

    // QuickSort com a mediana de três elementos como pivô
    public static void quickSortMedianaDeTres(int[] array, int esquerda, int direita) {
        if (esquerda < direita) {
            int indiceMediana = medianaDeTres(array, esquerda, direita);
            trocar(array, indiceMediana, direita);
            int indicePivo = particionaUltimoPivo(array, esquerda, direita);
            quickSortMedianaDeTres(array, esquerda, indicePivo - 1);
            quickSortMedianaDeTres(array, indicePivo + 1, direita);
        }
    }

    private static int medianaDeTres(int[] array, int esquerda, int direita) {
        int meio = esquerda + (direita - esquerda) / 2;

        if (array[esquerda] > array[meio]) trocar(array, esquerda, meio);
        if (array[esquerda] > array[direita]) trocar(array, esquerda, direita);
        if (array[meio] > array[direita]) trocar(array, meio, direita);

        return meio;
    }

    // Método para trocar elementos do array
    private static void trocar(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] tamanhos = {100, 1000, 10000};
        for (int tamanho : tamanhos) {
            System.out.println("\n--------------------------------------");
            System.out.println("Testando com tamanho de array: " + tamanho);
            System.out.println("--------------------------------------");

            int[] arrayOrdenado = gerarArrayOrdenado(tamanho);
            int[] arrayQuaseOrdenado = gerarArrayQuaseOrdenado(tamanho);
            int[] arrayAleatorio = gerarArrayAleatorio(tamanho);

            testarEImprimir(arrayOrdenado, "Array Ordenado", tamanho);
            testarEImprimir(arrayQuaseOrdenado, "Array Quase Ordenado", tamanho);
            testarEImprimir(arrayAleatorio, "Array Aleatório", tamanho);
        }
    }

    private static void testarEImprimir(int[] arrayOriginal, String tipoArray, int tamanho) {
        System.out.println("\n" + tipoArray + ":");

        int[] array;

        // Testar QuickSort com Primeiro Pivô
        array = arrayOriginal.clone();
        long tempoInicio = System.nanoTime();
        quickSortPrimeiroPivo(array, 0, array.length - 1);
        long tempoFim = System.nanoTime();
        System.out.println("QuickSort Primeiro Pivô: " + (tempoFim - tempoInicio) / 1e6 + " ms");

        // Testar QuickSort com Último Pivô
        array = arrayOriginal.clone();
        tempoInicio = System.nanoTime();
        quickSortUltimoPivo(array, 0, array.length - 1);
        tempoFim = System.nanoTime();
        System.out.println("QuickSort Último Pivô: " + (tempoFim - tempoInicio) / 1e6 + " ms");

        // Testar QuickSort com Pivô Aleatório
        array = arrayOriginal.clone();
        tempoInicio = System.nanoTime();
        quickSortPivoAleatorio(array, 0, array.length - 1);
        tempoFim = System.nanoTime();
        System.out.println("QuickSort Pivô Aleatório: " + (tempoFim - tempoInicio) / 1e6 + " ms");

        // Testar QuickSort com Mediana de Três
        array = arrayOriginal.clone();
        tempoInicio = System.nanoTime();
        quickSortMedianaDeTres(array, 0, array.length - 1);
        tempoFim = System.nanoTime();
        System.out.println("QuickSort Mediana de Três: " + (tempoFim - tempoInicio) / 1e6 + " ms");
    }

    // Método para gerar um array ordenado
    private static int[] gerarArrayOrdenado(int tamanho) {
        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = i;
        }
        return array;
    }

    // Método para gerar um array quase ordenado
    private static int[] gerarArrayQuaseOrdenado(int tamanho) {
        int[] array = gerarArrayOrdenado(tamanho);
        Random rand = new Random();
        for (int i = 0; i < tamanho / 10; i++) { 
            int indice1 = rand.nextInt(tamanho);
            int indice2 = rand.nextInt(tamanho);
            trocar(array, indice1, indice2);
        }
        return array;
    }

    // Método para gerar um array aleatório
    private static int[] gerarArrayAleatorio(int tamanho) {
        int[] array = new int[tamanho];
        Random rand = new Random();
        for (int i = 0; i < tamanho; i++) {
            array[i] = rand.nextInt(tamanho);
        }
        return array;
    }
}
