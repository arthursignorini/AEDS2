package labs.lab01;

import java.util.Scanner;

public class AquecimentoRecursivo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String string;
        while(!(string = scanner.nextLine()).equals("FIM")){
            int num = contar (string, 0);
            System.out.println(num);
            
        }
        scanner.close();
    }

    public static int contar(String string, int i) {
        if (i >= string.length()) {
            return 0;
        } else {
            if (string.charAt(i) >= 'A' && string.charAt(i) <= 'Z') {
                return 1 + contar(string, i + 1);
            } else {
                return contar(string, i + 1);
            }
        }
        
    }
}
