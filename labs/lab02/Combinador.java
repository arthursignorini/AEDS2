package labs.lab02;

import java.util.Scanner;

public class Combinador {
    
    public static void main(String[]args){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite uma string: ");
        String string1 = scanner.nextLine();

        System.out.print("Digite outra string: ");
        String string2 = scanner.nextLine();

        int tam1 = string1.length();
        int tam2 = string2.length();

        if(tam1 < tam2) {
            for(int i=0; i<tam1; i++){
                System.out.print(string1.charAt(i));
                System.out.print(string2.charAt(i));
            }
            for(int i=tam1; i<tam2; i++){
                System.out.print(string2.charAt(i));
            } 
        } else {
            for(int i=0; i<tam2; i++){
                System.out.print(string1.charAt(i));
                System.out.print(string2.charAt(i));
            }
            for(int i=tam2; i<tam1; i++){
                System.out.print(string1.charAt(i));
            } 
        }
    }
}
