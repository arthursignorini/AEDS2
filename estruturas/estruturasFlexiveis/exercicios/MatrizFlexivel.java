package estruturasFlexiveis.exercicios;

import java.util.Scanner;

public class MatrizFlexivel {
    public static void main(String[] args) {
        Matriz mat = new Matriz(4,5);
        mat.matrizElementos();
        mat.printarMatriz();
    }
}

class Celula {
    int elemento;
    Celula sup, dir, inf, esq;

    Celula () {
        this(0);
    }

    Celula (int x) {
        elemento = x;
        sup = null;
        dir = null;
        inf = null;
        esq = null;
    }
}

class Matriz {
    Celula inicio;
    int linha;
    int coluna;

    Matriz () {
        this(3,3);
    }

    Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        criarMatriz();
    }

    void criarMatriz() {
        inicio = new Celula();
        Celula atual = inicio;
        Celula linhaAcima = null;

        // Criação da primeira linha
        for (int i = 1; i < coluna; i++) {
            atual.dir = new Celula();
            atual.dir.esq = atual;
            atual = atual.dir;
        }

        // Criação das outras linhas
        for (int i = 1; i < linha; i++) {

            if(linhaAcima == null) {
                linhaAcima = inicio;
            } else {
                linhaAcima = linhaAcima.inf;
            }

            atual = new Celula();
            linhaAcima.inf = atual;
            atual.sup = linhaAcima;

            Celula celulaAcima = linhaAcima;
            Celula celulaAtual = atual;

            for (int j = 1; j < coluna; j++) {
                celulaAtual.dir = new Celula();
                celulaAtual.dir.esq = celulaAtual;

                celulaAcima = celulaAcima.dir;
                celulaAtual.dir.sup = celulaAcima;
                celulaAcima.inf = celulaAtual.dir;

                celulaAtual = celulaAtual.dir;
            }
        }
    }

    void matrizElementos () {
        Scanner sc = new Scanner(System.in);
        Celula atual = inicio;
        for(int i=0; i<this.linha; i++) {
            Celula linhaAtual = atual;
            for(int j=0;j<this.coluna; j++) {
                linhaAtual.elemento = sc.nextInt();
                linhaAtual = linhaAtual.dir;
            }
            atual = atual.inf;
        }
    }

    void printarMatriz () {
        Celula atual = inicio;
        for(int i=0; i<this.linha; i++) {
            Celula linhaAtual = atual;
            for(int j=0; j<coluna; j++) {
                System.out.print(linhaAtual.elemento + " ");
                linhaAtual = linhaAtual.dir;
            }
            System.out.println();
            atual = atual.inf;
        }
    }
}