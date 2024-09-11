package preprova;

public class ex1 { // seleção
    public static void main(String[]agrs) {
        int[] vet = {3,1,9,5,4,7};
        int tam = vet.length;

        for(int i=0; i<tam-1; i++) {
            int menor = i;
            for(int j=i+1; j<tam; j++){
                if(vet[menor] > vet[j]) {
                    menor = j;
                }
            }
            if(menor != i) {
                swap(menor, i, vet);
            }
        }

        for(int x : vet) {
            System.out.println(x + " ");
        }
    }

    public static void swap (int i, int j, int[] vet) {
        int temp = vet[i];
        vet[i] = vet[j];
        vet[j] = temp;
    }
}
