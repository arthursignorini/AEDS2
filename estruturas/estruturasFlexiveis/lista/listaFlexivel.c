#include <stdio.h>
#include <stdlib.h>

typedef struct Celula {
    int elemento;
    struct Celula *prox;
    struct Celula *ant;
} Celula ;

typedef struct Lista
{
   struct Celula *primeiro;
   struct Celula *ultimo;
} Lista ;

Lista* inicializar() {
    Lista *lista = (Lista*)malloc(sizeof(Lista));
    lista->primeiro = (Celula*)malloc(sizeof(Celula));
    lista->primeiro->prox = NULL;
    lista->ultimo = lista->primeiro;
    return lista;
}

void inserirInicio (Lista *lista, int x) {
    Celula *temp = (Celula*)malloc(sizeof(Celula));
    temp -> elemento = x;
    temp -> prox = lista -> primeiro -> prox;
    lista -> primeiro -> prox = temp;
    if(lista -> primeiro == lista -> ultimo) {
        lista -> ultimo = temp;
    }
}

void inserirFim(Lista *lista, int x) {
    lista -> ultimo -> prox = (Celula*)malloc(sizeof(Celula));
    lista -> ultimo = lista -> ultimo -> prox;
    lista -> ultimo -> elemento = x;
    lista -> ultimo -> prox = NULL;
}

int removerInicio(Lista *lista) {
    if(lista -> primeiro == lista -> ultimo) {
        printf("LISTA VAZIA");
        exit(1);
    }
    Celula *temp = lista -> primeiro -> prox;
    int elemento = temp ->elemento;
    lista -> primeiro -> prox = temp -> prox;

    if(temp == lista ->ultimo) {
        lista ->ultimo = lista->primeiro;
    }

    temp -> prox = NULL;
    free(temp);
    return elemento;
}

int removerFim(Lista *lista) {
    if(lista -> primeiro == lista -> ultimo) {
        printf("LISTA VAZIA");
        exit(1);
    }
    Celula *i;
    for(i=lista->primeiro; i->prox!=lista->ultimo; i=i->prox);
    int elemento = lista ->ultimo -> elemento;
    free(lista->ultimo);
    lista->ultimo = i;
    lista->ultimo->prox = NULL;
    return elemento;
}

void inserir(Lista *lista, int pos, int x) {
    int tam = tamanho(lista);
    if(pos < 0 || pos > tam) {
        printf("Posicao invalida");
        exit(1);
    }
    if(pos == 0) {
        inserirInicio(lista, x);
    } else if(pos == tam) {
        inserirFim(lista, x);
    } else {
        Celula *j = lista->primeiro;
        for(int i=0; i<pos; i++) {
            j = j->prox;
        }
        Celula *temp = (Celula*)malloc(sizeof(Celula));
        temp -> elemento = x;
        temp -> prox = j->prox;
        j -> prox = temp;
    }
}

int remover (Lista *lista, int pos) {
    int tam = tamanho(lista);
    if(pos < 0 || pos >= tam) {
        printf("Posicao invalida");
        exit(1);
    }
    if(lista -> primeiro == lista -> ultimo) {
        printf("Lista vazia");
        exit(1);
    }
    if(pos == 0) {
        removerInicio(lista);
    } else if(pos == tam-1) {
        removerFim(lista);
    } else {
        Celula *j = lista -> primeiro;
        for(int i=0; i<pos; i++) {
            j = j -> prox;
        }
        Celula *temp = j -> prox;
        int elemento = temp -> elemento;
        j -> prox = temp -> prox;
        temp -> prox = NULL;
        free(temp);
        return elemento;
    }
}

int tamanho(Lista *lista) {
    int cont = 0;
    Celula *i;
    for(i=lista->primeiro->prox; i!=NULL; i=i->prox) {
        cont++;
    }
    return cont;
}

void mostrar(Lista *lista) {
    printf("\n");
    for(Celula *i = lista ->primeiro->prox; i!=NULL; i=i->prox) {
        printf("%d ",i->elemento);
    }
}


int main() {
    Lista *lista = inicializar();
    inserirInicio(lista, 1);
    inserirInicio(lista, 2);
    inserirInicio(lista, 3);
    mostrar(lista);
    inserirFim(lista, 4);
    inserirFim(lista, 5);
    mostrar(lista);
    removerInicio(lista);
    removerFim(lista);
    mostrar(lista);
    inserir(lista, 2, 6);
    remover(lista, 1);
    mostrar(lista);

    return 0;
}