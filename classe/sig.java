

public class sig {

    // Método de Ordenação: Quicksort
    public static void quicksort(int[] arr, int baixo, int alto) {
        if (baixo < alto) {
            int pi = particionar(arr, baixo, alto);
            quicksort(arr, baixo, pi - 1);
            quicksort(arr, pi + 1, alto);
        }
    }

    private static int particionar(int[] arr, int baixo, int alto) {
        int pivo = arr[alto];
        int i = (baixo - 1);
        for (int j = baixo; j < alto; j++) {
            if (arr[j] <= pivo) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[alto];
        arr[alto] = temp;
        return i + 1;
    }

    // Método de Ordenação: Merge Sort
    public static void mergesort(int[] arr, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergesort(arr, inicio, meio);
            mergesort(arr, meio + 1, fim);
            merge(arr, inicio, meio, fim);
        }
    }

    private static void merge(int[] arr, int inicio, int meio, int fim) {
        int n1 = meio - inicio + 1;
        int n2 = fim - meio;
        int[] L = new int[n1];
        int[] R = new int[n2];
        System.arraycopy(arr, inicio, L, 0, n1);
        System.arraycopy(arr, meio + 1, R, 0, n2);
        int i = 0, j = 0;
        int k = inicio;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
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

    // Método de Ordenação: Heapsort
    public static void heapsort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int maior = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;
        if (esquerda < n && arr[esquerda] > arr[maior]) {
            maior = esquerda;
        }
        if (direita < n && arr[direita] > arr[maior]) {
            maior = direita;
        }
        if (maior != i) {
            int temp = arr[i];
            arr[i] = arr[maior];
            arr[maior] = temp;
            heapify(arr, n, maior);
        }
    }

    // Método de Ordenação: Bubble Sort
    public static void bubblesort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Método de Pesquisa Binária
    public static int pesquisaBinaria(int[] arr, int chave) {
        int baixo = 0;
        int alto = arr.length - 1;
        while (baixo <= alto) {
            int meio = baixo + (alto - baixo) / 2;
            if (arr[meio] == chave) {
                return meio;
            }
            if (arr[meio] < chave) {
                baixo = meio + 1;
            } else {
                alto = meio - 1;
            }
        }
        return -1; 
    }
}
