package estruturasFlexiveis.exercicios;

public class ListaDupla {
    public static void main(String[] args) throws Exception {
        LinkedList listaDupla = new LinkedList();
        listaDupla.inserirInicio(2);
        listaDupla.inserirInicio(3);
        listaDupla.inserirInicio(4);
        listaDupla.mostrar();
        listaDupla.inserirFim(10);
        listaDupla.inserirFim(20);
        listaDupla.mostrar();
        listaDupla.removerInicio();
        listaDupla.mostrar();
        listaDupla.removerFim();
        listaDupla.mostrar();
        listaDupla.inserir(17, 2);
        listaDupla.mostrar();
        listaDupla.inserir(13, 2);
        listaDupla.mostrar();
        listaDupla.remover(1);
        listaDupla.mostrar();
    }
}

class CelulaDupla {
    int elemento;
    CelulaDupla ant, prox;

    CelulaDupla() {
        this(0);
    }

    CelulaDupla(int x) {
        elemento = x;
        ant = prox = null;
    }
}

class LinkedList {
    CelulaDupla primeiro, ultimo;

    LinkedList() {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }

    void inserirInicio(int x) {
        CelulaDupla temp = new CelulaDupla(x);
        temp.prox = primeiro.prox;
        temp.ant = primeiro;
        primeiro.prox = temp;
        if (primeiro == ultimo) {
            ultimo = temp;
        } else {
            temp.prox.ant = temp;
        }
        temp = null;
    }

    void inserirFim(int x) {
        ultimo.prox = new CelulaDupla(x);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    int removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("LISTA VAZIA");
        }
        CelulaDupla temp = primeiro.prox;
        int elemento = temp.elemento;
        primeiro.prox = temp.prox;
        temp.prox.ant = primeiro;
        temp.ant = temp.prox = null;
        temp = null;
        return elemento;
    }

    int removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("LISTA VAZIA");
        }
        int elemento = ultimo.elemento;
        CelulaDupla temp = ultimo.ant;
        temp.prox = ultimo.prox;
        ultimo.ant = null;
        ultimo = temp;
        temp = null;
        return elemento;
    }

    void mostrar() {
        System.out.println();
        CelulaDupla i;
        for (i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
    }

    void inserir(int x, int pos) throws Exception {
        int tam = tamanho();
        if (pos > tam || pos < 0) {
            throw new Exception("POSICAO INVALIDA");
        }
        if (pos == 0) {
            inserirInicio(x);
        } else if (pos == tam) {
            inserirFim(x);
        } else {
            CelulaDupla i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;
            CelulaDupla temp = new CelulaDupla(x);
            temp.ant = i;
            temp.prox = i.prox;
            i.prox.ant = temp;
            i.prox = temp;
            temp = null;
        }
    }

    int remover(int pos) throws Exception {
        int tam = tamanho();
        if (pos > tam || pos < 0) {
            throw new Exception("POSICAO INVALIDA");
        }
        int elemento;
        if (pos == 0) {
            elemento = removerInicio();
        } else if (pos == tam - 1) {
            elemento = removerFim();
        } else {
            CelulaDupla i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox);
            CelulaDupla temp = i.prox;
            elemento = temp.elemento;
            i.prox = temp.prox;
            temp.prox.ant = i;
            temp.ant = temp.prox = null;
            temp = null;
        }
        return elemento;
    }

    int tamanho() {
        int cont = 0;
        for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
            cont++;
        }
        return cont;
    }
}
