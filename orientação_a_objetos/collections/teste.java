package collections;

import sig;

public class teste {
    
    public static void main(String[] args) {
        int[] array = {5, 2, 9, 1, 5, 6};
        
        // Usando o método bubblesort da classe Sig
        sig.bubblesort(array);
        System.out.println("Array ordenado com Bubble Sort:");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();

    }
}
