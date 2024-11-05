package beecrowd;

import java.util.*;

public class MenorPilha {
    public static void main(String[] args) {

        Stack<Integer> pilha = new Stack<>();
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        sc.nextLine(); 

        for (int i = 0; i < num; i++) {
            String str = sc.nextLine(); 

            if (str.equals("POP")) {
                
                if (!pilha.isEmpty()) {
                    pilha.pop();
                }
            } else if (str.equals("MIN")) {
                
                if (!pilha.isEmpty()) {
                    int min = pilha.get(0);
                    for (int j = 0; j < pilha.size(); j++) {
                        if (pilha.get(j) < min) {
                            min = pilha.get(j);
                        }
                    }
                    System.out.println(min);
                }
            } else if (str.startsWith("PUSH")) {

                int valor = Integer.parseInt(str.substring(5));
                pilha.push(valor);
            }
        }
        sc.close();
    }
}
