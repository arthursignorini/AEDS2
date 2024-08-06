package labs.lab01;

import java.util.Scanner;

public class AquecimentoInterativo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite uma string: ");
        String string = scanner.nextLine();
        int num = contar(string);
        System.out.println("Número de caracteres maiúsculos: " + num);
        scanner.close();
    }

    public static int contar(String string) {
        int tam = string.length();
        int cont = 0;
        for (int i = 0; i < tam; i++) {
            if (string.charAt(i) >= 'A' && string.charAt(i) <= 'Z') {
                cont++;
            }
        }
        return cont;
    }
}
