package unidade1.conceitos_basicos;

import java.util.Scanner;

public class PesquisaBinaria {

    public static boolean verificar(int vet[], int tam, int x) {
        boolean resp = false;
        int direita = tam-1;
        int esquerda = 0;
        int meio;
        int diferenca;
        
        while(esquerda <= direita) {
            meio = (esquerda + direita) / 2;
            diferenca = (x - vet[meio]);
            if (diferenca == 0) {
                resp = true;
                esquerda = tam;
            } else if (diferenca > 0) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }
        return resp;
    }
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o tamanho do array: ");
        int tam = scanner.nextInt();

        int [] vet = new int [tam];

        for(int i=0; i<tam; i++) {
            System.out.println("Digite um valor para a posicao " + i);
            vet[i] = scanner.nextInt();
        }

        int temp = 0;
        for(int i=0; i<tam-1; i++){
            for(int j=0; j<tam-i-1; j++) {
                if(vet[j] > vet[j+1]) {
                    temp = vet[j+1];
                    vet[j+1] = vet[j];
                    vet[j] = temp;
                }
            }
        }

        System.out.println("Digite o numero que voce quer encontrar: ");
        int x = scanner.nextInt();

        if(verificar(vet, tam, x)){
            System.out.println("O numero foi encontrado");
        } else {
            System.out.println("Nao foi encontrado o numero");
        }
        scanner.close();
    }
}

