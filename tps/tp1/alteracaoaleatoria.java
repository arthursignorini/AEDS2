package tps.tp1;

import java.util.Random;
import java.util.Scanner;

public class alteracaoaleatoria {

    public static String alteracao(String frase) {
        
        Random gerador = new Random(); 
        
       
        char letraAleatoria = frase.charAt(gerador.nextInt(frase.length())); 
        char letraAleatoria2 = (char)('a' + gerador.nextInt(26)); 
        
        StringBuilder novaFrase = new StringBuilder(frase);
        int tam = frase.length();
        
        for (int i = 0; i < tam; i++) {
            if (letraAleatoria == frase.charAt(i)) {
                novaFrase.setCharAt(i, letraAleatoria2);
            }
        }
        
        return novaFrase.toString(); 
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String frase;
        while (!(frase = sc.nextLine()).equals("FIM")) {
            System.out.println(alteracao(frase));
        }

        sc.close();
    }
}
