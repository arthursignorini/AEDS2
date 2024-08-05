package unidade1.conceitos_basicos;

import java.util.Scanner;

public class Selecao {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o tamanho do array: ");
        int tam = scanner.nextInt();

        int [] vet = new int [tam];

        for(int i=0; i<tam; i++) {
            System.out.println("Digite um valor para a posicao " + i);
            vet[i] = scanner.nextInt();
        }

        ordem(vet, tam);
        for(int i=0; i<tam; i++){
            System.out.println(vet[i]);
        }

        scanner.close();
    }

    public static void ordem(int vet[], int tam) {
        for(int i=0; i<tam-1; i++){
            int menor = i;
            for(int j=i+1; j<tam; j++){
                if(vet[menor] > vet[j]) {
                    int temp = vet[menor];
                    vet[menor] = vet[j];
                    vet[j] = temp;
                    menor = j;
                }
            }
        }
    }
}
