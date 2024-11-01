#include<stdio.h>
#include<stdlib.h>

typedef struct Celula {
    int elemento;
    struct Celula *prox;
} Celula;

typedef struct Fila {
    struct Celula *primeiro;
    struct Celula *ultimo;
} Fila;

Fila*iniciarFila() {
    Fila *fila = malloc(sizeof(Fila));
    fila -> primeiro = (Celula*)malloc(sizeof(Celula));
    fila -> primeiro -> prox = NULL;
    fila -> ultimo = fila -> primeiro;
    return fila;
}

void inserirFila (Fila *fila, int x) {
    fila -> ultimo -> prox = (Celula*)malloc(sizeof(Celula));
    fila -> ultimo -> prox -> elemento = x;
    fila -> ultimo = fila -> ultimo -> prox;
    fila -> ultimo -> prox = NULL;
}

int removerFila(Fila *fila) {
    if(fila -> primeiro == fila -> ultimo) {
        printf("FILA VAIZA");
        exit(1);
    }
    Celula *temp = fila -> primeiro -> prox;
    int elemento = temp -> elemento;
    fila -> primeiro -> prox = temp -> prox;
    if(fila -> ultimo == temp) {
        fila -> ultimo = fila -> primeiro;
    }
    free(temp);
    temp = NULL;
    return elemento;

}

void mostrar (Fila *fila) {
    printf("\n");
    for(Celula *i= fila ->primeiro->prox; i!=NULL; i=i->prox) {
        printf("%d ",i->elemento);
    }
}

int main() {
    Fila *fila = iniciarFila();
    inserirFila(fila, 10);
    inserirFila(fila, 20);
    inserirFila(fila, 30);
    inserirFila(fila, 40);
    mostrar(fila);
    removerFila(fila);
    mostrar(fila);
}
