import java.util.Scanner;

class List {
    int nP;
    int nN;
    int[] vet;

    List(int tam) {
        vet = new int[tam];
        nP = 0;
        nN = 0;
    }

    boolean inserirInicio(int x) {
        if (nP + nN >= vet.length) {
            System.out.println("ERRO: Lista cheia");
            return false; // Indica erro
        }

        for (int i = nP; i > 0; i--) {
            vet[i] = vet[i - 1];
        }
        vet[0] = x;
        nP++;
        if (nP > 0) {
            for (int i = 0; i < nP - 1; i++) {
                for (int j = 0; j < nP - i - 1; j++) {
                    if (vet[j] > vet[j + 1]) {
                        int temp = vet[j + 1];
                        vet[j + 1] = vet[j];
                        vet[j] = temp;
                    }
                }
            }
        }
        return true; // Inserção com sucesso
    }

    boolean inserirFim(int x) {
        if (nP + nN >= vet.length) {
            System.out.println("ERRO: Lista cheia");
            return false; // Indica erro
        }

        vet[nP + nN] = x;
        nN++;
        return true; // Inserção com sucesso
    }

    boolean inserir(int x) {
        if (nP + nN >= vet.length) {
            System.out.println("ERRO: Lista cheia");
            return false; // Indica erro
        }
        if (x >= 0) {
            inserirInicio(x);
            return true;
        } else {
            inserirFim(x);
            return true;
        }

    }

    void mostrar() {
        for (int i = 0; i < nN + nP; i++) {
            System.out.print(vet[i] + " ");
        }
        System.out.println();
    }

}

public class ex1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o tamanho do vetor: ");
        int tam = sc.nextInt();
        List lista = new List(tam);

        for (int i = 0; i < tam; i++) {
            System.out.print("Digite um numero: ");
            int num = sc.nextInt();
            lista.inserir(num);
            lista.mostrar();
        }
        sc.close();
    }
}
