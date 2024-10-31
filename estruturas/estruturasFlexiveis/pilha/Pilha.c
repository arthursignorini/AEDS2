#include<stdio.h>
#include<stdlib.h>

typedef struct Celula {
    int elemento;
    struct Celula *prox;
} Celula;

typedef struct Pilha {
    struct Celula *topo;
} Pilha;

Pilha*iniciarPilha() {
    Pilha *pilha = malloc(sizeof(Pilha));
    pilha -> topo = NULL;
    return pilha;
}

void inserirPilha(Pilha *pilha, int x) {
    Celula *temp = (Celula*)malloc(sizeof(Celula));
    temp -> elemento = x;
    temp -> prox = pilha -> topo;
    pilha -> topo = temp;
    temp = NULL;
}

int removerPilha(Pilha *pilha) {
    if(pilha -> topo == NULL) {
        printf("PILHA VAZIA");
        exit(1);
    } 
    Celula *temp = pilha -> topo;
    int elemento = temp -> elemento;
    pilha -> topo = temp -> prox;
    temp -> prox = NULL;
    free(temp);
    return elemento;
}

void mostrar(Pilha *pilha) {
    for(Celula *temp = pilha -> topo; temp!=NULL; temp = temp -> prox) {
        printf("%d ",temp->elemento);
    }
}

int somar(Pilha *pilha) {
    int soma = 0;
    for(Celula*i=pilha->topo; i!=NULL; i=i->prox) {
        soma += i -> elemento;
    }
    return soma;
}


int main () {
    Pilha *pilha = iniciarPilha();

    inserirPilha(pilha, 10);
    inserirPilha(pilha, 2);
    inserirPilha(pilha, 5);
    inserirPilha(pilha, 1);
    mostrar(pilha);
    printf("\n");
    removerPilha(pilha);
    mostrar(pilha);
    int result = somar(pilha);
    printf("\nA soma eh %d",result);
}
