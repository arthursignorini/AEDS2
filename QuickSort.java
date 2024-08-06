public class QuickSort {

    private static void quicksort(int[] array, int esq, int dir) {
        int i = esq;
        int j = dir;
        int pivo = array[(esq + dir) / 2];
        
        while (i <= j) {
            while (array[i] < pivo) {
                i++;
            }
            while (array[j] > pivo) {
                j--;
            }
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        
        if (esq < j) {
            quicksort(array, esq, j);
        }
        if (i < dir) {
            quicksort(array, i, dir);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] data = { 3, 6, 8, 10, 1, 2, 1 };
        quicksort(data, 0, data.length - 1);
        
        System.out.println("Array ordenado:");
        for (int num : data) {
            System.out.print(num + " ");
        }
    }
}
