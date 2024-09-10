package ordenacao;

public class ex2 {
    public static void main(String[] args) {
        int[] vet = {5, 3, 2, 8, 9, 4};

        for(int i=0; i<vet.length; i++) {
           int temp = vet[i];
           int j = i-1;
           while(j >= 0 && vet[j] > temp) {
                vet[j+1] = vet[j];
                j--;
           }
           vet[j+1] = temp;
        }

        for (int i = 0; i < vet.length; i++) {
            System.out.println(vet[i]);
        }
    }

    public static void swap(int i, int j, int[] vet) {
        int temp = vet[i];
        vet[i] = vet[j];
        vet[j] = temp;
    }
}
