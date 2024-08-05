package redirecionamento;

import java.util.Scanner;

class ExemploMyIO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entre com uma palavra: ");
        String str = scanner.nextLine();

        System.out.print("Entre com um inteiro: ");
        int inteiro = scanner.nextInt();

        System.out.print("Entre com um real: ");
        double real = scanner.nextDouble();

        System.out.print("Entre com um caractere: ");
        char caractere = scanner.next().charAt(0);

        System.out.println("Sua string: " + str);
        System.out.println("Seu inteiro: " + inteiro);
        System.out.println("Seu real: " + real);
        System.out.println("Seu caractere: " + caractere);

        scanner.close();
    }
}
