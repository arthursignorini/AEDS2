package unidade1.basicos;

import java.util.Scanner;

public class ex1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o tamanho do array: ");
        int tam = scanner.nextInt();

        int[] array = new int[tam];

        for (int i = 0; i < tam; i++) {  // Correção aqui
            System.out.print("Digite um valor para a posição " + i + ": ");
            array[i] = scanner.nextInt();
        }

        selecao(array, tam);

        // Imprimir o array ordenado
        System.out.println("Array ordenado:");
        for (int i = 0; i < tam; i++) {
            System.out.print(array[i] + " ");
        }

        scanner.close();
    }

    public static void selecao(int[] array, int tam) {
        int menor;
        for (int i = 0; i < tam - 1; i++) {
            menor = i;
            for (int j = i + 1; j < tam; j++) {
                if (array[menor] > array[j]) {
                    menor = j;  // Atualize o índice do menor elemento encontrado
                }
            }
            if (menor != i) {  // Troca apenas se necessário
                swap(array, i, menor);
            }
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
