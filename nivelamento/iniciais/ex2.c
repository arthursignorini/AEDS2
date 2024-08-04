#include <stdio.h>

void encontrarMaiorEMenor(int array[], int tamanho) {
    if (tamanho <= 0) {
        printf("Array vazio!\n");
        return;
    }

    int maior, menor;

    // Inicialização com o primeiro elemento
    if (tamanho % 2 == 0) {
        // Se o tamanho é par, comparamos o primeiro par de elementos
        if (array[0] > array[1]) {
            maior = array[0];
            menor = array[1];
        } else {
            maior = array[1];
            menor = array[0];
        }
    } else {
        // Se o tamanho é ímpar, inicializamos com o primeiro elemento
        maior = menor = array[0];
    }

    // Começar a partir do segundo par
    int i;
    if (tamanho % 2 == 0) {
        i = 2;
    } else {
        i = 1;
    }
    
    // Verificar limites para evitar acesso inválido
    for (; i + 1 < tamanho; i += 2) {
        int a = array[i];
        int b = array[i + 1];

        int localMaior;
        int localMenor;

        if (a > b) {
            localMaior = a;
            localMenor = b;
        } else {
            localMaior = b;
            localMenor = a;
        }

        // Atualizar maior
        if (localMaior > maior) {
            maior = localMaior;
        }
        
        // Atualizar menor
        if (localMenor < menor) {
            menor = localMenor;
        }
    }

    // Verificar o último elemento em caso de tamanho ímpar
    if (tamanho % 2 != 0) {
        int ultimoElemento = array[tamanho - 1];
        if (ultimoElemento > maior) {
            maior = ultimoElemento;
        }
        if (ultimoElemento < menor) {
            menor = ultimoElemento;
        }
    }

    printf("Maior elemento: %d\n", maior);
    printf("Menor elemento: %d\n", menor);
}

int main() {
    int array[] = {3, 5, 1, 8, 2};
    int tamanho = sizeof(array) / sizeof(array[0]);

    encontrarMaiorEMenor(array, tamanho);

    return 0;
}
