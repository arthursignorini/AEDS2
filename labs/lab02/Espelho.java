package labs.lab02;

import java.util.Scanner;

public class Espelho {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] vet = new int[100];
        int cont = 0;

        String input1;
        String input2;

        while (sc.hasNext()) {

            input1 = sc.next();
            input2 = sc.next();

            int num1 = Integer.parseInt(input1);
            int num2 = Integer.parseInt(input2);

            int aux;

            if (num1 > num2) {
                aux = num2;
                while (aux <= num1) {
                    System.out.print(aux);
                    vet[cont] = aux;
                    cont++;
                    aux++;
                }
                printar(cont, vet);
            } else {
                aux = num1;
                while (aux <= num2) {
                    System.out.print(aux);
                    vet[cont] = aux;
                    cont++;
                    aux++;
                }
                printar(cont, vet);
            }
            System.out.println();

            // Esvaziar o vetor
            limparVetor(vet);
            cont = 0; // Resetar o contador
        }

        sc.close();
    }

    public static void printar(int cont, int[] vet) {
        for (int i = cont - 1; i >= 0; i--) {
            String numeroStr = Integer.toString(vet[i]);
            // Inverter o número e imprimir
            String numeroEspelhado = new StringBuilder(numeroStr).reverse().toString();
            System.out.print(numeroEspelhado);
        }
    }

    public static void limparVetor(int[] vet) {
        for (int i = 0; i < vet.length; i++) {
            vet[i] = 0; // Define todos os valores do vetor como zero
        }
    }
}
