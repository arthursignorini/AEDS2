import java.util.*;

class Celula {
    Celula dir, esq, sup, inf;
    int elemento;

    Celula() {
        this(0);
    }

    Celula(int x) {
        this.elemento = x;
        this.dir = null;
        this.esq = null;
        this.sup = null;
        this.inf = null;
    }
}

class Matriz {
    public Celula inicio;
    public int NumLinhas;
    public int NumColunas;

    Matriz(int linha, int coluna) {
        this.NumLinhas = linha;
        this.NumColunas = coluna;
        this.inicio = createMatriz(linha, coluna);
    }

    Celula createMatriz(int linha, int coluna) {
        Celula inicio = createLinha(coluna);
        Celula linhaAnterior = inicio;

        for (int i = 1; i < linha; i++) {
            Celula novaLinha = createLinha(coluna);
            Celula acima = linhaAnterior;
            Celula atual = novaLinha;

            while (acima != null && atual != null) {
                acima.inf = atual;
                atual.sup = acima;
                acima = acima.dir;
                atual = atual.dir;
            }

            linhaAnterior = novaLinha;
        }

        return inicio;
    }

    Celula createLinha(int coluna) {
        Celula inicio = new Celula();
        Celula tmp = inicio;
        for (int i = 1; i < coluna; i++) {
            Celula nova = new Celula();
            tmp.dir = nova;
            nova.esq = tmp;
            tmp = nova;
        }
        return inicio;
    }

    void addTodosElementos(Scanner sc) {
        Celula tmp = inicio;
        for (Celula t = tmp; t != null; t = t.inf) {
            for (Celula d = t; d != null; d = d.dir) {
                d.elemento = sc.nextInt();
            }
        }
    }

    Matriz somaMatrizes(Matriz mat2){
        Matriz mat3 = new Matriz(mat2.NumLinhas, mat2.NumColunas);
        Celula i1 = inicio;
        Celula i2 = mat2.inicio;
        Celula i3 = mat3.inicio;

        Celula p = i2;
        Celula j = i3;

        for (Celula t = i1; t != null; t = t.inf) {
            Celula d = t;
            Celula k = p;
            Celula y = j;
        
            while (d != null && k != null && y != null) {
                y.elemento = d.elemento + k.elemento;
                d = d.dir;
                k = k.dir;
                y = y.dir;
            }
        
            p = p.inf;
            j = j.inf;
        }
        return mat3;
    }
    Matriz multiplicacao(Matriz mat2) {
        Matriz mat4 = new Matriz(NumLinhas, mat2.NumColunas); 
    
        int[] somaLinhasMat2 = new int[mat2.NumLinhas]; 
        Celula temp = mat2.inicio;
        for (int i = 0; i < mat2.NumLinhas; i++) {
            int soma = 0;
            Celula celula = temp;
            for (int j = 0; j < mat2.NumColunas; j++) {
                soma += celula.elemento;
                celula = celula.dir;
            }
            somaLinhasMat2[i] = soma;
            temp = temp.inf; 
        }
    
        Celula tmp = mat4.inicio; 
        for (int i = 0; i < this.NumLinhas; i++) {  
            Celula d = this.inicio; 
            Celula r = tmp; 
    
            for (int col = 0; col < mat2.NumColunas; col++) {
                r.elemento = d.elemento * somaLinhasMat2[i];  
                d = d.dir;
                r = r.dir;  
            }
            this.inicio = this.inicio.inf;  
            tmp = tmp.inf; 
        }
    
        return mat4; 
    }
    
    
    
    

    void mostra() {
        for (Celula i = inicio; i != null; i = i.inf) {
            for (Celula j = i; j != null; j = j.dir) {
                System.out.print(j.elemento + " ");
            }
            System.out.println();
        }
    }

    void getDiagonal() {
        if (NumColunas != NumLinhas) {
            System.out.println("Esta matriz não possui Diagonal Principal");
        } else {
            Celula i = inicio;
            while (i != null) {
                System.out.print(i.elemento + " ");
                i = i.dir;
                if (i != null) {
                    i = i.inf;
                }
            }
            System.out.println();
        }
    }

    void getDiagonalSecundaria() {
        if (NumColunas != NumLinhas) {
            System.out.println("Esta matriz não possui Diagonal Secundária");
        } else {
            Celula i = inicio;
            while (i.dir != null) {
                i = i.dir;
            }
            while (i != null) {
                System.out.print(i.elemento + " ");
                i = i.esq;
                if (i != null) {
                    i = i.inf;
                }
            }
            System.out.println();
        }
    }
}

class MatrizFlexivel {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testes = sc.nextInt();
        for(int i = 0; i<testes; i++){
            int linha1 = sc.nextInt();
            int coluna1 = sc.nextInt();
            Matriz mat1 = new Matriz(linha1, coluna1);
            mat1.addTodosElementos(sc);
            int linha2 = sc.nextInt();
            int coluna2 = sc.nextInt();
            Matriz mat2 = new Matriz(linha2, coluna2);
            mat2.addTodosElementos(sc);
            mat1.getDiagonal();
            mat1.getDiagonalSecundaria();
            Matriz mat3 = mat1.somaMatrizes(mat2);
            mat3.mostra();
            Matriz mat4 = mat2.multiplicacao(mat1);
            mat4.mostra();
        }
        
        sc.close();
    }
}

