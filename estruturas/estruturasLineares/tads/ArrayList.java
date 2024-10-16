import java.util.Scanner;

public class ArrayList {
    int n;
    int[] vet;

    ArrayList(int tam) {
        vet = new int[tam];
        n = 0;
    }

    boolean inserirInicio(int x) { 
        if (n >= vet.length) {
            System.out.println("ERRO: Lista cheia");
            return false; // Indica erro
        }

        for (int i = n; i > 0; i--) {
            vet[i] = vet[i - 1];
        }
        vet[0] = x;
        n++;
        return true; // Inserção com sucesso
    }

    boolean inserirFim(int x) {
        if (n >= vet.length) {
            System.out.println("ERRO: Lista cheia");
            return false; // Indica erro
        }

        vet[n] = x;
        n++;
        return true; // Inserção com sucesso
    }

    boolean inserir(int x, int pos) {
        if (n >= vet.length) {
            System.out.println("ERRO: Lista cheia");
            return false; // Indica erro
        }
        if (pos < 0 || pos > n) {
            System.out.println("ERRO: Posição inválida");
            return false; // Indica erro
        }

        for (int i = n; i > pos; i--) {
            vet[i] = vet[i - 1];
        }
        vet[pos] = x;
        n++;
        return true; // Inserção com sucesso
    }

    void mostrar() {
        for (int i = 0; i < n; i++) {
            System.out.print(vet[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o tamanho da lista: ");
        int tamanho = sc.nextInt();
        ArrayList lista = new ArrayList(tamanho);
        int op;
        int num, pos;

        do {
            System.out.println("OPÇÕES DE ESCOLHA");
            System.out.println("=================");
            System.out.println("1) INSERIR NO INÍCIO");
            System.out.println("2) INSERIR NO FIM");
            System.out.println("3) INSERIR EM UM LUGAR ESPECÍFICO");
            System.out.println("4) MOSTRAR LISTA");
            System.out.println("0) SAIR");
            System.out.print("ESCOLHA: ");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.print("NUMERO QUE VOCE DESEJA INSERIR: ");
                    num = sc.nextInt();
                    lista.inserirInicio(num);
                    break;

                case 2:
                    System.out.print("NUMERO QUE VOCE DESEJA INSERIR: ");
                    num = sc.nextInt();
                    lista.inserirFim(num);
                    break;

                case 3:
                    System.out.print("NUMERO QUE VOCE DESEJA INSERIR: ");
                    num = sc.nextInt();
                    System.out.print("POSIÇÃO ONDE VOCE DESEJA INSERIR: ");
                    pos = sc.nextInt();
                    lista.inserir(num, pos);
                    break;

                case 4:
                    lista.mostrar();
                    break;

                case 0:
                    System.out.println("SAINDO...");
                    break;

                default:
                    System.out.println("OPÇÃO INVÁLIDA");
                    break;
            }
        } while (op != 0);

        sc.close();
    }
}
