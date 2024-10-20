package estruturasFlexiveis.exercicios;

// EXERCÍCIOS FEITOS DO SLIDE DE PILHA FLEXÍVEL

public class ex5 {
    public static void main(String[] args) {
        try {
            Pilha pilha = new Pilha();
            pilha.inserir(1);
            pilha.inserir(2);
            pilha.inserir(3);
            pilha.inserir(4);
            pilha.inserir(5);
            pilha.mostrar();
            pilha.soma();
            pilha.somaRec();
            pilha.maiorElemento();
            pilha.maiorElementoRec();
            pilha.elementosRemovidos();
            pilha.elementosRemovidosRec();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Celula {
    int elemento;
    Celula prox;

    Celula () {
        elemento = 0;
        prox = null;
    }

    Celula (int x) {
        elemento = x;
        prox = null;
    }
}

class Pilha {
    Celula topo;

    Pilha() {
        topo = null;
    }

    void inserir(int x) {
        Celula temp = new Celula(x);
        temp.prox = topo;
        topo = temp;
        temp = null;
    }

    int remover() throws Exception {
        if(topo == null) {
            throw new Exception ("PILHA VAZIA");
        }
        int elemento = topo.elemento;
        Celula temp = topo;
        topo = temp.prox;
        temp.prox = null;
        temp = null;
        return elemento;
    }

    void mostrar() {
        Celula i;
        for(i=topo; i!=null; i=i.prox) {
            System.out.println(i.elemento);
        }
    }

    void soma() {
        Celula i;
        int cont = 0;
        for(i=topo; i!=null; i=i.prox) {
            cont += i.elemento;
        }
        System.out.println("A soma é: " + cont);
    }

    void somaRec() {
        System.out.println("A soma recurssiva: " + somaRec(topo));
    }

    int somaRec(Celula i) {
        if(i == null) {
            return 0;
        } else {
            return i.elemento + somaRec(i.prox);
        }
    }

    void maiorElemento() {
        int maior = topo.elemento;
        Celula i;
        for(i=topo; i!=null; i=i.prox) {
            if(i.elemento > maior) {
                maior = i.elemento;
            }
        }
        System.out.println("Maior elemento: " + maior);
    }

    void maiorElementoRec() {
        Celula resp = maiorElementoRec(topo, topo);
        System.out.println("O maior elemento recursivo é: " + resp.elemento);
    }

    Celula maiorElementoRec(Celula i, Celula maior) {
        if(i==null) {
            return maior;
        } else {
            if(i.elemento > maior.elemento) {
                maior = i;
            }
            return maiorElementoRec(i.prox, maior);
        }
    }

    void elementosRemovidos() {
        Celula i;
        System.out.print("Elementos na ordem em que serao removidos: ");
        for(i=topo;i!=null;i=i.prox) {
            System.out.print(i.elemento + " ");
        }
    }

    void elementosRemovidosRec() {
        System.out.println();
        System.out.print("Elementos na ordem em que foram inseridos: ");
        elementosRemovidosRec(topo);
        System.out.println();
    }

    void elementosRemovidosRec(Celula i) {
        if(i==null){
            return;
        } else {
            elementosRemovidosRec(i.prox);
            System.out.print(i.elemento + " ");
        }
    }
}
