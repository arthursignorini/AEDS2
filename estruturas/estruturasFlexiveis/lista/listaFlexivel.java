package estruturasFlexiveis.lista;

public class listaFlexivel {
    public static void main(String[] args) {

        Lista lista = new Lista();

        // Inserir elementos no início e no fim
        System.out.println("Inserindo elementos...");
        lista.inserirInicio(10);
        lista.inserirFim(20);
        lista.inserirInicio(5);
        lista.inserirFim(30);
        lista.mostrar();

        // Mostrar o tamanho atual da lista
        System.out.println("Tamanho da lista: " + lista.tamanho());  

        // Remover um elemento do início
        System.out.println("Removendo do início: " + lista.removerInicio());  
        System.out.println("Tamanho da lista após remover do início: " + lista.tamanho());
        lista.mostrar();  

        // Remover um elemento do fim
        System.out.println("Removendo do fim: " + lista.removerFim());  
        System.out.println("Tamanho da lista após remover do fim: " + lista.tamanho());
        lista.mostrar();  

        // Inserir em uma posição específica
        System.out.println("Inserindo 15 na posição 1...");
        lista.inserir(15, 1);
        System.out.println("Tamanho da lista após inserir 15: " + lista.tamanho());
        lista.mostrar();    

        // Remover de uma posição específica
        System.out.println("Removendo o elemento da posição 1: " + lista.remover(1)); 
        System.out.println("Tamanho da lista após remover da posição 1: " + lista.tamanho());  
        lista.mostrar();  
    
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

class Lista {
    Celula primeiro;
    Celula ultimo;

    Lista () {
        primeiro = ultimo = new Celula();
    }

    void inserirFim (int x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    void inserirInicio (int x) {
        Celula temp = new Celula(x);
        temp.prox = primeiro.prox;
        primeiro.prox = temp;
        if (primeiro.prox == ultimo) {
            ultimo = temp;
        }
        temp = null;
    }

    int removerFim () {
        if (primeiro == ultimo) {
            System.exit(0);
        }
        Celula i;
        for(i=primeiro; i.prox != ultimo; i=i.prox);
        int elemento = ultimo.elemento;
        ultimo = i;
        i = ultimo.prox = null;
        return elemento;
    }

    int removerInicio () { // Remove apenas logicamente e não fisicamente
        if (primeiro == ultimo) {
            System.exit(0);
        }
        Celula temp = primeiro;
        primeiro = primeiro.prox;
        int elemento = primeiro.elemento;
        temp.prox = null;
        temp = null;
        return elemento;
    }

    void inserir (int x, int pos) {
        int tam = tamanho();
        if (pos < 0 || pos > tam) {
            System.exit(0);
        } else if (pos == 0) {
            inserirInicio(x);
        } else if (pos == tam) {
            inserirFim(x);
        } else {
            Celula i = primeiro;
            for(int j=0; j<pos; j++, i=i.prox);
            Celula temp = new Celula(x);
            temp.prox = i.prox;
            i.prox = temp;
            temp = i = null;
        }
    }

    int remover (int pos) {
        int elemento=-1, tam = tamanho();
        if(primeiro == ultimo || pos < 0 || pos >= tam) {
            System.exit(0);
        } else if (pos == 0) {
            elemento = removerInicio();
        } else if (pos == tam-1) {
            elemento = removerFim();
        } else {
            Celula i = primeiro;
            for(int j=0; j<pos; j++, i=i.prox);
            Celula temp = i.prox;
            elemento = temp.elemento;
            i.prox = temp.prox;
            temp.prox = null;
            i = temp = null; 
        }
        return elemento;
    }

    void mostrar () {
        for(Celula i=primeiro.prox; i!=null; i=i.prox) {
            System.out.print(i.elemento + " ");
        }
        System.out.println();
    }

    int tamanho () {
        int tam = 0;
        for(Celula i=primeiro; i!=ultimo; i=i.prox, tam++);
        return tam;
    } 
}
