#include <ctype.h>
#include <errno.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_POKEMON 801
#define MAX_NOME 100

typedef struct pokemon {
    int id;
    char nome[MAX_NOME];
} Pokemon;

void ordenar_pokemon(char nomes[][MAX_NOME], int n);
int pesquisa_binaria(char nomes[][MAX_NOME], int n, const char *nome_procurado);

int main(void) {
    FILE *csv = stdin;  // Leitura dos dados do stdin
    char input[MAX_NOME];
    int ids[MAX_POKEMON];
    int num_ids = 0;
    char nomes[MAX_POKEMON][MAX_NOME];
    int num_nomes = 0;
    bool lendo_ids = true;

    // Ler IDs e nomes dos Pokémon
    while (fgets(input, sizeof(input), csv) != NULL) {
        // Remover nova linha
        input[strcspn(input, "\n")] = '\0';

        if (strcmp(input, "FIM") == 0) {
            if (lendo_ids) {
                lendo_ids = false;  // Começar a ler nomes
            } else {
                break;  // Terminar a leitura
            }
        } else {
            if (lendo_ids) {
                // Adicionar ID
                ids[num_ids++] = atoi(input);
            } else {
                // Adicionar nome
                strcpy(nomes[num_nomes++], input);
            }
        }
    }

    // Ordenar os nomes dos Pokémon em ordem alfabética usando ordenação por seleção
    ordenar_pokemon(nomes, num_nomes);

    // Realizar a pesquisa binária para cada nome e imprimir resultado
    for (int i = 0; i < num_nomes; ++i) {
        int resultado = pesquisa_binaria(nomes, num_nomes, nomes[i]);
        if (resultado != -1) {
            printf("SIM\n");
        } else {
            printf("NÃO\n");
        }
    }

    return EXIT_SUCCESS;
}

// Função para ordenar os nomes dos Pokémon em ordem alfabética usando ordenação por seleção
void ordenar_pokemon(char nomes[][MAX_NOME], int n) {
    for (int i = 0; i < n - 1; ++i) {
        int min_idx = i;
        for (int j = i + 1; j < n; ++j) {
            if (strcmp(nomes[j], nomes[min_idx]) < 0) {
                min_idx = j;
            }
        }
        // Trocar o elemento mínimo com o primeiro elemento não ordenado
        if (min_idx != i) {
            char temp[MAX_NOME];
            strcpy(temp, nomes[i]);
            strcpy(nomes[i], nomes[min_idx]);
            strcpy(nomes[min_idx], temp);
        }
    }
}

// Função para realizar a pesquisa binária nos nomes dos Pokémon
int pesquisa_binaria(char nomes[][MAX_NOME], int n, const char *nome_procurado) {
    int inicio = 0;
    int fim = n - 1;

    while (inicio <= fim) {
        int meio = (inicio + fim) / 2;
        int cmp = strcmp(nomes[meio], nome_procurado);

        if (cmp == 0) {
            return meio;  // Nome encontrado
        } else if (cmp < 0) {
            inicio = meio + 1;  // Buscar na metade direita
        } else {
            fim = meio - 1;  // Buscar na metade esquerda
        }
    }

    return -1;  // Nome não encontrado
}
