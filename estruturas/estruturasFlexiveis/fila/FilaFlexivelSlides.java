package estruturasFlexiveis.exercicios;

public class FilaFlexivelSlides {
    public static void main(String[] args) {
        Fila fila = new Fila();
        fila.inserir(1);
        fila.inserir(2);
        fila.inserir(3);
        fila.inserir(4);
        fila.inserir(5);
        fila.inserir(10);
        fila.mostrar();
        fila.maiorElemento();
        fila.terceiroElemento();
        fila.somarElementos();
        fila.inverterOrdem();
        fila.mostrar();
        fila.contarParesMultiplos();
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

class Fila {
    Celula primeiro;
    Celula ultimo;

    Fila() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    void inserir(int x) {
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
        System.out.print("Elementos: ");
        for (i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
    }

    void maiorElemento() {
        Celula i;
        int maior = primeiro.prox.elemento;
        for (i = primeiro.prox; i != null; i = i.prox) {
            if (i.elemento > maior) {
                maior = i.elemento;
            }
        }
        System.out.println("\nO maior elemento é: " + maior);
    }

    void terceiroElemento() {
        Celula i = primeiro;
        // p 1 2 3 4 5
        for (int j = 0; j < 3; j++, i = i.prox)
            ;
        int elemento = i.elemento;
        System.out.println("\nO terceiro elemento é o: " + elemento);
    }

    void somarElementos() {
        int soma = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            soma += i.elemento;
        }
        System.out.println("\nA soma dos elementos é de: " + soma);
    }

    void inverterOrdem() {
        Celula anterior = null;
        Celula atual = primeiro.prox;
        Celula proximo = null;
        ultimo = primeiro.prox;

        while (atual != null) {
            proximo = atual.prox;
            atual.prox = anterior;
            anterior = atual;
            atual = proximo;
        }

        primeiro.prox = anterior;
    }

    void contarParesMultiplos() {
        int qntd = contarParesMultiplos(primeiro.prox);
        System.out.println("\nQuantidade de numeros pares e multiplos de 5: " + qntd);
    }

    int contarParesMultiplos(Celula i) {
        if (i == null) {
            return 0;
        } else {
            if (i.elemento % 2 == 0 && i.elemento % 5 == 0) {
                return 1 + contarParesMultiplos(i.prox);
            } else {
                return 0 + contarParesMultiplos(i.prox);
            }
        }
    }
}

