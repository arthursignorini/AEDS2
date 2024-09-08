public class ex2 {
    int n = 0;
    int[] array = new int[100];

    void inserir(int valor) {
        if (n < array.length) {
            n++;
            array[array.length - n] = valor;
        }
    }
    int remover () {
        if(n > 0) {
            n--;
            int resp = array[n];
            return resp;
        }
    }
}