package maratona;

import java.util.Scanner;

public class ex1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        int duracao = sc.nextInt();
        int conta = 0;
        if(num == 1) {
            System.out.println(duracao);
        } else {
            conta = ( duracao / num ) - 1;
            System.out.println(conta);
        }
    }
}