package beecrowd;

import java.util.Scanner;

public class AlfabetoAlienigena {
    public static boolean comparar(String alfa, String str, int n1, int n2) {
        int cont = 0;
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if (alfa.charAt(i) == str.charAt(j)) {
                    cont++;
                }
            }
        }
        return cont == n2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        scanner.nextLine(); // Consumir o caractere de nova linha após a entrada dos números

        String alfa = scanner.nextLine();
        String str = scanner.nextLine();

        boolean resposta = comparar(alfa, str, n1, n2);
        if (resposta) {
            System.out.println("S");
        } else {
            System.out.println("N");
        }

        scanner.close();
    }
}
