package labs.lab02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Espelho {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int num1 = sc.nextInt();
            int num2 = sc.nextInt();

            int menor = Math.min(num1, num2);
            int maior = Math.max(num1, num2);

            List<Integer> numeros = new ArrayList<>();

            // Preencher a lista com números entre menor e maior
            for (int i = menor; i <= maior; i++) {
                System.out.print(i);
                numeros.add(i);
            }

            // Imprimir os números espelhados
            printar(numeros);
            System.out.println();
        }

        sc.close();
    }

    public static void printar(List<Integer> numeros) {
        for (int i = numeros.size() - 1; i >= 0; i--) {
            String numeroStr = Integer.toString(numeros.get(i));
            // Inverter o número e imprimir
            String numeroEspelhado = new StringBuilder(numeroStr).reverse().toString();
            System.out.print(numeroEspelhado);
        }
    }
}
