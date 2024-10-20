package estruturasFlexiveis.exercicios;

public class ex4 {
    public static void main(String[]args) {
        try {
            Lista lista = new Lista();

            // Inserções no início da lista
            lista.inserirInicio(10);
            lista.inserirInicio(20);
            lista.inserirInicio(30);

            // Exibe a lista antes de qualquer remoção
            System.out.print("Lista antes da remoção: ");
            lista.mostrar();

            // Remove o primeiro elemento
            lista.removerInicio();
            System.out.print("\nLista após a remoção no início: ");
            lista.mostrar();

            // Insere no fim da lista
            lista.inserirFim(40);
            System.out.print("\nLista após inserir no fim: ");
            lista.mostrar();

            // Remove o último elemento
            lista.removerFim();
            System.out.print("\nLista após a remoção no fim: ");
            lista.mostrar();

            // Insere alguns elementos adicionais
            lista.inserirFim(50);
            lista.inserirFim(60);
            lista.inserir(25, 1); // Inserir na posição 1 (entre 20 e 10)
            System.out.print("\nLista após inserção intermediária (25 na posição 1): ");
            lista.mostrar();

            // Remove da posição intermediária
            lista.remover(1);
            System.out.print("\nLista após remover da posição 1: ");
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

    Lista() {
        primeiro = ultimo = new Celula();
    }

    void inserirInicio(int x) {
        Celula temp = new Celula(x);
        temp.prox = primeiro.prox;
        primeiro.prox = temp;
        if(primeiro == ultimo) {
            ultimo = temp;
        }
        temp = null;
    }

    void inserirFim(int x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    } 

    void inserir(int x, int pos) throws Exception {
        int tam = tamanho();
        if(pos < 0 || pos > tam) {
            throw new Exception ("Posicao invalida");
        }
        if(pos == 0) {
            inserirInicio(x);
        } else if(pos == tam) {
            inserirFim(x);
        } else {
            Celula i=primeiro;
            for(int j=0; j<pos; j++, i=i.prox);
            Celula temp = new Celula(x);
            temp.prox = i.prox;
            i.prox = temp;
            i = temp = null;
        }
        
    }
    
    void mostrar() {
        for(Celula i=primeiro.prox; i!=null; i=i.prox) { 
            System.out.print(i.elemento + " ");
        }
    }

    int tamanho() {
        Celula i;
        int cont = 0;
        for(i=primeiro.prox; i!=null; i=i.prox,cont++); 
        return cont;
    }

    // SE EU QUISER CONTAR OU MOSTRAR DEVO IR ATÉ O FINAL
    // ENTAO DEVO USAR O I ATÉ ELE SER NULL

    int removerInicio() throws Exception {
        if(primeiro == ultimo) {
            throw new Exception ("LISTA VAZIA");
        }
        int elemento = primeiro.prox.elemento;
        Celula temp = primeiro.prox;
        primeiro.prox = temp.prox;
        if(temp == ultimo) {
            ultimo = primeiro;
        }
        temp.prox = null;
        temp = null;
        return elemento;
    }

    int removerFim() throws Exception {
        if(primeiro == ultimo) {
            throw new Exception ("LISTA VAIZA");
        }
        Celula i;
        for(i=primeiro.prox; i.prox !=ultimo; i=i.prox);
        Celula temp = i.prox;
        int elemento = temp.elemento;
        ultimo = i;
        i.prox = null;
        return elemento;
    }

    int remover(int pos) throws Exception {
        int tam = tamanho();
        if(primeiro == ultimo) {
            throw new Exception ("LISTA VAIZA");
        }
        if(pos < 0 || pos >= tam) {
            throw new Exception ("POSICAO INVALIDA");
        }
        if(pos == 0) {
            removerInicio();
        }
        if(pos == tam-1){
            removerFim();
        }
        Celula i=primeiro;
        for(int j=0; j<pos; j++, i=i.prox);
        Celula temp = i.prox;
        int elemento = temp.elemento;
        i.prox = temp.prox;
        temp.prox = null;
        return elemento;
    }
}