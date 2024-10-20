package estruturasFlexiveis.exercicios;

public class FilaFlexivelSlides {
    public static void main(String[] args) {
        Fila fila = new Fila();
        fila.inserir(1);
        fila.inserir(2);
        fila.inserir(3);
        fila.mostrar();
        System.out.println();
        fila.remover();
        fila.mostrar();
    }
}

class Celula {
    int elemento;
    Celula prox;

    Celula () {
        elemento = 0;
        prox = null;
    }

    Celula (int x) {
        elemento = x;
        prox = null;
    }
}

class Fila {
    Celula primeiro;
    Celula ultimo;

    Fila() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    void inserir (int x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    int remover() {
        int elemento = primeiro.prox.elemento;
        Celula temp = primeiro.prox;
        primeiro = temp.prox;
        temp.prox = null;
        temp = null;
        return elemento;  
    }

    void mostrar() {
        Celula i;
        for(i=primeiro.prox; i!=null;i=i.prox){
            System.out.print(i.elemento + " ");
        }
    }
}