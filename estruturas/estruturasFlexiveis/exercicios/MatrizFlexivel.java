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
    Celula sup;
    Celula dir;
    Celula inf;
    Celula esq;

    Celula() {
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
    
    Matriz() {
        this(3, 3);  
    }
    
    Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        primeiraLinha(); 
        outrasLinhas();  
    }

    void primeiraLinha() {
        inicio = new Celula();  
        Celula x = inicio;  
        
        for (int i = 1; i < coluna; i++) {
            Celula temp = new Celula();
            x.dir = temp;
            temp.esq = x;
            x = temp;
        }
    }

    void outrasLinhas() {
        Celula atual = inicio;  
        Celula linhaAcima = atual; 
        
        // criar as outras linhas
        for (int i = 1; i < linha; i++) {

            // cria a primeira célula da nova linha
            Celula novaLinha = new Celula();
            linhaAcima.inf = novaLinha;  
            novaLinha.sup = linhaAcima;
            
            Celula celulaAtual = novaLinha;
            Celula celulaAcima = linhaAcima;
            
            // cria as células restantes na nova linha
            for (int j = 1; j < coluna; j++) {
                Celula novaCelula = new Celula();
                celulaAtual.dir = novaCelula;
                novaCelula.esq = celulaAtual;
                
                celulaAcima = celulaAcima.dir;
                celulaAcima.inf = novaCelula;
                novaCelula.sup = celulaAcima;
                
                celulaAtual = novaCelula;
            }
            linhaAcima = novaLinha;
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
