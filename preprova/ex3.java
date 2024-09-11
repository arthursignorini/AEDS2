package preprova;

public class ex3 {
    public static void main(String[] args) {
        int[] vet = { 3, 1, 9, 5, 4, 7 };
        int tam = vet.length;

        boolean troca = true;
        for (int i = 0; i < tam - 1 && troca; i++) {
            troca = false;
            for (int j = 0; j < tam - 1 - i; j++) {
                if (vet[j] > vet[j + 1]) {
                    swap(i, j, vet);
                    troca = true;
                }
            }
        }

        for (int x : vet) {
            System.out.println(x + " ");
        }
    }

    public static void swap(int i, int j, int[] vet) {
        int temp = vet[i];
        vet[i] = vet[j];
        vet[j] = temp;
    }
}
