package estruturasFlexiveis.exercicios;

public class ex4 {
    public static void main(String[] args) {
        try {
            Lista lista = new Lista();

            // Inserções no início da lista
            lista.inserirInicio(50);
            lista.inserirInicio(20);
            lista.inserirInicio(30);
            lista.mostrar();

            lista.ordenar();
            lista.mostrar();
            lista.quantidadeTermos();
            lista.removerFim();
            lista.mostrar();
            lista.quantidadeTermos();
            lista.inserirNoCabeca(2);
            lista.mostrar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class Celula {
    int elemento;
    Celula prox;

    Celula() {
        elemento = 0;
        prox = null;
    }

    Celula(int x) {
        elemento = x;

        prox = null;
    }
}

class Lista {
    Celula primeiro;
    Celula ultimo;
    int cont;

    Lista() {
        primeiro = ultimo = new Celula();
    }

    void inserirInicio(int x) {
        Celula temp = new Celula(x);
        temp.prox = primeiro.prox;
        primeiro.prox = temp;
        if (primeiro == ultimo) {
            ultimo = temp;
        }
        temp = null;
        cont++;
    }

    void inserirFim(int x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
        cont++;
    }

    void inserir(int x, int pos) throws Exception {
        int tam = tamanho();
        if (pos < 0 || pos > tam) {
            throw new Exception("Posicao invalida");
        }
        if (pos == 0) {
            inserirInicio(x);
        } else if (pos == tam) {
            inserirFim(x);
        } else {
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox);
            Celula temp = new Celula(x);
            temp.prox = i.prox;
            i.prox = temp;
            i = temp = null;
            cont++;
        }

    }

    void mostrar() {
        System.out.println();
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
    }

    int tamanho() {
        Celula i;
        int cont = 0;
        for (i = primeiro.prox; i != null; i = i.prox, cont++)
            ;
        return cont;
    }

    // SE EU QUISER CONTAR OU MOSTRAR DEVO IR ATÉ O FINAL
    // ENTAO DEVO USAR O I ATÉ ELE SER NULL

    int removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("LISTA VAZIA");
        }
        int elemento = primeiro.prox.elemento;
        Celula temp = primeiro.prox;
        primeiro.prox = temp.prox;
        if (temp == ultimo) {
            ultimo = primeiro;
        }
        temp.prox = null;
        temp = null;
        cont--;
        return elemento;
    }

    int removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("LISTA VAIZA");
        }
        Celula i;
        for (i = primeiro.prox; i.prox != ultimo; i = i.prox)
            ;
        Celula temp = i.prox;
        int elemento = temp.elemento;
        ultimo = i;
        i.prox = null;
        cont--;
        return elemento;
    }

    int remover(int pos) throws Exception {
        int tam = tamanho();
        if (primeiro == ultimo) {
            throw new Exception("LISTA VAIZA");
        }
        if (pos < 0 || pos >= tam) {
            throw new Exception("POSICAO INVALIDA");
        }
        if (pos == 0) {
            removerInicio();
        }
        if (pos == tam - 1) {
            removerFim();
        }
        Celula i = primeiro;
        for (int j = 0; j < pos; j++, i = i.prox)
            ;
        Celula temp = i.prox;
        int elemento = temp.elemento;
        i.prox = temp.prox;
        temp.prox = null;
        cont--;
        return elemento;
    }

    void ordenar () throws Exception {
       if(primeiro == ultimo) {
            throw new Exception("VAZIA");
       }

       for(Celula i=primeiro.prox; i!= null; i=i.prox) {
            for(Celula j=i.prox; j!=null; j=j.prox){
                if(i.elemento > j.elemento) {
                    int temp = i.elemento;
                    i.elemento = j.elemento;
                    j.elemento = temp;
                }
            }
       }
    }

    void quantidadeTermos() {
        System.out.println("\nQuantidade de elementos na lista: " + cont);
    }

    void inserirNoCabeca(int x) {
        primeiro.elemento = x;
        Celula nova = new Celula();
        nova.prox = primeiro;
        primeiro = nova;
    }
}