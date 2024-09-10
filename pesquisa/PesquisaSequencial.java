package pesquisa;

public class PesquisaSequencial {
    public static void main(String[] args) {
        int[] vet = {5,3,2,1,9};

        int num = 3;
        for(int i=0; i<vet.length; i++) {
            if(num == vet[i]) {
                System.out.println("encontrado");
                break;
            }
        }
    }
}
