import java.util.Scanner;

public class Fila {
    int n;
    int[] vet;

    Fila(int tam) {
        vet = new int[tam];
    }

    void inserir (int x) throws Exception {
        if(n == vet.length) {
            throw new Exception ("Fila já está cheia");
        }
        vet[n] = x;
        n++;
    }

    int remover () throws Exception {
        if(n == 0) {
            throw new Exception ("Fila está vazia");
        }
        int resp = vet[0];

        n--;
        for(int i=0; i<n; i++) {
            vet[i] = vet[i+1]; 
        }
        return resp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int tam = sc.nextInt();
        Fila fila = new Fila(tam);

        try {
            for(int i=0; i<tam; i++) {
                int num = sc.nextInt();
                fila.inserir(num);
            }

            System.out.println("O elemento " + fila.remover() + " foi removido");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }
    }
}
