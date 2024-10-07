package ordenacao;

public class quick {
    public static void main(String[] args) {
        int[] vet = {5,3,1,8,9,6,5,7};
        quicksort(0, vet.length-1, vet);
        for(int x : vet) {
            System.out.println(x);
        }
    }

    public static void quicksort(int esq, int dir, int[] vet) {
        int i = esq, j = dir, pivo = vet[(dir + esq) / 2];
        while(i<=j) {
            while(vet[i]<pivo) {
                i++;
            }
            while(vet[j] > pivo) {
                j--;
            }
            if(i<=j) {
                swap(i,j, vet);
                i++;
                j--;
            }
            if(esq < j) {
                quicksort(esq, j, vet);
            }
            if(i < dir) {
                quicksort(i, dir, vet);
            }
        }
    }

    public static void swap (int i, int j, int[] vet) {
        int temp = vet[i];
        vet[i] = vet[j];
        vet[j] = temp;
    }
}
