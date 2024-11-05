#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<stdbool.h>

typedef struct Celula {
    char elemento[5];
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

void inserirFila (Fila *fila, char x[]) {
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

bool isEmpty(Fila *fila) {
    if(fila ->primeiro == fila->ultimo) {
        return true;
    } else {
        return false;
    }
}

int main() {
    Fila *oeste = iniciarFila();
    Fila *sul = iniciarFila();
    Fila *norte = iniciarFila();
    Fila *leste = iniciarFila();

    char direcao[5];
    char input[5];
    while(strcmp(scanf("%s",&input), "0")!=0) {
        if(input[0] != 'A') {
            strcpy(direcao, input); 
        }

        if(input[0] == 'A') {
            if(strcmp(direcao, "-1")==0) {
                inserirFila(oeste, input);
            }

            if(strcmp(direcao, "-2")==0) {
                inserirFila(sul, input);
            }

            if(strcmp(direcao, "-3")==0) {
                inserirFila(norte, input);
            }

            if(strcmp(direcao, "-4")==0) {
                inserirFila(leste, input);
            }
        }
    }

    while (!(isEmpty(oeste) || isEmpty(sul) || isEmpty(norte) || isEmpty(leste))) {
            if (!isEmpty(oeste)) {
                printf("%s ",oeste->primeiro->prox->elemento);
                removerFila(oeste);
            }
            if (!isEmpty(norte)) {
                printf("%s ",norte->primeiro->prox->elemento);
                removerFila(norte);
            }
            if (!isEmpty(sul)) {
                printf("%s ",sul->primeiro->prox->elemento);
                removerFila(sul);
            }
            if (!isEmpty(leste)) {
                printf("%s ",leste->primeiro->prox->elemento);
                removerFila(leste);
            }
        }
}