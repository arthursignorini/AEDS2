#include <ctype.h>
#include <errno.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define DB_PADRAO "/tmp/pokemon.csv"

enum tipos_pokemon {
    SEM_TIPO = 0,
    INSETO,
    NOTURNO,
    DRAGAO,
    ELETRICO,
    FADA,
    LUTADOR,
    FOGO,
    VOADOR,
    FANTASMA,
    GRAMA,
    TERRA,
    GELO,
    NORMAL,
    VENENOSO,
    PSIQUICO,
    PEDRA,
    METAL,
    AGUA
};

typedef int tipo_pokemon;

typedef struct habilidades {
    char **lista;
    int num;
} habilidades_pokemon;

typedef struct data {
    int ano;
    int mes;
    int dia;
} Data;

typedef struct informacoes {
    double peso;
    double altura;
    char *nome;
    char *descricao;
    Data data_captura;
    tipo_pokemon tipo[2];
    int id;
    int taxa_captura;
    int geracao;
    bool eh_lendario;
    habilidades_pokemon habilidades;
} Pokemon;

void selection_sort(Pokemon *pokemons[], int n, int *comparacoes, int *movimentacoes);
void gerar_arquivo_log_selecao(long tempo_execucao, int comparacoes, int movimentacoes);

Pokemon *pokemon_de_string(char *str);
Pokemon *clonar_pokemon(const Pokemon *p);
static inline Pokemon *novo_pokemon(void);
void liberar_pokemon(Pokemon *restrict p);

#define MAX_POKEMON 801

int main(int argc, char **argv) {
    FILE *csv = fopen((argc > 1) ? argv[1] : DB_PADRAO, "r");
    Pokemon *pokemon[MAX_POKEMON] = {NULL};
    int n = 0;
    char *input = NULL;
    size_t tamanho;
    int comparacoes = 0; // Contador de comparações
    int movimentacoes = 0; // Contador de movimentações
    clock_t inicio = clock(); // Início da contagem do tempo

    if (!csv) {
        int errsv = errno;
        perror("erro ao abrir");
        return errsv;
    }

    while (fgetc(csv) != '\n') // Ignorar cabeçalho
        ;

    while (n < MAX_POKEMON && getline(&input, &tamanho, csv) != -1)
        pokemon[n++] = pokemon_de_string(input);
    fclose(csv);

    Pokemon *lista[1000];
    int contador = 0;

    // Ler IDs para seleção dos pokemons
    while (getline(&input, &tamanho, stdin) != -1) {
        if (strcmp(input, "FIM\n") == 0)
            break;
        // Remove nova linha
        input[strcspn(input, "\n")] = 0;
        int id = atoi(input);

        for (int i = 0; i < n; i++) {
            if (pokemon[i] == NULL) {
                break;
            }
            if (pokemon[i]->id == id) {
                lista[contador] = clonar_pokemon(pokemon[i]);
                contador++;
            }
        }
    }

    // Ordenar a lista usando Selection Sort
    selection_sort(lista, contador, &comparacoes, &movimentacoes);

    clock_t fim = clock(); // Fim da contagem do tempo
    long tempo_execucao = ((long)(fim - inicio)) * 1000 / CLOCKS_PER_SEC; // Tempo em milissegundos

    gerar_arquivo_log_selecao(tempo_execucao, comparacoes, movimentacoes); // Gera o arquivo de log

    // Libera memória
    for (int i = 0; i < n; i++) {
        liberar_pokemon(pokemon[i]);
    }

    free(input);
    return EXIT_SUCCESS;
}

// Implementação do Selection Sort
void selection_sort(Pokemon *pokemons[], int n, int *comparacoes, int *movimentacoes) {
    for (int i = 0; i < n - 1; i++) {
        int min_idx = i;
        for (int j = i + 1; j < n; j++) {
            (*comparacoes)++; // Incrementa o número de comparações
            if (strcmp(pokemons[j]->nome, pokemons[min_idx]->nome) < 0) {
                min_idx = j;
            }
        }
        if (min_idx != i) {
            Pokemon *temp = pokemons[i];
            pokemons[i] = pokemons[min_idx];
            pokemons[min_idx] = temp;
            (*movimentacoes)++; // Incrementa o número de movimentações
        }
    }
}

// Função para gerar o arquivo de log
void gerar_arquivo_log_selecao(long tempo_execucao, int comparacoes, int movimentacoes) {
    FILE *log = fopen("848122_selecao.txt", "w");
    if (!log) {
        perror("Erro ao criar arquivo de log");
        exit(EXIT_FAILURE);
    }
    fprintf(log, "848122\t%ldms\t%d comparacoes\t%d movimentacoes\n", tempo_execucao, comparacoes, movimentacoes);
    fclose(log);
}

// Função que converte uma string do CSV em um objeto Pokemon
Pokemon *pokemon_de_string(char *str) {
    Pokemon *p = novo_pokemon();
    // Implementação da função de leitura do CSV (deve existir)
    // ler_pokemon(p, str);  // Função não fornecida
    return p;
}

// Função que clona um Pokémon
Pokemon *clonar_pokemon(const Pokemon *p) {
    return pokemon_de_parametros(
        p->id, p->geracao, p->nome, p->descricao, p->tipo, &p->habilidades,
        p->peso, p->altura, p->taxa_captura, p->eh_lendario, p->data_captura
    );
}

// Função que aloca memória para um novo Pokémon
static inline Pokemon *novo_pokemon(void) {
    Pokemon *res = malloc(sizeof(Pokemon));
    if (!res) {
        int errsv = errno;
        perror("Falha ao alocar memória para Pokémon");
        exit(errsv);
    }
    return res;
}

// Função que libera a memória de um Pokémon
void liberar_pokemon(Pokemon *restrict p) {
    if (p != NULL) {
        free(p->nome);
        free(p->descricao);
        for (int i = 0; i < p->habilidades.num; ++i) {
            free(p->habilidades.lista[i]);
        }
        free(p->habilidades.lista);
        free(p);
    }
}
