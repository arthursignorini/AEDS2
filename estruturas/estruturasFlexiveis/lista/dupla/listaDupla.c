#include<stdio.h>
#include<stdlib.h>

typedef struct Celula {
    int elemento;
    struct Celula *prox;
    struct Celula *ant;
} Celula;

typedef struct Lista {
    struct Celula *primeiro;
    struct Celula *ultimo;
} Lista;

Celula* iniciarCelula() {
    Celula *celula = (Celula*)malloc(sizeof(Celula));
    celula->elemento = 0;
    celula->ant = NULL;
    celula->prox = NULL;
    return celula;
}

Lista* iniciarLista() {
    Lista *lista = (Lista*)malloc(sizeof(Lista));
    lista->primeiro = iniciarCelula();
    lista->ultimo = lista->primeiro;
    return lista;
}

void inserirInicio(Lista *lista, int x) {
    Celula *temp = iniciarCelula();
    temp->elemento = x;
    if (lista->primeiro->prox == NULL) {
        lista->primeiro->prox = temp;
        lista->ultimo = temp;
        temp->ant = lista->primeiro;
    } else {
        temp->prox = lista->primeiro->prox;
        temp->ant = lista->primeiro;
        lista->primeiro->prox->ant = temp;
        lista->primeiro->prox = temp;
    }
}

void inserirFim(Lista *lista, int x) {
    Celula *temp = iniciarCelula();
    temp->elemento = x;
    temp->ant = lista->ultimo;
    lista->ultimo->prox = temp;
    lista->ultimo = temp;
}

int removerInicio(Lista *lista) {
    if (lista->primeiro == lista->ultimo) {
        printf("LISTA VAZIA\n");
        exit(1);
    }
    Celula *temp = lista->primeiro->prox;
    int elemento = temp->elemento;
    lista->primeiro->prox = temp->prox;
    if (temp->prox != NULL) {
        temp->prox->ant = lista->primeiro;
    } else {
        lista->ultimo = lista->primeiro;
    }
    free(temp);
    return elemento;
}

int removerFim(Lista *lista) {
    if (lista->primeiro == lista->ultimo) {
        printf("LISTA VAZIA\n");
        exit(1);
    }
    Celula *temp = lista->ultimo;
    int elemento = temp->elemento;
    lista->ultimo = temp->ant;
    lista->ultimo->prox = NULL;
    free(temp);
    return elemento;
}

void mostrar(Lista *lista) {
    printf("\n");
    for (Celula *i = lista->primeiro->prox; i != NULL; i = i->prox) {
        printf("%d ", i->elemento);
    }
    printf("\n");
}

void inserir(Lista *lista, int pos, int x) {
    int tam = tamanho(lista);
    if (pos < 0 || pos > tam) {
        printf("POSICAO INVALIDA\n");
        exit(1);
    }
    if (pos == tam) {
        inserirFim(lista, x);
    } else if (pos == 0) {
        inserirInicio(lista, x);
    } else {
        Celula *i = lista->primeiro;
        for (int j = 0; j < pos; j++, i = i->prox);
        Celula *temp = iniciarCelula();
        temp->elemento = x;
        temp->ant = i;
        temp->prox = i->prox;
        i->prox->ant = temp;
        i->prox = temp;
    }
}

int remover(Lista *lista, int pos) {
    if (lista->primeiro == lista->ultimo) {
        printf("LISTA VAZIA\n");
        exit(1);
    }
    int tam = tamanho(lista);
    if (pos < 0 || pos >= tam) {
        printf("POSICAO INVALIDA\n");
        exit(1);
    }
    if (pos == 0) {
        return removerInicio(lista);
    } else if (pos == tam - 1) {
        return removerFim(lista);
    } else {
        Celula *i = lista->primeiro;
        for (int j = 0; j < pos; j++, i = i->prox);
        Celula *temp = i->prox;
        int elemento = temp->elemento;
        i->prox = temp->prox;
        temp->prox->ant = i;
        free(temp);
        return elemento;
    }
}

int tamanho(Lista *lista) {
    int cont = 0;
    for (Celula *i = lista->primeiro->prox; i != NULL; i = i->prox) {
        cont++;
    }
    return cont;
}

int main() {
    Lista *lista = iniciarLista();
    inserirInicio(lista, 10);
    inserirInicio(lista, 50);
    inserirInicio(lista, 30);
    inserirInicio(lista, 1);
    inserirInicio(lista, 90);
    inserirInicio(lista, 70);
    inserirInicio(lista, 6);
    mostrar(lista);
    inserirFim(lista, 20);
    mostrar(lista);
    removerInicio(lista);
    removerFim(lista);
    mostrar(lista);
    inserir(lista, 2, 17);
    mostrar(lista);
    remover(lista, 3);
    mostrar(lista);
}
