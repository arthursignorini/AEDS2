package beecrowd;

import java.util.Scanner;

public class Teclado_Quebrado {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String str = sc.nextLine();

            StringBuilder inicio = new StringBuilder();
            StringBuilder fim = new StringBuilder();

            boolean addInicio = false;

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);

                if (ch == '[') {
                    addInicio = true; 
                } else if (ch == ']') {
                    addInicio = false;  
                } else {
                    if (addInicio) {
                        inicio.insert(0, ch);  
                    } else {
                        fim.append(ch);  
                    }
                }
            }
            inicio.append(fim);
            System.out.println(inicio.toString());
        }
        sc.close();
    }
}
