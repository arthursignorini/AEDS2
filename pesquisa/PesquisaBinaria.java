package pesquisa;

public class PesquisaBinaria {
    public static void main(String[] args) {
        int[] vet = {1,2,3,4,5,6,7,8};
        int num = 7;
        boolean resp = binaria(vet, 0, vet.length-1, num);
        if(resp) {
            System.out.println("achou");
        } else {
            System.out.println("nao achou");
        }
    }

    public static boolean binaria(int[] vet, int esq, int dir, int num) {
        while(esq<=dir) {
            int meio = (esq + dir) / 2;
            if(vet[meio] == num) {
                return true;
            }
            if(vet[meio] < num) {
                esq = meio + 1;
            } else {
                dir = meio -1; 
            }
        }
        return false;
    }
}
