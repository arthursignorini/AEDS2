package tps.tp1;

import java.util.Scanner;

public class Q03 {
    public static String criptografar(String palavra) {
        if (palavra.isEmpty()) {
            return "";
        }

        char primeiroChar = palavra.charAt(0);
        int letra = (int) primeiroChar;

        letra = letra + 3;

        if (letra > 126) {
            letra = 32 + (letra - 127);
        }

        char charCriptografado = (char) letra;

        return charCriptografado + criptografar(palavra.substring(1));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Digite uma palavra para criptografar (ou 'FIM' para sair):");
            String palavra = scanner.nextLine();

            if (palavra.equals("FIM")) {
                break;
            } else {
                String palavraCriptografada = criptografar(palavra);
                System.out.println("Palavra criptografada: " + palavraCriptografada);
            }
        }
        scanner.close();
    }
}
