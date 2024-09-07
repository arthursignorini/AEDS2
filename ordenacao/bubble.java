package ordenacao;

import java.util.Scanner;

public class bubble {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int tam;
        System.out.print("Digite o tamanho do vetor: ");
        tam = sc.nextInt();
        int[] vet = new int[tam];

        for(int i=0; i<tam; i++) {
            System.out.print("Digite um valor para o vetor: ");
            vet[i] = sc.nextInt();
        }

        boolean trocou = true;
        while(trocou) {
            for(int i=0; i<tam-1 && trocou; i++) {
                trocou = false;
                for(int j=0; j<tam-1-i; j++) {
                    if(vet[j] > vet[j+1]) {
                        int temp = vet[j+1];
                        vet[j+1] = vet[j];
                        vet[j] = temp;
                        trocou = true;
                    }
                }
            }
        }
        for(int i=0; i<tam; i++) {
            System.out.println(vet[i]);
        }
    }    
}