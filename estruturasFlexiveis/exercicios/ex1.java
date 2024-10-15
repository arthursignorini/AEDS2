package estruturasFlexiveis.exercicios;

// treinando pilha flexivel

public class ex1 {
    public static void main(String[] args) throws Exception {
        Pilha pilha = new Pilha();
        pilha.inserir(10);
        pilha.inserir(20);
        pilha.inserir(30);

        pilha.mostrar();
        pilha.remover();
        System.out.println();
        pilha.mostrar();
        System.out.println();
        pilha.remover();
        pilha.mostrar();

    }
    
}

class Celula {
    int elemento;
    Celula prox;

    Celula(int x) {
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

    int remover () throws Exception {
        if (topo == null) {
            throw new Exception("PILHA VAZIA");
        }

        int elemento = topo.elemento;
        Celula temp = topo;
        topo = topo.prox;
        temp.prox = null;
        temp = null;
        return elemento;
    }

    void mostrar() {
        mostrar(topo);
    }

    void mostrar(Celula i) {
        while (i != null) {
            System.out.println(i.elemento);
            i = i.prox;  
        }
    }
}