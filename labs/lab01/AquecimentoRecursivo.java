package labs.lab01;

import java.util.Scanner;

public class AquecimentoRecursivo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite uma string: ");
        String string = scanner.nextLine();
        int num = contar(string, 0);
        System.out.println("Número de caracteres maiúsculos: " + num);
        scanner.close();
    }

    public static int contar(String string, int i) {
        if (i >= string.length()) {
            return 0;
        } else {
            if (string.charAt(i) >= 'A' && string.charAt(i) <= 'Z') {
                return 1 + contar(string, i + 1);
            } else {
                return contar(string, i + 1);
            }
        }
    }
}
