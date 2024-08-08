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


        System.out.print("\nDigite o numero que voce quer encontrar: ");
        int num = scanner.nextInt();

        if(find(array, tam, num)){
            System.out.println("ESSA NUMERO FOI ENCONTRADO NO ARRAY");
        } else {
            System.out.println("ESSE NUMERO NAO FOI ENCONTRADO NO ARRAY");
        }
        

        scanner.close();
    }

    public static void selecao(int[] array, int tam) {
        int menor;
        for (int i = 0; i < tam - 1; i++) {
            menor = i;
            for (int j = i + 1; j < tam; j++) {
                if (array[menor] > array[j]) {
                    menor = j;  
                }
            }
            if (menor != i) {  
                swap(array, i, menor);
            }
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static boolean find (int[] array, int tam, int num) {
       
        int direita = tam - 1;
        int esquerda = 0;
        boolean resp = false;

        while(esquerda <= direita){
            int meio = (esquerda + direita) / 2;
            int diferenca = (num - array[meio]);
            if(diferenca == 0){
                resp = true;
                return resp;
            }
            if(diferenca > 0){
                esquerda = meio + 1;
            }
            if(diferenca < 0){
                direita = meio - 1;
            }
        }
        return resp;

    }
}
