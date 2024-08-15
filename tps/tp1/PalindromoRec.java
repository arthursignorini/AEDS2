package tps.tp1;

import java.util.Scanner;

public class PalindromoRec {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String string;

        while (!(string = sc.nextLine()).equals("FIM")) {
            if (palindromo(string, 0, string.length() - 1)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
        }
        sc.close();
    }

    public static boolean palindromo(String string, int i, int j) {
        if (i >= j) {
            return true;
        } else if (string.charAt(i) != string.charAt(j)) {
            return false;
        } else {
            return palindromo(string, i + 1, j - 1);
        }
    }
}
