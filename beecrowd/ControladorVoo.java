package beecrowd;

import java.util.ArrayList;
import java.util.Scanner;

public class ControladorVoo {
    public static void main(String[]args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> oeste = new ArrayList<>();
        ArrayList<String> sul = new ArrayList<>();
        ArrayList<String> norte = new ArrayList<>();
        ArrayList<String> leste = new ArrayList<>();
        
       String direcao = "";

        String input;
        while  (!(input = sc.nextLine()).equals("0"))  {
            if(!(input.startsWith("A"))) {
                direcao = input;
            }

            if((input.startsWith("A"))) {
                if(direcao.equals("-1")) {
                    oeste.add(input);
                }

                if(direcao.equals("-2")) {
                    sul.add(input);
                }

                if(direcao.equals("-3")) {
                    norte.add(input);
                }

                if(direcao.equals("-4")) {
                    leste.add(input);
                }
            }
        }
        // oeste norte sul leste
        while (!(oeste.isEmpty() || sul.isEmpty() || norte.isEmpty() || leste.isEmpty())) {
            if (!oeste.isEmpty()) {
                System.out.print(oeste.get(0) + " ");
                oeste.remove(0);
            }
            if (!norte.isEmpty()) {
                System.out.print(norte.get(0) + " ");
                norte.remove(0);
            }
            if (!sul.isEmpty()) {
                System.out.print(sul.get(0) + " ");
                sul.remove(0);
            }
            if (!leste.isEmpty()) {
                System.out.print(leste.get(0) + " ");
                leste.remove(0);
            }
        }
        
        sc.close();
    }
}