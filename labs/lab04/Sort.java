package labs.lab04;

import java.util.Scanner;

public class Sort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tam, modolo;
        int[] vet1;
        int[] vet2;

        while((tam = sc.nextInt()) != 0) {
            modolo = sc.nextInt();
            vet1 = new int[tam];

            for(int i = 0; i < tam; i++) {
                vet1[i] = sc.nextInt();
            }
            vet2 = new int[tam];
            for(int i = 0; i < tam; i++) { 
                vet2[i] = vet1[i] % modolo;
            }
            
            ordenar(tam, vet2, vet1);
            conferir(vet1, vet2, tam);

            for(int i = 0; i < tam; i++) {
                System.out.println(vet2[i]);
            }
        }

        sc.close();
    }
    
    public static void ordenar(int tam, int[] vet2, int[] vet1) {
        for(int i = 0; i < tam - 1; i++) {
            int menor = i;
            for(int j = i + 1; j < tam; j++) {  
                if(vet2[j] < vet2[menor]) {
                    menor = j;
                }
            }
            swap(vet2, menor, i);
            swap(vet1, menor, i);
        }
    }

    public static void swap(int[] vet, int i, int j) {
        int temp = vet[i];
        vet[i] = vet[j];
        vet[j] = temp;
    }

    public static void conferir(int[] vet1, int[] vet2, int tam) {
        boolean trocou;
        do {
            trocou = false;
            for (int i = 0; i < tam - 1; i++) {
                // Se os restos são iguais, verificar as condições de paridade
                if (vet2[i] == vet2[i + 1]) {
                    // Se o número atual é par e o próximo é ímpar, troque
                    if (vet1[i] % 2 == 0 && vet1[i + 1] % 2 != 0) {
                        swap(vet2, i, i + 1);
                        swap(vet1, i, i + 1);
                        trocou = true;
                    }
                    // Se ambos são ímpares, coloque o maior ímpar na frente
                    else if (vet1[i] % 2 != 0 && vet1[i + 1] % 2 != 0 && vet1[i] < vet1[i + 1]) {
                        swap(vet2, i, i + 1);
                        swap(vet1, i, i + 1);
                        trocou = true;
                    }
                    // Se ambos são pares, coloque o maior par na frente
                    else if (vet1[i] % 2 == 0 && vet1[i + 1] % 2 == 0 && vet1[i] < vet1[i + 1]) {
                        swap(vet2, i, i + 1);
                        swap(vet1, i, i + 1);
                        trocou = true;
                    }
                }
            }
        } while (trocou);
    }    
}
