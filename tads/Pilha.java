import java.util.Scanner;

public class Pilha {
    int n;
    int[] vet;

    Pilha(int tam) {
        vet = new int[tam];
        n = 0; 
    }

    void inserir (int x) throws Exception { // deve inserir no fim para empilhar
        if(n == vet.length) {
            throw new Exception ("Pilha cheia");
        }
        vet[n] = x;
        n++;
    }

    int remover() throws Exception { // deve remover do fim para desempilhar
        if (n == 0) { 
            throw new Exception("ERRO: Pilha vazia");
        }
        n--; 
        return vet[n]; 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pilha pilha = new Pilha(6); // cria uma pilha com 6 posições
        
        try {
            
            for (int i = 0; i < 6; i++) {
                int num = sc.nextInt();
                pilha.inserir(num);

            }
            
            
            int resp = pilha.remover();
            System.out.println("Elemento removido: " + resp);
            
        } catch (Exception e) {
            System.out.println(e.getMessage()); 
        } finally {
            sc.close(); 
        }
    }
}
