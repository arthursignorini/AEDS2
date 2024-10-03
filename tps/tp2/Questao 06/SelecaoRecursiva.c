#include <ctype.h>
#include <errno.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define DB_PADRAO "/tmp/pokemon.csv"

enum tipos_pokemon
{
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

typedef struct habilidades
{
    char **lista;
    int num;
} habilidades_pokemon;

typedef struct data
{
    int ano;
    int mes;
    int dia;
} Data;

typedef struct informacoes
{
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

int main(int argc, char **argv);
void ler_pokemon(Pokemon *restrict p, char *str);
void imprimir_pokemon(Pokemon *restrict const p);
Pokemon *pokemon_de_string(char *str);
Pokemon *pokemon_de_parametros(int id, int geracao, const char *nome, const char *descricao, const tipo_pokemon tipo[2],
                               const habilidades_pokemon *habilidades, double peso_kg, double altura_m, int taxa_captura, bool eh_lendario,
                               Data data_captura);
Pokemon *clonar_pokemon(const Pokemon *p);
static inline Pokemon *novo_pokemon(void);
void liberar_pokemon(Pokemon *restrict p);
static habilidades_pokemon habilidades_de_string(char *str);
static tipo_pokemon tipo_de_string(const char *str);
static const char *tipo_para_string(tipo_pokemon tipo);

// Função de ordenação recursiva por seleção
void selecao_recursiva(Pokemon *pokemons[], int n, int index)
{
    // Caso base: se o índice for igual ao tamanho do array, terminamos a ordenação
    if (index == n)
        return;

    // Encontra o índice do menor elemento a partir do índice atual
    int min_index = index;
    for (int i = index + 1; i < n; i++)
    {
        if (strcmp(pokemons[i]->nome, pokemons[min_index]->nome) < 0)
        {
            min_index = i;
        }
    }

    // Troca o elemento atual com o menor elemento encontrado
    if (min_index != index)
    {
        Pokemon *temp = pokemons[index];
        pokemons[index] = pokemons[min_index];
        pokemons[min_index] = temp;
    }

    // Chama recursivamente para o próximo índice
    selecao_recursiva(pokemons, n, index + 1);
}

void imprimir_pokemon(Pokemon *restrict const p)
{
    printf("[#%d -> %s: %s - ['%s'", p->id, p->nome, p->descricao,
           tipo_para_string(p->tipo[0]));
    if (p->tipo[1] != SEM_TIPO)
        printf(", '%s'", tipo_para_string(p->tipo[1]));
    printf("] - ['");
    for (int i = 0; i < p->habilidades.num; ++i)
    {
        if (i > 0)
        {
            printf(", ");
        }
        printf("%s", p->habilidades.lista[i]);
    }
    printf("] - %0.1lfkg - %0.1lfm - %u%% - %s - %u gen] - %02u/%02u/%04u\n",
           p->peso, p->altura, p->taxa_captura,
           p->eh_lendario ? "true" : "false", p->geracao,
           p->data_captura.dia, p->data_captura.mes, p->data_captura.ano);
}

#define MAX_POKEMON 801

int main(int argc, char **argv)
{
    FILE *csv = fopen((argc > 1) ? argv[1] : DB_PADRAO, "r");
    Pokemon *pokemon[MAX_POKEMON] = {NULL};
    int n = 0;
    char *input = NULL;
    size_t tamanho;

    if (!csv)
    {
        int errsv = errno;
        perror("erro ao abrir");
        return errsv;
    }

    // Pula a primeira linha (cabeçalho)
    while (fgetc(csv) != '\n')
        ;

    // Lê os Pokémon do arquivo CSV
    while (n < MAX_POKEMON && getline(&input, &tamanho, csv) != -1)
        pokemon[n++] = pokemon_de_string(input);
    fclose(csv);

    Pokemon *lista[1000];
    // Loop para pesquisa binária
    while (getline(&input, &tamanho, stdin) != -1)
    {
        if (strcmp(input, "FIM\n") == 0)
            break;
        // Remove nova linha
        input[strcspn(input, "\n")] = 0;
        int id = atoi(input);
        for(int i=0; i<sizeof(pokemon); i++) {
            if(id == pokemon[i]->id) {
                lista[i] = pokemon[i];
            }
        }
    }


    // Ordena os Pokémon pelo nome usando seleção recursiva
    selecao_recursiva(lista, n, 0);

    // Imprime os Pokémon ordenados
    for (int i = 0; i < n; i++)
    {
        imprimir_pokemon(lista[i]);
    }

    // Libera a memória alocada
    for (int i = 0; i < n; i++)
    {
        liberar_pokemon(lista[i]);
    }
    free(input);

    return EXIT_SUCCESS;
}
