package labs.lab02;

import java.util.Scanner;

public class Espelho {
    public static void main(String[]args){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite um numero: ");
        int num1 = scanner.nextInt();

        System.out.println("Digite um outro numero: ");
        int num2 = scanner.nextInt();

        int aux = 0;
       
        if(num1 > num2){
            aux = num2;
            while(aux <= num1){
                System.out.print(aux);
                aux++;
            }
            aux--;
            while(aux >= num2){
                System.out.print(aux);
                aux--;
            }
        } else if(num2 > num1){
            aux = num1;
            while(aux <= num2){
                System.out.print(aux);
                aux++;
            }
            aux--;
            while(aux >= num1){
                System.out.print(aux);
                aux--;
            }
        }
        
    scanner.close();
    }
}