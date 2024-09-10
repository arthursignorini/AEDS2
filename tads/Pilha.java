import java.util.Scanner;

public class Pilha {
    int n;
    int[] vet;

    Pilha(int tam) {
        vet = new int[tam];
        n = 0; // Inicializa n como 0 para rastrear o número de elementos na pilha
    }

    void inserir(int x) throws Exception { // deve inserir no fim para empilhar
        if (n >= vet.length) {
            throw new Exception("ERRO: Pilha cheia");
        }
        vet[n] = x;
        n++;
    }

    int remover() throws Exception { // deve remover do fim para desempilhar
        if (n == 0) { // Verifica se a pilha está vazia
            throw new Exception("ERRO: Pilha vazia");
        }
        n--; // Decrementa n para "remover" o último elemento
        return vet[n]; // Retorna o último elemento
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pilha pilha = new Pilha(6); // Cria uma pilha com 6 posições
        
        try {
            // Inserir 6 números na pilha
            for (int i = 0; i < 6; i++) {
                int num = sc.nextInt();
                pilha.inserir(num);
            }
            
            // Remover o último número inserido e imprimir
            int resp = pilha.remover();
            System.out.println("Elemento removido: " + resp);
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Exibe a mensagem de erro, se houver
        } finally {
            sc.close(); // Fecha o Scanner
        }
    }
}
