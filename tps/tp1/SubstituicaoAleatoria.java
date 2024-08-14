package tps.tp1;

import java.util.Random;
import java.util.Scanner;

public class SubstituicaoAleatoria {

    public static String substituirLetras(String frase) {
        Random gerador = new Random();
        gerador.setSeed(4);
        
        // Gerando duas letras minúsculas aleatórias
        char letraAleatoria1 = (char)('a' + gerador.nextInt(26));
        char letraAleatoria2 = (char)('a' + gerador.nextInt(26));
        
        // Criando um StringBuilder para modificar a string
        StringBuilder novaFrase = new StringBuilder(frase);

        // Substituindo todas as ocorrências de letraAleatoria1 por letraAleatoria2
        for (int i = 0; i < novaFrase.length(); i++) {
            if (novaFrase.charAt(i) == letraAleatoria1) {
                novaFrase.setCharAt(i, letraAleatoria2);
            }
        }

        return novaFrase.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String frase;
        while (!(frase = sc.nextLine()).equals("FIM")) {
            System.out.println(substituirLetras(frase));
        }

        sc.close();
    }
}
