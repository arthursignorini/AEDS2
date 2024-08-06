package tps.tp1;

import java.util.Scanner;

public class CiframentoCesar {
    public static void main(String[]args){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite uma string");
        String string = scanner.nextLine();

        mudar(string);

        scanner.close();

    }

    public static void mudar(String string){
        int x = string.length();

        for(int i=0; i<x; i++){
            char c = (char) (string.charAt(i) + 3); 
            System.out.print(c);
        }
    }
}
