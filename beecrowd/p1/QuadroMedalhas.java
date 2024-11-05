package beecrowd;

import java.util.*;

public class QuadroMedalhas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> ouro = new ArrayList<>();
        ArrayList<Integer> prata = new ArrayList<>();
        ArrayList<Integer> bronze = new ArrayList<>();

        // contagem dos ouros
        for (int i = 0; i < 10; i++) {
            int num = sc.nextInt();
            ouro.add(num);
        }

        // contagem das pratas
        for (int i = 0; i < 10; i++) {
            int num = sc.nextInt();
            prata.add(num);
        }

        // contagem dos bronzes
        for (int i = 0; i < 10; i++) {
            int num = sc.nextInt();
            bronze.add(num);
        }

        // ordenar os ouros
        for (int i = 0; i < 10 - 1; i++) {
            for (int j = 0; j < 10 - 1 - i; j++) {
                if (ouro.get(j) < ouro.get(j + 1)) {
                    swap(j, j + 1, ouro);
                    swap(j, j + 1, prata);
                    swap(j, j + 1, bronze);
                }
            }
        }

        // ordenar as pratas
        for (int i = 0; i < 10 - 1; i++) {
            if (ouro.get(i) == ouro.get(i + 1) && prata.get(i) < prata.get(i + 1)) {
                swap(i, i + 1, ouro);
                swap(i, i + 1, prata);
                swap(i, i + 1, bronze);
            }
        }

        // ordenar os bronzes
        for (int i = 0; i < 10 - 1; i++) {
            if (prata.get(i) == prata.get(i + 1) && bronze.get(i) < bronze.get(i + 1)) {
                swap(i, i + 1, ouro);
                swap(i, i + 1, prata);
                swap(i, i + 1, bronze);
            }
        }
        imprimir(ouro);
        System.out.println();
        imprimir(prata);
        System.out.println();
        imprimir(bronze);
        
        sc.close();
    }

    public static void swap(int i, int j, ArrayList<Integer> array) {
        int temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    public static void imprimir (ArrayList<Integer> array) {
        for(int i=0; i<10; i++) {
            System.out.print(array.get(i) + " ");
        }
    }
}