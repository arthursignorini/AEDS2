package estruturasFlexiveis.exercicios;

public class ex4 {
    public static void main(String[] args) {
        try {
            Lista lista = new Lista();

            // Inserções no início da lista
            lista.inserirInicio(50);
            lista.inserirInicio(20);
            lista.inserirInicio(30);
            lista.inserirInicio(32);
            lista.inserirInicio(9);
            lista.inserirInicio(8);
            lista.mostrar();

            lista.ordenar();
            lista.mostrar();
            lista.quantidadeTermos();
            lista.removerFim();
            lista.mostrar();
            lista.quantidadeTermos();
            lista.inserirNoCabeca(2);
            lista.mostrar();
            lista.inverter();
            lista.mostrar();
            // lista.shellSort();
            lista.quickSort();
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

    void inverter () {
        Celula anterior = null;
        Celula atual = primeiro.prox;
        Celula proximo = null;
        ultimo = primeiro.prox;

        while(atual!=null) {
            proximo = atual.prox;
            atual.prox = anterior;
            anterior = atual;
            atual = proximo;
        }
        primeiro.prox = anterior;
    }

    void shellSort() {
        // Contar o número de elementos na lista
        int tamanho = tamanho();
        
        // Definir o gap inicial
        for (int gap = tamanho / 2; gap > 0; gap /= 2) {
            // Percorrer a lista a partir do gap
            for (int i = gap; i < tamanho; i++) {
                // Encontre o i-ésimo elemento da lista
                Celula atual = getCelula(i);
                int valorAtual = atual.elemento;
    
                // Mover o valor maior de trás para frente
                int j = i;
                while (j >= gap && getCelula(j - gap).elemento > valorAtual) {
                    getCelula(j).elemento = getCelula(j - gap).elemento;
                    j -= gap;
                }
                // Colocar o valor atual na posição correta
                getCelula(j).elemento = valorAtual;
            }
        }
    }
    
    Celula getCelula(int pos) {
        Celula atual = primeiro.prox;
        for (int i = 0; i < pos && atual != null; i++) {
            atual = atual.prox;
        }
        return atual;
    }

    void quickSort(Celula inicio, Celula fim) {
        if (inicio != null && fim != null && inicio != fim && inicio != fim.prox) { // confere se nao tem apenas 1 elemento
            Celula pivo = partition(inicio, fim); // passa o começo e o final da lista
            quickSort(inicio, pivo.prox != inicio ? pivo.prox : inicio);
            quickSort(pivo.prox, fim);
        }
    }
    
    Celula partition(Celula inicio, Celula fim) {
        int pivoValor = fim.elemento; // pivo vai começar no final
        Celula i = inicio;
    
        for (Celula j = inicio; j != fim; j = j.prox) { // percorre ate chegar na posicao anterior ao pivo
            if (j.elemento <= pivoValor) {
                int temp = i.elemento;
                i.elemento = j.elemento;
                j.elemento = temp;
                i = i.prox;
            }
        }
    
        // Troca o valor do pivô para a posição correta
        int temp = i.elemento;
        i.elemento = fim.elemento;
        fim.elemento = temp;
    
        return i; 
    }
    
    void quickSort() {
        if (primeiro != ultimo) { // se nao estiver vazia, começa o metodo
            quickSort(primeiro.prox, ultimo);
        }
    }
}