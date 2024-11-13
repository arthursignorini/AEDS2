package estruturas;

public class arvoreBinaria {
    public static void main(String[] args) {
        Arvore arvore = new Arvore();
        arvore.inserir(1);
        arvore.inserir(4);
        arvore.inserir(6);
        arvore.inserir(3);
        arvore.inserir(9);
        arvore.mostrar();
    }
}

class Arvore {
   public No raiz;

   Arvore () {
      raiz = null;
   }

   // PESQUISAR
   public boolean pesquisar (int elemento) {
      return pesquisar(raiz, elemento);
   }
   public boolean pesquisar(No no, int elemento) {
      if(no == null) {
         return false;
      }
      if(elemento == no.elemento) {
         return true;
      } else if (elemento < no.elemento) {
         return pesquisar(no.esq, elemento);
      } else {
         return pesquisar(no.dir,elemento);
      }
   }
   
   // INSERIR
   public void inserir(int elemento) {
      raiz = inserir(raiz, elemento);
   }

   public No inserir (No no, int elemento) {
      if(no == null) {
         return new No(elemento);
      }
      if(elemento < no.elemento) {
         no.esq = inserir(no.esq, elemento);
      } else if (elemento > no.elemento) {
         no.dir = inserir(no.dir, elemento);
      }
      return no;
   }

   public void remover(int elemento) {
      raiz = remover(raiz, elemento);
   }

   public No remover(No no, int elemento) {
      if(no == null) {
         return null;
      }
      if(elemento < no.elemento) {
         no.esq = remover(no.esq, elemento);
      } else if (elemento > no.elemento) {
         no.dir = remover(no.dir, elemento);
      } else {
         // CASO NAO TENHA FILHOS
         if(no.esq == null && no.dir == null) {
            return null;
         }
         // CASO TENHA 1 FILHO
         if(no.esq == null) {
            return no.dir;
         } else if (no.dir == null) {
            return no.esq;
         }
         // CASO TENHA 2 FILHOS
         No substituto = acharMinimo(no.dir);
         no.elemento = substituto.elemento;
         no.dir = remover(no.dir, substituto.elemento);
      }
      return no;
     }

     public No acharMinimo(No no) {
      while (no.esq != null) {
          no = no.esq;
      }
      return no;
   }

   // MOSTRAR ELEMENTOS - CAMINHAMENTO
   public void mostrar() {
      mostrar(raiz);
      System.out.println();
  }

  private void mostrar(No no) {
      if (no != null) {
          mostrar(no.esq);
          System.out.print(no.elemento + " ");
          mostrar(no.dir);
      }
  }
}

class No {
    public int elemento; 
    public No esq, dir;  
 
    public No(int elemento) {
       this.elemento = elemento;
       this.esq = null;
       this.dir = null;
    }
 
    public No(int elemento, No esq, No dir) {
       this.elemento = elemento;
       this.esq = esq;
       this.dir = dir;
    }
 }


 /*

metodo de pesquisa

se a raiz estiver vazia, retorna uma pesquisa negativa
se o elemento for igual ao da raiz, retorna uma positiva
se o elemento procurado for menor que o da raiz, ir para arvore da esquerda
se o elemento procurado for maior que o da raiz, ir para arvore da direita

metodo de caminhamento

central ou em ordem = esquerda, mostrar elemento e vai para direita = ordem crescente
pós-fixado ou pós-ordem = esquerda, direita, mostrar elemento
pré-fixado ou pré-ordem = mostrar elemento, ir para esquerda e depois direita

metodo de inserção

se a raiz estiver vazia, insere nela
se o novo elemento for menor que a raiz, ir para a subarvore da esquerda
se o novo elemento for maior, ir para a subarvore da direita
se o novo elemento for igual a raiz, nao inserir

metodo remover

se o elemento estiver em uma folha, remover ela
se for um nó intermediario com um unico filho, fazer com que o seu pai aponte para o filho
se for um nó intermediario com dois filhos, o elemento a ser removido deve ser substituido
ou pelo maior nó da subarvore a esquerda ou pelo menor nó da subarvore a direita
*/
