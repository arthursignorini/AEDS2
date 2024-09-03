package ordenacao;

import java.util.Scanner;

public class quicksort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o tamanho do vetor: ");
        int tam = sc.nextInt();

        int[] vet = new int[tam];

        for (int x : vet) {
            System.out.print("Digite um valor para o vetor: ");
            x = sc.nextInt();
        }

        quicksort(0, tam-1, vet);

        for (int x : vet) {
            System.out.print(x + " ");
        }


        sc.close();
    }

    public static void quicksort (int esq, int dir, int[] vet) {
        int i = esq, j = dir;
        int pivo = (esq + dir) / 2;

        while (i<=j) {
            while (vet[i] < pivo) {
                i++;
            }
            while (vet[j] > pivo) {
                j--;
            }
            if(i<=j) {
                swap(i,j,vet);
                i++;
                j--;
            }
        }
        if(esq < j) {
            quicksort(esq, j, vet);
        }
        if(i < dir) {
            quicksort(i, dir, vet);
        }
    }

    public static void swap (int i, int j, int[] vet) {
        int temp = vet[i];
        vet[i] = vet[j];
        vet[j] = temp;
    }
}
