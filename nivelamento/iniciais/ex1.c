#include <stdio.h>
#include <stdbool.h>

// Função de busca binária
bool buscarBinaria(int vet[], int esquerda, int direita, int x) {
    while (esquerda <= direita) {
        int meio = esquerda + (direita - esquerda) / 2;

        if (vet[meio] == x) {
            return true;
        }

        if (vet[meio] < x) {
            esquerda = meio + 1;
        } else {
            direita = meio - 1;
        }
    }
    return false;
}

// Função para conferir se o número está no vetor
bool conferir(int vet[], int tamanho) {
    int x;
    printf("Digite um numero: ");
    scanf("%d", &x);
    
    return buscarBinaria(vet, 0, tamanho - 1, x);
}

int main() {
    int vet[5];
    for (int i = 0; i < 5; i++) {
        printf("Digite um valor: ");
        scanf("%d", &vet[i]);
    }

    // Ordenar o vetor em ordem crescente
    int temp;
    for (int i = 0; i < 5 - 1; i++) {
        for (int j = 0; j < 5 - i - 1; j++) {
            if (vet[j] > vet[j + 1]) {
                temp = vet[j + 1];
                vet[j + 1] = vet[j];
                vet[j] = temp;
            }
        }
    }

    // Conferir se o número está no vetor
    bool resposta = conferir(vet, 5);
    if (resposta) {
        printf("Existe um numero igual no vetor\n");
    } else {
        printf("Nao existe um numero igual no vetor\n");
    }

    return 0;
}
