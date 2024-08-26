import java.util.Scanner;

public class BalancoParenteses {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String str;

        while (!(str = sc.nextLine()).equals("FIM")) {

            // Contar parênteses
            int cont1 = 0, cont2 = 0;

            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(') {
                    cont1++;
                }
                if (str.charAt(i) == ')') {
                    cont2++;
                }
            }

            // Verificar se o número de parênteses abertos é igual ao número de parênteses fechados
            if (cont1 != cont2) {
                System.out.println("incorreto");
            } else {
                // Verificar o equilíbrio dos parênteses ao longo da string
                if (conferir(str)) {
                    System.out.println("correto");
                } else {
                    System.out.println("incorreto");
                }
            }
        }

        sc.close();
    }

    public static boolean conferir(String str) {
        int balance = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                balance++;
            }
            if (str.charAt(i) == ')') {
                balance--;
            }
            // Se o balance for negativo, significa que há mais parênteses fechados do que abertos
            if (balance < 0) {
                return false;
            }
        }
        // Se o balance for zero, os parênteses estão balanceados
        return balance == 0;
    }
}
