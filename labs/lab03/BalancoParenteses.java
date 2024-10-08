package labs.lab03;

import java.util.Scanner;

public class BalancoParenteses {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String str;

        while (!(str = sc.nextLine()).equals("FIM")) {

            if (conferir(str)) {
                System.out.println("correto");
            } else {
                System.out.println("incorreto");
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
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }
}
