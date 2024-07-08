import java.util.Scanner;

public class SomaMatriz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a quantidade de linhas da matriz: ");
        int linhas = scanner.nextInt();

        System.out.print("Digite o numero de colunas: ");
        int colunas = scanner.nextInt();

        int[][] matriz = new int[linhas][colunas];

        for (int i = 0; i < linhas; i++){
            for (int j = 0; j < colunas; j++) {
                System.out.print("Digite um valor para a matriz: ");
                matriz[i][j] = scanner.nextInt();

            }
        }

        int soma = 0;
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                soma += matriz[i][j];
            }
        }

        System.out.print("A soma dos numeros da matriz é: " + soma);

        scanner.close();
    }
}
