package tps.tp1;

import java.util.Scanner;

public class CiframentoCesar {
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

        String string;
        while(!(string = scanner.nextLine()).equals("FIM")){
                String palavraCriptografada = criptografar(string);
                System.out.println(palavraCriptografada);
        }
        scanner.close();
    }     
}

