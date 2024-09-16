package beecrowd;

import java.util.*;

public class AlfabetoAlienigena {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int n1 = sc.nextInt();
        int n2 = sc.nextInt();

        String alfa;
        String str;
        
        alfa = sc.next();
        str = sc.next();

        int cont = 0;
        for(int i=0; i<n1; i++) {
            for(int j=0; j<n2; j++) {
                if(alfa.charAt(i) != str.charAt(j)) {
                    cont++;
                }
            }
        }
        if(cont >= n2) {
            System.out.println("N");
        } else {
            System.out.println("S");
        }
        sc.close();
    }
}
