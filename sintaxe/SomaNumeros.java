import java.util.Scanner;

public class SomaNumeros {
    public static void main(String[] args) {
        // Cria um objeto Scanner para receber a entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Solicita ao usuário que insira o primeiro número
        System.out.print("Digite o primeiro número: ");
        int numero1 = scanner.nextInt();

        // Solicita ao usuário que insira o segundo número
        System.out.print("Digite o segundo número: ");
        int numero2 = scanner.nextInt();

        // Calcula a soma dos dois números
        int soma = numero1 + numero2;

        // Exibe o resultado da soma
        System.out.println("A soma dos dois números é: " + soma);

        // Fecha o Scanner
        scanner.close();
    }
}
