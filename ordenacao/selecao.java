package ordenacao;

import java.util.Scanner;

public class selecao {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Digite o tamanho do vetor: ");
            int tam = sc.nextInt();

            int[] vet = new int[tam];

            for (int i = 0; i < tam; i++) {
                System.out.print("Digite um valor para o vetor: ");
                vet[i] = sc.nextInt();
            }

            selecaosort(vet, tam);

            System.out.print("Vetor ordenado: ");
            for (int i = 0; i < tam; i++) {
                System.out.print(vet[i] + " ");
            }
        }
    }

    public static void selecaosort (int[] vet, int tam) {
        for(int i=0; i<tam-1; i++) {
            int menor = i;
            for(int j=i+1; j<tam; j++) {
                if(vet[menor] > vet[j]) {
                    menor = j;
                }
            }
            swap(menor, i, vet);
        }
    }

    public static void swap (int i, int j, int[] vet) {
        int temp = vet[i];
        vet[i] = vet[j];
        vet[j] = temp;
    }
}
