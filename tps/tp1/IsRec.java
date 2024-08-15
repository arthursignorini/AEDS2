package tps.tp1;

import java.util.Scanner;

public class IsRec {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

    String palavra;

    while(!(palavra = sc.nextLine()).equals("FIM")){
        int tam = palavra.length();
            System.out.print(x1(palavra.toLowerCase(), tam, 0) ? "SIM " : "NAO ");
            System.out.print(x2(palavra.toLowerCase(), tam, 0) ? "SIM " : "NAO ");
            System.out.print(x3(palavra.toLowerCase(), tam, 0) ? "SIM " : "NAO ");
            System.out.println(x4(palavra.toLowerCase(), tam, 0, false) ? "SIM " : "NAO ");
    }

    sc.close();
    }

    public static boolean x1(String palavra, int tam, int i) {
        if(i == tam){
            return true;
        }
        char c = palavra.charAt(i);
        if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') {
            return false;
        }
        return x1(palavra, tam, i+1);
    }

    public static boolean x2(String palavra, int tam, int i) {
        if(i == tam){
            return true;
        }
        char c = palavra.charAt(i);
        if (!Character.isLetter(c) || c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' ) {
            return false;
        }
        return x2(palavra, tam, i+1);
    }

    public static boolean x3(String palavra, int tam, int i) {
        if(i == tam){
            return true;
        }
        char c = palavra.charAt(i);
        if (c < '0' || c > '9') {
            return false;
        }
        return x3(palavra, tam, i+1);
    }

    public static boolean x4(String str, int tam, int i, boolean pontoEncontrado) {
        if (i == tam) {
            return pontoEncontrado; 
        }
        char c = str.charAt(i);
        if (c == '.' || c == ',') {
            if (pontoEncontrado) {
                return false;
            }
            pontoEncontrado = true; 
        } else if (c < '0' || c > '9') { 
            return false;
        }
        return x4(str, tam, i + 1, pontoEncontrado);
    } 
}