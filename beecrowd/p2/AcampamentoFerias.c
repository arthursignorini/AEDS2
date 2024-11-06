#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char nome[31];
    int numero;
} Aluno;

typedef struct Celula {
    Aluno aluno;
    struct Celula *prox;
    struct Celula *ant;
} Celula;

typedef struct {
    Celula *primeiro;
    int tamanho;
} Fila;

// Função para iniciar a lista circular
Fila* iniciarFila() {
    Fila* fila = (Fila*)malloc(sizeof(Fila));
    fila->primeiro = NULL;
    fila->tamanho = 0;
    return fila;
}

// Função para adicionar uma criança ao final da lista
void enfileirar(Fila* fila, Aluno aluno) {
    Celula* novaCelula = (Celula*)malloc(sizeof(Celula));
    novaCelula->aluno = aluno;
    if (fila->primeiro == NULL) {
        novaCelula->prox = novaCelula;
        novaCelula->ant = novaCelula;
        fila->primeiro = novaCelula;
    } else {
        Celula* ultimo = fila->primeiro->ant;
        ultimo->prox = novaCelula;
        novaCelula->ant = ultimo;
        novaCelula->prox = fila->primeiro;
        fila->primeiro->ant = novaCelula;
    }
    fila->tamanho++;
}

// Função para eliminar uma criança do círculo e retornar o valor da ficha
int eliminar(Celula** atual, Fila* fila) {
    int numeroFicha = (*atual)->aluno.numero;
    Celula* removida = *atual;

    if (fila->tamanho == 1) {
        fila->primeiro = NULL;
    } else {
        removida->ant->prox = removida->prox;
        removida->prox->ant = removida->ant;
        if (removida == fila->primeiro) {
            fila->primeiro = removida->prox;
        }
    }
    
    *atual = removida->prox;  
    free(removida);
    fila->tamanho--;

    return numeroFicha;  
}

// Função para encontrar o vencedor
Aluno encontrarVencedor(Fila* fila) {
    Celula* atual = fila->primeiro->prox;  
    int passos = atual->aluno.numero; 

    while (fila->tamanho > 1) {
        int sentidoHorario = (passos % 2 == 0);
        for (int i = 0; i < passos - 1; i++) {
            atual = sentidoHorario ? atual->prox : atual->ant;  
        }
        passos = eliminar(&atual, fila); 
    }
    return fila->primeiro->aluno;  
}


// Libera memória da fila
void liberarFila(Fila* fila) {
    while (fila->tamanho > 0) {
        Celula* atual = fila->primeiro;
        fila->primeiro = atual->prox;
        free(atual);
        fila->tamanho--;
    }
    free(fila);
}

int main() {
    int n;
    while (scanf("%d", &n) && n != 0) {
        Fila* fila = iniciarFila();
        
        for (int i = 0; i < n; i++) {
            Aluno aluno;
            scanf("%s %d", aluno.nome, &aluno.numero);
            enfileirar(fila, aluno);
        }
        
        // Encontra o vencedor e imprime seu nome
        Aluno vencedor = encontrarVencedor(fila);
        printf("Vencedor: %s\n", vencedor.nome);
        
        liberarFila(fila);
    }
    return 0;
}
