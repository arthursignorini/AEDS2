package preprova;

public class ex2 { // inserção
    public static void main(String[] args) {
        int[] vet = {3,1,9,5,4,7};
        int tam = vet.length;

        for(int i=1; i<tam; i++){
            int temp = vet[i];
            int j = i-1;
            while(j >= 0 && vet[j] > temp) {
                vet[j+1] = vet[j];
                j--;
            }
            vet[j+1] = temp;
        }

        for(int x : vet) {
            System.out.println(x + " ");
        }
    }
}
