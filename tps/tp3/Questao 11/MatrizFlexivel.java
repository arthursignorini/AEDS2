import java.util.Scanner;

public class MatrizFlexivel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCasos = sc.nextInt(); // Lê o número de casos de teste
        
        for (int caso = 0; caso < numCasos; caso++) {
            // Lê a primeira matriz
            int l1 = sc.nextInt();
            int c1 = sc.nextInt();
            Matriz mat1 = new Matriz(l1, c1);
            mat1.matrizElementos(sc); // Passa o Scanner para o método ler elementos
            
            // Lê a segunda matriz
            int l2 = sc.nextInt();
            int c2 = sc.nextInt();
            Matriz mat2 = new Matriz(l2, c2);
            mat2.matrizElementos(sc); // Passa o Scanner para o método ler elementos
            
            // Exibe as diagonais da primeira matriz
            mat1.mostrarDiagonalPrincipal();
            mat1.mostrarDiagonalSecundaria();
            
            // Soma das matrizes
            if (l1 == l2 && c1 == c2) {
                Matriz soma = mat1.soma(mat2);
                soma.printarMatriz();
            } else {
                System.out.println("As matrizes não podem ser somadas.");
            }
            
            // Multiplicação das matrizes
            if (c1 == l2) {
                Matriz multiplicacao = mat1.multiplicacao(mat2);
                multiplicacao.printarMatriz();
            } else {
                System.out.println("As matrizes não podem ser multiplicadas.");
            }
        }
        
        sc.close(); // Fecha o scanner
    }
}

class Celula {
    int elemento;
    Celula sup, dir, inf, esq;

    Celula() {
        this(0);
    }

    Celula(int x) {
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
        criarMatriz();
    }

    void criarMatriz() {
        inicio = new Celula();
        Celula atual = inicio;

        // Criação da primeira linha
        for (int i = 1; i < coluna; i++) {
            atual.dir = new Celula();
            atual.dir.esq = atual;
            atual = atual.dir;
        }

        // Criação das outras linhas
        Celula linhaAtual = inicio;
        for (int i = 1; i < linha; i++) {
            Celula novaLinha = new Celula();
            linhaAtual.inf = novaLinha;  // Conecta a linha atual com a nova linha
            novaLinha.sup = linhaAtual;   // Conecta a nova linha com a linha atual

            // Criação das colunas da nova linha
            Celula atualLinha = novaLinha;
            for (int j = 0; j < coluna; j++) {
                if (j > 0) {
                    atualLinha.dir = new Celula();
                    atualLinha.dir.esq = atualLinha;
                    atualLinha = atualLinha.dir;
                }
                if (i > 0) {
                    Celula celulaAcima = inicio;
                    for (int k = 0; k < j; k++) {
                        celulaAcima = celulaAcima.dir; // Navega para a coluna correta
                    }
                    atualLinha.sup = celulaAcima; // Conecta à célula acima
                    celulaAcima.inf = atualLinha;   // Conecta à célula abaixo
                }
            }
            linhaAtual = novaLinha; // Mover para a nova linha
        }
    }

    void matrizElementos(Scanner sc) {
        Celula atual = inicio;
        for (int i = 0; i < this.linha; i++) {
            Celula linhaAtual = atual;
            for (int j = 0; j < this.coluna; j++) {
                linhaAtual.elemento = sc.nextInt();
                linhaAtual = linhaAtual.dir;
            }
            atual = atual.inf;
        }
    }

    void printarMatriz() {
        Celula atual = inicio;
        for (int i = 0; i < this.linha; i++) {
            Celula linhaAtual = atual;
            for (int j = 0; j < coluna; j++) {
                System.out.print(linhaAtual.elemento + " ");
                linhaAtual = linhaAtual.dir;
            }
            System.out.println();
            atual = atual.inf;
        }
    }

    Matriz soma(Matriz outra) {
        if (this.linha != outra.linha || this.coluna != outra.coluna) {
            throw new IllegalArgumentException("As matrizes devem ter as mesmas dimensões.");
        }
        Matriz resultado = new Matriz(this.linha, this.coluna);
        Celula atual = this.inicio;
        Celula atualOutra = outra.inicio;
        Celula atualResultado = resultado.inicio;

        for (int i = 0; i < this.linha; i++) {
            Celula linhaAtualResultado = atualResultado;
            for (int j = 0; j < this.coluna; j++) {
                linhaAtualResultado.elemento = atual.elemento + atualOutra.elemento;
                linhaAtualResultado = linhaAtualResultado.dir;
                atual = atual.dir;
                atualOutra = atualOutra.dir;
            }
            atualResultado = atualResultado.inf;
            if(atual != null) {
                atual = atual.inf;
            }
            if(atualOutra != null) {
                atualOutra = atualOutra.inf;
            }
        }

        return resultado;
    }

    Matriz multiplicacao(Matriz outra) {
        if (this.coluna != outra.linha) {
            throw new IllegalArgumentException("O número de colunas da primeira matriz deve ser igual ao número de linhas da segunda matriz.");
        }
        Matriz resultado = new Matriz(this.linha, outra.coluna);

        Celula linhaAtualResultado = resultado.inicio;
        for (int i = 0; i < this.linha; i++) {
            for (int j = 0; j < outra.coluna; j++) {
                int soma = 0;
                Celula atualLinha = this.inicio;
                Celula atualColuna = outra.inicio;

                for (int k = 0; k < this.coluna; k++) {
                    soma += atualLinha.elemento * atualColuna.elemento;
                    atualLinha = atualLinha.dir;
                    atualColuna = atualColuna.inf;
                }

                linhaAtualResultado.elemento = soma;
                linhaAtualResultado = linhaAtualResultado.dir;
            }
            resultado.inicio = resultado.inicio.inf; // Mover para a próxima linha de resultado
        }

        return resultado;
    }

    void mostrarDiagonalPrincipal() {
        Celula atual = inicio;
        for (int i = 0; i < Math.min(linha, coluna); i++) {
            System.out.print(atual.elemento + " ");
            atual = atual.dir;
            if(atual != null) {
                atual = atual.inf;
            } // Mover para a próxima célula da diagonal principal
        }
        System.out.println();
    }

    void mostrarDiagonalSecundaria() {
        Celula atual = inicio;
        for (int i = 0; i < linha; i++) {
            Celula temp = atual;
            for (int j = 0; j < coluna - 1; j++) {
                temp = temp.dir; // Navega para a última coluna da linha atual
            }
            System.out.print(temp.elemento + " ");
            atual = atual.inf; // Mover para a próxima linha
        }
        System.out.println();
    }
}
