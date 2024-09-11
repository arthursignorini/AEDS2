package preprova;

public class ex4 { // inserção otimizado
    public static void main(String[] args) {
        int[] vet = { 3, 1, 9, 5, 4, 7 };
        int tam = vet.length;

        for(int i=1; i<tam; i++){
            int temp = vet[i];
            int newPos = binaria(0, i-1, vet, vet[i]);

            for(int j=i; j > newPos; j--) {
                vet[j] = vet[j-1];
            }

            vet[newPos] = temp;
        }

        for (int num : vet) {
            System.out.print(num + " ");
        }
    }

    public static int binaria(int esq, int dir, int[] vet, int num) {
        while(esq <= dir) {
            int meio = (esq + dir) /2;

            if(vet[meio] == num) {
                return meio;
            }
            if(vet[meio] > num ){
                dir = meio -1;
            } else {
                esq = meio + 1;
            }
        }
        return esq;
    }
}
