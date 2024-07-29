import java.util.Scanner;

public class MediaNumeros {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        System.out.print("Digite o primeiro numero: ");
        int num1 = scanner.nextInt();

        System.out.print("Digite o segundo numero: ");
        int num2 = scanner.nextInt();

        int soma = num1 + num2;
        float media = soma / 2;

        System.out.println("A media desses dois numeros é de: " + media);

        scanner.close();
    }
}
