package tps.tp1;

import java.util.Scanner;

public class Is {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str;
        while (!(str = sc.nextLine()).equals("FIM")) {
            int tam = str.length();
            System.out.print(x1(str.toLowerCase(), tam) ? "SIM " : "NAO ");
            System.out.print(x2(str.toLowerCase(), tam) ? "SIM " : "NAO ");
            System.out.print(x3(str.toLowerCase(), tam) ? "SIM " : "NAO ");
            System.out.println(x4(str.toLowerCase(), tam) ? "SIM " : "NAO ");
        }

        sc.close();
    }

    public static boolean x1(String str, int tam) {
        for (int i = 0; i < tam; i++) {
            char c = str.charAt(i);
            if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') {
                return false;
            }
        }
        return true;
    }

    public static boolean x2(String str, int tam) {
        for (int i = 0; i < tam; i++) {
            char c = str.charAt(i);
            if (!Character.isLetter(c) || c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' ) {
                return false;
            }
        }
        return true;
    }

    public static boolean x3(String str, int tam) {
        if (tam == 0) {
            return false;
        }
        for (int i = 0; i < tam; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static boolean x4(String str, int tam) {
        boolean pontoEncontrado = false;
        if (tam == 0) {
            return false;
        }
        for (int i = 0; i < tam; i++) {
            char c = str.charAt(i);
            if (c == '.' || c == ',') {
                if (pontoEncontrado) {
                    return false;
                }
                pontoEncontrado = true;
            } else if (c < '0' || c > '9') {
                return false;
            }
        }
        return pontoEncontrado;
    }
}
