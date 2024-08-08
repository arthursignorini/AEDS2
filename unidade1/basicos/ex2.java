package unidade1.basicos;

public class ex2 {
    public static void main(String[] args) {
        // Cria um array de inteiros para demonstrar o Bubble Sort
        int[] array = {64, 25, 12, 22, 11};
        
        // Exibe o array antes de ordenar
        System.out.println("Array antes de ordenar:");
        printArray(array);
        
        // Ordena o array usando Bubble Sort
        bubbleSort(array);
        
        // Exibe o array após ordenar
        System.out.println("Array após ordenar:");
        printArray(array);
        
        // Cria uma instância da classe Ex2 e chama o método ex2()
        Ex2 obj = new Ex2();
        obj.ex2();
    }

    // Método para ordenar o array usando Bubble Sort
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Troca array[j] e array[j + 1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Método auxiliar para imprimir o array
    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

// Classe Ex2 com o método ex2()
class Ex2 {
    // Método que será chamado pela classe principal
    public void ex2() {
        System.out.println("Método ex2() chamado com sucesso!");
    }
}
