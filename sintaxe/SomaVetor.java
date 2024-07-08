import java.util.Scanner;

public class SomaVetor {
    public static void main(String[] args) {
        // Cria um objeto Scanner para receber a entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Solicita ao usuário o tamanho do vetor
        System.out.print("Digite a quantidade de números que deseja somar: ");
        int tamanho = scanner.nextInt();

        // Cria um vetor para armazenar os números
        int[] numeros = new int[tamanho];

        // Solicita ao usuário que insira os números
        for (int i = 0; i < tamanho; i++) {
            System.out.print("Digite o número " + (i + 1) + ": ");
            numeros[i] = scanner.nextInt();
        }

        // Calcula a soma dos números no vetor
        int soma = 0;
        for (int i = 0; i < tamanho; i++) {
            soma += numeros[i];
        }

        // Exibe o resultado da soma
        System.out.println("A soma dos números é: " + soma);

        // Fecha o Scanner
        scanner.close();
    }
}
