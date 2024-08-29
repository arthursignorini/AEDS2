package provas_resolvidas.p3;

import java.util.Scanner;

public class ex01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite uma string de parênteses: ");
        String str = sc.nextLine();

        int cont1 = 0; // Contador para '('
        int cont2 = 0; // Contador para ')'

        for (int i=0; i<str.length(); i++) {
            if (str.charAt(i) == '(') {
                cont1++;
            } else if (str.charAt(i) == ')') {
                cont2++;
            }
        }
        
        int conta = cont1 - cont2;
        if(conta > 0) {
            System.out.println("Ainda temos " + conta + "assuntos pendentes");
        } else {
            System.out.println("PARTIU RU!");
        }
        sc.close();
    }
}
