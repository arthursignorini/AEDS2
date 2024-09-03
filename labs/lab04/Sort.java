package labs.lab04;

import java.util.Scanner;

public class Sort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int tam = sc.nextInt();
            if (tam == 0) break;

            int modolo = sc.nextInt();
            int[] vet1 = lerArray(tam, sc);
            int[] vet2 = lerModolo(vet1, modolo);
            ordenar(vet2, vet1);
            conferir(vet1, vet2);
            printar(vet2);
        }

        sc.close();
    }

    // Lê um array de inteiros do Scanner
    public static int[] lerArray(int tam, Scanner sc) {
        int[] vet1 = new int[tam];
        for (int i = 0; i < tam; i++) {
            vet1[i] = sc.nextInt();
        }
        return vet1;
    }

    // Calcula os restos da divisão dos elementos por um módulo
    public static int[] lerModolo(int[] vet1, int modolo) {
        int tam = vet1.length;
        int[] vet2 = new int[tam];
        for (int i = 0; i < tam; i++) {
            vet2[i] = vet1[i] % modolo;
        }
        return vet2;
    }

    // Ordena vet2 e rearranja vet1 conforme vet2
    public static void ordenar(int[] vet2, int[] vet1) {
        int tam = vet2.length;
        for (int i = 0; i < tam - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < tam; j++) {
                if (vet2[j] < vet2[menor]) {
                    menor = j;
                }
            }
            swap(vet2, menor, i);
            swap(vet1, menor, i);
        }
    }

    // Troca os elementos nos índices i e j do array
    public static void swap(int[] vet, int i, int j) {
        int temp = vet[i];
        vet[i] = vet[j];
        vet[j] = temp;
    }

    // Reorganiza vet1 e vet2 conforme regras adicionais
    public static void conferir(int[] vet1, int[] vet2) {
        boolean trocou;
        int tam = vet2.length;
        do {
            trocou = false;
            for (int i = 0; i < tam - 1; i++) {
                if (vet2[i] == vet2[i + 1]) {
                    if (deveTrocarParImpar(vet1[i], vet1[i + 1])) {
                        swap(vet2, i, i + 1);
                        trocou = true;
                    } else if (deveTrocarImpares(vet1[i], vet1[i + 1])) {
                        swap(vet2, i, i + 1);
                        trocou = true;
                    } else if (deveTrocarPares(vet1[i], vet1[i + 1])) {
                        swap(vet2, i, i + 1);
                        trocou = true;
                    }
                }
            }
        } while (trocou);
    }

    // Verifica se deve trocar um número par que precede um ímpar
    private static boolean deveTrocarParImpar(int num1, int num2) {
        return num1 % 2 == 0 && num2 % 2 != 0;
    }

    // Verifica se deve trocar dois números ímpares
    private static boolean deveTrocarImpares(int num1, int num2) {
        return num1 % 2 != 0 && num2 % 2 != 0 && num1 < num2;
    }

    // Verifica se deve trocar dois números pares
    private static boolean deveTrocarPares(int num1, int num2) {
        return num1 % 2 == 0 && num2 % 2 == 0 && num1 < num2;
    }

    // Imprime os elementos de vet2
    public static void printar(int[] vet2) {
        for (int num : vet2) {
            System.out.println(num);
        }
    }
}