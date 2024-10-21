package estruturasFlexiveis.exercicios;

public class ListaDupla {
    public static void main(String[] args) {
        
    }
}

class CelulaDupla {
    int elemento;
    CelulaDupla ant, prox;

    CelulaDupla () {
        this(0);
    }

    CelulaDupla (int x) {
        int elemento = x;
        ant = prox = null;
    }
}

class LinkedList {
    CelulaDupla primeiro, ultimo;

    LinkedList () {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }

    void inserirInicio (int x) {
        CelulaDupla temp = new CelulaDupla(x);
        temp.prox = primeiro.prox;
        temp.ant = primeiro;
        primeiro.prox = temp;
        if(primeiro == ultimo) {
            ultimo = temp; 
        } else {
            temp.prox.ant = temp;
        }
        temp = null;
    }

    void inserirFim (int x) {
        ultimo.prox = new CelulaDupla(x);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    void removerInicio () throws Exception {
        if(primeiro == ultimo) {
            throw new Exception("LISTA VAZIA");
        }
        CelulaDupla temp = primeiro;
        primeiro = primeiro.prox;
        int elemento = primeiro.elemento;
        temp.prox = null;
        // PRECISO DESENHAR
    }
}
