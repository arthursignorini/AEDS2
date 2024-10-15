package estruturasFlexiveis.exercicios;
// FAZENDO UMA LISTA FLEXIVEL
public class ex2 {
    public static void main(String[] args) {
        
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
    Celula segundo;
}
