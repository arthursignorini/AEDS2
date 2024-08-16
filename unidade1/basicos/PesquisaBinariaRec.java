package unidade1.basicos;

import java.util.Scanner;

public class PesquisaBinariaRec {
    public static void main(String[]args) {
        
        Scanner sc = new Scanner(System.in);

        int [] array = new int[] {5,3,2,10,7,4};

        quick(array, 0, (array.length)-1);

        System.out.println("Digite um numero: ");
        int num = sc.nextInt();
        int x = binaria(array,num, 0, array.length-1);
        if(x >= 0){
            System.out.println("O numero foi encontrado");
        } else {
            System.out.println("O numero nao foi encontrado");
        }

        sc.close();
    }

    public static void quick(int array[], int esq, int dir) {
        int i = esq;
        int j = dir;
        int pivo = array[(esq + dir) / 2];

        while(i <= j){
            while (array[i] < pivo) {
                i++;
            }
            while(array[j] > pivo) {
                j--;
            }
            if(i <= j){
                swap(array, i, j);
                i++;
                j--;
            }
        }
        if (esq < j) {
            quick(array, esq, j);
        }
        if (i < dir) {
            quick(array, i, dir);
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int binaria(int array[], int num, int esq, int dir) {
        if(esq > dir){
            return -1;
        } else {
            int meio = (dir + esq) / 2;
            if(num == array[meio]){
                return meio;
            } else {
                if(num > array[meio]){
                    return binaria(array, num, meio+1, dir);
                } else {
                    return binaria(array, num, esq, meio-1);
                }
            }
        }
    }
}
