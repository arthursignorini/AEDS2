package labs.lab01;

import java.util.Scanner;

public class AquecimentoInterativo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String string;
        while(!(string = scanner.nextLine()).equals("FIM")){
            int num = contar(string);
            System.out.println(num);
            
        }
        scanner.close();
    }

    public static int contar(String string) {
        int tam = string.length();
        int cont = 0;
        for (int i = 0; i < tam; i++) {
            if (string.charAt(i) >= 'A' && string.charAt(i) <= 'Z') {
                cont++;
            }
        }
        return cont;
    }
}
