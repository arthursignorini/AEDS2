package beecrowd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Dicionario_de_Andy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> palavras = new ArrayList<>(); 
        
        while (sc.hasNext()) {
            String palavra = sc.next().toLowerCase();
            for(int i=0; i<palavra.length(); i++) {
                if(palavra.charAt(i) < 'a' && palavra.charAt(i) > 'z') {
                    tratar(palavra);
                }
            }
            palavras.add(palavra);  
        }        
        
        Collections.sort(palavras);

        for (String palavra : palavras) {
            System.out.println(palavra);
        }

        sc.close();  
    }

    public static String tratar(String palavra) {
        StringBuilder result = new StringBuilder();
        for(int i=0; i<palavra.length(); i++) {
            if(Character.isLetter(palavra.charAt(i))) {
                result.append(palavra.charAt(i)); 
            }
        }
        return result.toString();
    }
}
