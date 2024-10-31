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
    fila -> primeiro = (Celula*)malloc(Celula);
    fila -> ultimo = fila -> primeiro;
    return fila;
}

void inserirFila (Fila *fila) {
    fila -> ultimo -> prox = (Celula*)malloc(Celula);
    fila -> ultimo = fila -> ultimo -> prox;
}

int removerFila(Fila *fila) {
    if(fila -> primeiro == fila -> ultimo) {
        printf("FILA VAIZA");
        exit(1);
    }
    Celula *temp = primeiro -> prox;
    int elemento = temp -> elemento;
    fila -> primeiro -> prox = temp -> prox;
    if(ultimo == temp) {
        fila -> ultimo = fila -> primeiro;
    }
    free(temp);
    temp = NULL;
    return elemento;

}

int main() {
    Fila *fila = iniciarFila();
    inserirFila(fila);
    removerFila(fila);
}