package tps.tp3.ex11_MatrizDinamica;

import java.util.Scanner;

class Celula {
    public int elemento;
    public Celula inf, sup, esq, dir;

    public Celula() {
        this(0);
    }

    public Celula(int elemento) {
        this.elemento = elemento;
        this.inf = sup = esq = dir = null;
    }
}

public class Matriz {
    private Celula inicio;
    private int linha, coluna;

    public Matriz() {
        this(3, 3);
    }

    public Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;

        // Codigo omitido:
        inicio = new Celula();
        Celula i = inicio;
        Celula j;

        for (int a = 0; a < linha; a++) {
            j = i;
            for (int b = 1; b < coluna; b++) {
                j.dir = new Celula();
                j.dir.esq = j;
                j = j.dir;
            }
            if (a < linha - 1) {
                i.inf = new Celula();
                i.inf.sup = i;
                i = i.inf;
            }
        }

        i = inicio;
        for (int a = 0; a < linha - 1; a++) {
            j = i;
            Celula k = i.inf;
            for (int b = 0; b < coluna; b++) {
                j.inf = k;
                k.sup = j;
                j = j.dir;
                k = k.dir;
            }
            i = i.inf;
        }
    }

    public void mostrar() {
        Celula i = inicio;
        Celula j = inicio;
        for (int a = 0; a < linha; a++) {
            for (int b = 0; b < coluna; b++) {
                System.out.print(j.elemento + " ");
                j = j.dir;
            }
            System.out.println();
            i = i.inf;
            j = i;
        }
    }

    public Celula getInicio() {
        return inicio;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public Celula getPosition(int i, int j) {
        Celula temp = inicio;

        // Percorre as linhas até chegar na linha i
        for (int linha = 0; linha < i; linha++) {
            temp = temp.inf;
        }

        // Percorre as colunas até chegar na coluna j
        for (int coluna = 0; coluna < j; coluna++) {
            temp = temp.dir;
        }

        return temp;
    }

    public void set(int i, int j, int x) {
        Celula temp = getPosition(i, j);
        temp.elemento = x;
    }

    public static Matriz soma(Matriz a, Matriz b) {
        if (a.linha != b.linha || a.coluna != b.coluna) {
            throw new IllegalArgumentException("Matrizes de tamanhos diferentes");
        }
        Matriz resp = new Matriz(a.linha, a.coluna);
        for (int i = 0; i < a.linha; i++) {
            for (int j = 0; j < a.coluna; j++){
                // Soma os elementos das matrizes a e b e atribui o resultado à matriz resp
                resp.set(i, j, a.getPosition(i, j).elemento + b.getPosition(i, j).elemento);
            }
        }

        return resp;
    }

    public static Matriz multiplicacao(Matriz a, Matriz b) {
        if (a.coluna != b.linha) {
            throw new IllegalArgumentException("Matrizes de tamanhos incompatíveis");
        }
        Matriz resp = new Matriz(a.linha, b.coluna);
        for (int i = 0; i < a.linha; i++) {
            for (int j = 0; j < b.coluna; j++) {
                int soma = 0;
                for (int k = 0; k < a.coluna; k++) {
                    soma += a.get(i, k) * b.get(k, j);
                }
                resp.set(i, j, soma);
            }
        }
        return resp;
    }

    public int[] getDiagonalPrincipal(){
        // A diagonal principal tem o tamanho da menor dimensão da matriz
        int[] diagP = new int[Math.min(linha, coluna)];
        Celula temp = inicio;
        for (int i = 0; i < diagP.length; i++) {
            diagP[i] = temp.elemento;
            // Se houver uma célula abaixo e à direita, vá para a próxima célula
            if (temp.inf != null && temp.dir != null) {
                temp = temp.inf.dir;
            }
        }
        return diagP;
    }

    public void mostrarDiagonalPrincipal() {
        int[] diagP = getDiagonalPrincipal();
        for (int i = 0; i < diagP.length; i++) {
            System.out.print(diagP[i] + " ");
        }
        System.out.println();
    }

    public void mostrarDiagonalSecundaria() {
        int[] diagS = getDiagonalSecundaria();
        for (int i = 0; i < diagS.length; i++) {
            System.out.print(diagS[i] + " ");
        }
        System.out.println();
    }

    public int[] getDiagonalSecundaria() {
        int [] diagonalS = new int[Math.min(linha, coluna)];
        Celula temp = inicio;
        // Ir para o final da primeira linha
        for (int i = 0; i < coluna - 1; i++){
            if (temp.dir != null){
                temp = temp.dir;
            }
        }
        // Ir para a última linha
        for (int i = 0; i < diagonalS.length; i++){
            // Atribui o valor da célula à diagonal secundária
            diagonalS[i] = temp.elemento;
            // Se houver uma célula abaixo e à esquerda, vá para a próxima célula
            if (temp.inf != null && temp.esq != null){
                temp = temp.inf.esq;
            }
        }
        return diagonalS;
    }


    public Celula remover(int i, int j) {
        Celula temp = getPosition(i, j);
        if (temp == null) {
            throw new IllegalArgumentException("Posição inválida");
        }
        if (temp.esq != null) {
            temp.esq.dir = temp.dir;
        }
        if (temp.dir != null) {
            temp.dir.esq = temp.esq;
        }
        if (temp.sup != null) {
            temp.sup.inf = temp.inf;
        }
        if (temp.inf != null) {
            temp.inf.sup = temp.sup;
        }
        return temp;
    }

    public void drawMatrix() {
        Celula i = inicio;
        Celula j = inicio;
        for (int a = 0; a < linha; a++) {
            for (int b = 0; b < coluna; b++) {
                System.out.print(j.elemento + " ");
                j = j.dir;
            }
            System.out.println();
            i = i.inf;
            j = i;
        }
    }

    public int get(int i, int j) {
        Celula temp = getPosition(i, j);
        return temp.elemento;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for (int testCase = 0; testCase < N; testCase++) {

            // ler dimensoes de m1
            int l1 = sc.nextInt();
            int c1 = sc.nextInt();

            Matriz m1 = new Matriz(l1, c1);

            // Ler elementos de m1
            for (int i = 0; i < l1; i++) {
                for (int j = 0; j < c1; j++) {
                    int value = sc.nextInt();
                    m1.set(i, j, value);
                }
            }

            // ler dimensoes de m2
            int l2 = sc.nextInt();
            int c2 = sc.nextInt();

            Matriz m2 = new Matriz(l2, c2);

            // Ler elementos de m2
            for (int i = 0; i < l2; i++) {
                for (int j = 0; j < c2; j++) {
                    int value = sc.nextInt();
                    m2.set(i, j, value);
                }
            }

            // Diagonais de m1
            m1.mostrarDiagonalPrincipal();
            m1.mostrarDiagonalSecundaria();

            // Soma das matrizes
            Matriz sum = Matriz.soma(m1, m2);
            sum.mostrar();

            // multiplicacao das matrizes
            Matriz mult = Matriz.multiplicacao(m1, m2);
            mult.mostrar();
        }
        sc.close();
    }
}
