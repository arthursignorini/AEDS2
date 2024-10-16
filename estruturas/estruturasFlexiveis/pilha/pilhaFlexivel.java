package estruturasFlexiveis.pilha;
import java.util.*;

public class pilhaFlexivel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pilha pilha = new Pilha();
        int x = 0;
        while(x != 4) {
            System.out.print("1) INSERIR  2) REMOVER  3) MOSTRAR: ");
            x = sc.nextInt();
            switch (x) {
                case 1:
                    System.out.print("Digite o numero que voce quer adicionar: ");
                    int num = sc.nextInt();
                    pilha.inserirPilha(num);
                    break;

                case 2:
                    System.out.println("Elemento removido...");
                    pilha.removerPilha();
                    break;

                case 3:
                    System.out.println("Elementos da pilha:");
                    pilha.mostrar();
                    break;

                case 4:
                    System.out.println("Saindo...");
                    break;
    
                default:
                    System.out.println("Operação não é válida");
                    break;
            }
        }
        sc.close();  
    }
}

class Celula {
    public int elemento;
    public Celula prox;

    public Celula() {
        this(0);
    }
    public Celula (int x) {
        this.elemento = x;
        this.prox = null;
    }
}

class Pilha {
    public Celula topo;

    public Pilha() {
        topo = null;
    }
    public void inserirPilha(int x) {
        Celula tmp = new Celula(x);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;
    }
    public int removerPilha() {
        if (topo == null) {
            throw new NoSuchElementException("Erro ao remover: a pilha está vazia!");
        }

        int elemento = topo.elemento;
        // Celula tmp = topo; isso seria para retirar a celula da pilha
        topo = topo.prox;
        // tmp = null; 

        return elemento;
    }
    public void mostrar() {
        Celula i = topo;
        while (i != null) {
            System.out.println(i.elemento);
            i = i.prox;
        }
    }
}