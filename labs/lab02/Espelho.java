package labs.lab02;

import java.util.Scanner;

public class Espelho {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input1 = sc.next();
            String input2 = sc.next();

            if (input1.equals(" ") || input2.equals(" ")) {
                break;
            }

            int aux = 0;

            int num1 = Integer.parseInt(input1);
            int num2 = Integer.parseInt(input2);

            if (num1 > num2) {
                aux = num2;
                while (aux <= num1) {
                    System.out.print(aux);
                    aux++;
                }
                aux--;
                while (aux >= num2) {
                    System.out.print(aux);
                    aux--;
                }
            } else if (num2 > num1) {
                aux = num1;
                while (aux <= num2) {
                    System.out.print(aux);
                    aux++;
                }
                aux--;
                while (aux >= num1) {
                    System.out.print(aux);
                    aux--;
                }
            }

        }

        sc.close();
    }
}