package beecrowd;

import java.util.Scanner;

public class SequenciaSecreta {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);

        int qntd = sc.nextInt();
        int[] vet = new int[qntd];
        for(int i=0; i<qntd; i++){
            vet[i] = sc.nextInt();
        }
        int cont = 0;
        for(int i=0; i<qntd-1; i++) {
            if(vet[i] != vet[i+1]) {
                cont++;
            }
        }
        System.out.println(cont+1);
        sc.close();
    }
}
