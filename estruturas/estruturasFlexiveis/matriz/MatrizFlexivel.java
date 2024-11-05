package estruturasFlexiveis.matriz;

import java.util.Scanner;

public class MatrizFlexivel {
    public static void main(String[] args) {
        Matriz mat = new Matriz(3,3);
        mat.matrizElementos();
        System.out.println("\n\n");
        mat.printarMatriz();
        System.out.println("\n\n");
        mat.diagonalPrincipal();
        System.out.println("\n\n");
        mat.diagonalSecundaria();
        System.out.println("\n\n");
        Matriz m2 = new Matriz(3,3);
        m2.matrizElementos();
        System.out.println("\n\n");
        m2.printarMatriz();
        System.out.println("\n\n");
        mat.somarMatriz(m2);
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

    void diagonalPrincipal() {
        Celula i=inicio;
        Celula j=inicio;
        while(i!=null) {
            while (j!=null) {
                if(i == j) {
                    System.out.print(i.elemento + " ");
                }
                j = j.dir;
            }
            i=i.inf;
            if(i!=null) {
                i=i.dir;
            }
            j=i;
        }
    }

    void diagonalSecundaria() {
        Celula i=inicio;
        while (i.dir !=null) {
            i=i.dir;
        }
        while(i != null) {
            System.out.print(i.elemento + " ");
            i=i.inf;
            if(i!=null) {
                i=i.esq;
            }
        }
        
    }

    void somarMatriz(Matriz m2) {
        Matriz m3 = new Matriz(3,3);
        Celula i1 = inicio;
        Celula i2 = m2.inicio;
        Celula i3 = m3.inicio;
        
        Celula j1 = inicio;
        Celula j2 = m2.inicio;
        Celula j3 = m3.inicio;
        
        while(i1 != null) {
            while (j1!=null) {
                j3.elemento = j1.elemento + j2.elemento;
                j1=j1.dir;
                j2=j2.dir;
                j3=j3.dir;
            }
            i1 = i1.inf;
            i2 = i2.inf;
            i3 = i3.inf;

            j1 = i1;
            j2 = i2;
            j3 = i3;
        }
        m3.printarMatriz();
    }
}