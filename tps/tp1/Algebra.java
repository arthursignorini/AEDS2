package tps.tp1;

import java.util.Scanner;

public class Algebra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            int tam = Integer.parseInt(line.charAt(0));
            int[] vet = new int[tam];
            for(int i=1; i<tam;i++) {
                vet[i] = sc.nextInt();
            }
            
        }

    }
}