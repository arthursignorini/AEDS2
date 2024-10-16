package estruturasFlexiveis.exercicios;

// Classe que representa a célula da pilha (cada elemento na pilha)
class CelulaPilha {
    int elemento;
    CelulaPilha prox;

    public CelulaPilha(int elemento) {
        this.elemento = elemento;
        this.prox = null;
    }
}

// Classe que representa a célula da lista de pilhas (cada nó da lista contém uma pilha)
class CelulaLista {
    CelulaPilha topo;  // Referência para o topo da pilha
    CelulaLista prox;  // Referência para o próximo nó da lista

    public CelulaLista() {
        this.topo = null;
        this.prox = null;
    }

    // Método para empilhar (inserir elemento na pilha)
    public void empilhar(int elemento) {
        CelulaPilha novaCelula = new CelulaPilha(elemento);
        novaCelula.prox = topo;
        topo = novaCelula;
    }

    // Método para desempilhar (remover elemento da pilha)
    public int desempilhar() throws Exception {
        if (topo == null) {
            throw new Exception("Pilha vazia!");
        }
        int elemento = topo.elemento;
        topo = topo.prox;
        return elemento;
    }

    // Método para verificar se a pilha está vazia
    public boolean estaVazia() {
        return topo == null;
    }
}

// Classe que representa a lista de pilhas flexíveis
class Lista {
    CelulaLista inicio;  // Início da lista
    CelulaLista fim;     // Fim da lista

    public Lista() {
        this.inicio = null;
        this.fim = null;
    }

    // Método para adicionar uma nova pilha à lista
    public void adicionarPilha() {
        CelulaLista novaCelula = new CelulaLista();
        if (inicio == null) {
            inicio = novaCelula;
            fim = novaCelula;
        } else {
            fim.prox = novaCelula;
            fim = novaCelula;
        }
    }

    // Método para empilhar em uma pilha específica da lista (pelo índice)
    public void empilharNaPilha(int indice, int elemento) throws Exception {
        CelulaLista atual = inicio;
        int contador = 0;
        while (atual != null && contador < indice) {
            atual = atual.prox;
            contador++;
        }

        if (atual == null) {
            throw new Exception("Pilha não encontrada no índice especificado.");
        }

        atual.empilhar(elemento);
    }

    // Método para desempilhar de uma pilha específica da lista (pelo índice)
    public int desempilharDaPilha(int indice) throws Exception {
        CelulaLista atual = inicio;
        int contador = 0;
        while (atual != null && contador < indice) {
            atual = atual.prox;
            contador++;
        }

        if (atual == null || atual.estaVazia()) {
            throw new Exception("Pilha não encontrada ou está vazia no índice especificado.");
        }

        return atual.desempilhar();
    }

    // Método para mostrar o estado atual da lista e das pilhas
    public void mostrarListaDePilhas() {
        CelulaLista atualLista = inicio;
        int pilhaIndex = 0;

        while (atualLista != null) {
            System.out.println("Pilha " + pilhaIndex + ":");
            CelulaPilha atualPilha = atualLista.topo;
            while (atualPilha != null) {
                System.out.println("  Elemento: " + atualPilha.elemento);
                atualPilha = atualPilha.prox;
            }
            atualLista = atualLista.prox;
            pilhaIndex++;
        }
    }
}

// Exemplo de uso da lista de pilhas flexíveis
public class ex3 {
    public static void main(String[] args) {
        try {
            // Criando a lista de pilhas flexíveis
            Lista listaDePilhas = new Lista();

            // Adicionando três pilhas à lista
            listaDePilhas.adicionarPilha();
            listaDePilhas.adicionarPilha();
            listaDePilhas.adicionarPilha();

            // Empilhando elementos na primeira pilha (índice 0)
            listaDePilhas.empilharNaPilha(0, 10);
            listaDePilhas.empilharNaPilha(0, 20);
            listaDePilhas.empilharNaPilha(0, 30);

            // Empilhando elementos na segunda pilha (índice 1)
            listaDePilhas.empilharNaPilha(1, 40);
            listaDePilhas.empilharNaPilha(1, 50);

            // Empilhando elementos na terceira pilha (índice 2)
            listaDePilhas.empilharNaPilha(2, 60);

            // Mostrando o estado atual da lista de pilhas
            System.out.println("Estado atual da lista de pilhas:");
            listaDePilhas.mostrarListaDePilhas();

            // Desempilhando da primeira pilha (índice 0)
            System.out.println("Desempilhado da primeira pilha: " + listaDePilhas.desempilharDaPilha(0));

            // Desempilhando da segunda pilha (índice 1)
            System.out.println("Desempilhado da segunda pilha: " + listaDePilhas.desempilharDaPilha(1));

            // Mostrando novamente o estado da lista de pilhas após desempilhar
            System.out.println("\nEstado da lista de pilhas após desempilhar:");
            listaDePilhas.mostrarListaDePilhas();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
