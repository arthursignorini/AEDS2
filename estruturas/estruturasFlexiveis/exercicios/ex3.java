package estruturasFlexiveis.exercicios;

public class ex3 {
    public static void main(String[]args) {
        Pilha pilha = new Pilha();
        pilha.inserir(10);
        pilha.mostrar();
        pilha.inserir(20);
        pilha.mostrar();
        pilha.inserir(30);
        pilha.mostrar();
        pilha.remover();
        pilha.remover();
        pilha.mostrar();
    }
}

class Celula {
    int elemento;
    Celula prox;
    
    Celula () {
        this.elemento = 0;
        this.prox = null;
    }
    
    Celula (int x) {
        this.elemento = x;
        this.prox = null;
    }
}

class Pilha {
    Celula topo;
    
    Pilha() {
        this.topo = null;
    }
    
    void inserir(int x) {
        Celula temp = new Celula(x);
        temp.prox = topo;
        topo = temp;
        temp = null;
    }
    
    int remover() {
        int elemento = topo.elemento;
        Celula temp = topo;
        topo = topo.prox;
        temp = null;
        return elemento;
    }
    
    void mostrar() {
        mostrar(topo);
        System.out.println();
    }
    
    void mostrar(Celula i) {
        while(i != null) {
            System.out.println(i.elemento);
            i = i.prox;
        }
    }
}








