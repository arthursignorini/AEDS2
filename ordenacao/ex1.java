package ordenacao;

public class ex1 {
    public static void main(String[] args) {
        int[] vet = {5, 3, 2, 8, 9, 4};

        for (int i = 0; i < vet.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < vet.length; j++) {
                if (vet[j] < vet[menor]) {
                    menor = j;
                }
            }
            if (menor != i) {
                swap(menor, i, vet);
            }
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