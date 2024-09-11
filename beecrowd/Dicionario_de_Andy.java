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
            palavras.add(palavra);  
        }        
        
        Collections.sort(palavras);

        for (String palavra : palavras) {
            System.out.println(palavra);
        }

        sc.close();  
    }
}
