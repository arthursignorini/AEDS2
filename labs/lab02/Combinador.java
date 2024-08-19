package labs.lab02;

import java.util.Scanner;

public class Combinador {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            String string1 = sc.next();
            String string2 = sc.next();
            
          
            if (string1.equals(" ") || string2.equals(" ")) {
                break;
            }

            int tam1 = string1.length();
            int tam2 = string2.length();

            if (tam1 < tam2) {
                for (int i = 0; i < tam1; i++) {
                    System.out.print(string1.charAt(i));
                    System.out.print(string2.charAt(i));
                }
                for (int i = tam1; i < tam2; i++) {
                    System.out.print(string2.charAt(i));
                }
            } else {
                for (int i = 0; i < tam2; i++) {
                    System.out.print(string1.charAt(i));
                    System.out.print(string2.charAt(i));
                }
                for (int i = tam2; i < tam1; i++) {
                    System.out.print(string1.charAt(i));
                }
            }
        }
        sc.close();
    }
}
