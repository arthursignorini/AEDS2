package tps.tp1;

import java.util.Scanner;

public class palindromo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite uma string: ");
        String string = scanner.nextLine();

        if (conferir(string)) {
            System.out.println("A string é um palíndromo.");
        } else {
            System.out.println("A string não é um palíndromo.");
        }

        scanner.close();
    }

    public static boolean conferir(String string) {
        int n = string.length();
        for (int i = 0; i < n / 2; i++) {
            if (string.charAt(i) != string.charAt(n - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
