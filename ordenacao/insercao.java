package ordenacao;

import java.util.Scanner;

public class insercao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o tamanho do vetor: ");
        int tam = sc.nextInt();

        int[] vet = new int[tam];

        for (int i = 0; i < tam; i++) {
            System.out.print("Digite um valor para o vetor: ");
            vet[i] = sc.nextInt();
        }

        insercaosort(vet, tam);

        System.out.print("Vetor ordenado: ");
        for (int i = 0; i < tam; i++) {
            System.out.print(vet[i] + " ");
        }
    }

    public static void insercaosort (int[] vet, int tam) {
        for (int i = 1; i < tam; i++) {
            int temp = vet[i];
            int j = i - 1;

            while (j >= 0 && vet[j] > temp) {
                vet[j + 1] = vet[j];
                j--;
            }
            vet[j + 1] = temp;
        }
    }
}
