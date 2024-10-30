package estruturas;

public class arvoreBinaria {
    
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
