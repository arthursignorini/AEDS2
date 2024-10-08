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

Pokemon *pokemon_de_string(char *str)
{
    Pokemon *res = novo_pokemon();
    ler_pokemon(res, str);
    return res;
}

Pokemon *pokemon_de_parametros(int id, int geracao, const char *nome, const char *descricao, const tipo_pokemon tipo[2],
                               const habilidades_pokemon *habilidades, double peso_kg, double altura_m, int taxa_captura, bool eh_lendario,
                               Data data_captura)
{
    Pokemon *res = novo_pokemon();
    habilidades_pokemon habilidades_clone = {.num = habilidades->num};
    habilidades_clone.lista = malloc(habilidades_clone.num * sizeof(char *));
    for (int i = 0; i < habilidades_clone.num; ++i)
        habilidades_clone.lista[i] = strdup(habilidades->lista[i]);
    *res = (Pokemon){.id = id, .geracao = geracao, .nome = strdup(nome), .descricao = strdup(descricao), .tipo[0] = tipo[0], .tipo[1] = tipo[1], .habilidades = habilidades_clone, .peso = peso_kg, .altura = altura_m, .taxa_captura = taxa_captura, .eh_lendario = eh_lendario, .data_captura = data_captura};
    return res;
}

Pokemon *clonar_pokemon(const Pokemon *p)
{
    return pokemon_de_parametros(p->id, p->geracao, p->nome, p->descricao, p->tipo, &p->habilidades, p->peso, p->altura,
                                 p->taxa_captura, p->eh_lendario, p->data_captura);
}

static inline Pokemon *novo_pokemon(void)
{
    Pokemon *res = malloc(sizeof(Pokemon));
    if (!res)
    {
        int errsv = errno;
        perror("Falha ao alocar memória para Pokémon");
        exit(errsv);
    }
    return res;
}

void liberar_pokemon(Pokemon *restrict p)
{
    if (p != NULL)
    {
        free(p->nome);
        free(p->descricao);
        for (int i = 0; i < p->habilidades.num; ++i)
            free(p->habilidades.lista[i]);
        free(p->habilidades.lista);
        free(p);
    }
}

void ler_pokemon(Pokemon *restrict p, char *str)
{
    char *const post_list = strstr(str, "']\",") + 3;
    char *token = NULL;
    char *save = NULL;
    int contador_token = 0;
    if (!post_list)
    {
        int errsv = errno;
        perror("erro");
        exit(errsv);
    }
    p->id = atoi(strtok_r(str, ",", &save));
    p->geracao = atoi(strtok_r(NULL, ",", &save));
    token = strtok_r(NULL, ",", &save);
    p->nome = strdup(token);
    token = strtok_r(NULL, ",", &save);
    p->descricao = strdup(token);
    p->tipo[0] = tipo_de_string(strtok_r(NULL, ",", &save));
    token = strtok_r(NULL, "[,", &save);
    p->tipo[1] = (*token == '"') ? SEM_TIPO : tipo_de_string(token);
    p->habilidades = habilidades_de_string(strtok_r(NULL, "]", &save));
    str = post_list;
    save = NULL;
    for (int i = 0; str[i]; ++i)
        if (str[i] == ',' && str[i + 1] != ',')
            ++contador_token;
    if (contador_token == 5)
    {
        p->peso = atof(strtok_r(str, ",", &save));
        p->altura = atof(strtok_r(NULL, ",", &save));
    }
    else
    {
        p->altura = p->peso = 0;
    }
    p->taxa_captura = atoi(strtok_r(save ? NULL : str, ",", &save));
    p->eh_lendario = atoi(strtok_r(NULL, ",", &save));
    p->data_captura.dia = atoi(strtok_r(NULL, "/", &save));
    p->data_captura.mes = atoi(strtok_r(NULL, "/", &save));
    p->data_captura.ano = atoi(strtok_r(NULL, "/\n\r", &save));
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

static habilidades_pokemon habilidades_de_string(char *str)
{
    habilidades_pokemon res = {.num = 1};
    char *save = NULL;
    for (int i = 0; str[i] && str[i] != ']'; ++i)
        if (str[i] == ',')
            ++res.num;
    res.lista = malloc(res.num * sizeof(char *));
    if (!res.lista)
    {
        int errsv = errno;
        perror("erro");
        exit(errsv);
    }
    for (int i = 0; i < res.num; ++i)
    {
        char *habilidade;
        int tamanho_token = 0;
        habilidade = strtok_r(i ? NULL : str, ",]", &save);
        if (!habilidade)
        {
            int errsv = errno;
            for (int j = 0; j < i; ++j)
            {
                free(res.lista[j]);
            }
            free(res.lista);
            perror("erro");
            exit(errsv);
        }
        tamanho_token = strlen(habilidade);
        res.lista[i] = malloc((tamanho_token + 1) * sizeof(char));
        if (!res.lista[i])
        {
            int errsv = errno;
            for (int j = 0; j < i; ++j)
            {
                free(res.lista[j]);
            }
            free(res.lista);
            perror("erro");
            exit(errsv);
        }
        strcpy(res.lista[i], habilidade);
    }
    return res;
}

static tipo_pokemon tipo_de_string(const char *str)
{
    if (!strcmp(str, "bug"))
        return INSETO;
    else if (!strcmp(str, "dark"))
        return NOTURNO;
    else if (!strcmp(str, "dragon"))
        return DRAGAO;
    else if (!strcmp(str, "electric"))
        return ELETRICO;
    else if (!strcmp(str, "fairy"))
        return FADA;
    else if (!strcmp(str, "fighting"))
        return LUTADOR;
    else if (!strcmp(str, "fire"))
        return FOGO;
    else if (!strcmp(str, "flying"))
        return VOADOR;
    else if (!strcmp(str, "ghost"))
        return FANTASMA;
    else if (!strcmp(str, "grass"))
        return GRAMA;
    else if (!strcmp(str, "ground"))
        return TERRA;
    else if (!strcmp(str, "ice"))
        return GELO;
    else if (!strcmp(str, "normal"))
        return NORMAL;
    else if (!strcmp(str, "poison"))
        return VENENOSO;
    else if (!strcmp(str, "psychic"))
        return PSIQUICO;
    else if (!strcmp(str, "rock"))
        return PEDRA;
    else if (!strcmp(str, "steel"))
        return METAL;
    else if (!strcmp(str, "water"))
        return AGUA;
    return SEM_TIPO;
}

static const char *tipo_para_string(tipo_pokemon tipo)
{
    switch (tipo)
    {
    case INSETO:
        return "bug";
    case NOTURNO:
        return "dark";
    case DRAGAO:
        return "dragon";
    case ELETRICO:
        return "electric";
    case FADA:
        return "fairy";
    case LUTADOR:
        return "fighting";
    case FOGO:
        return "fire";
    case VOADOR:
        return "flying";
    case FANTASMA:
        return "ghost";
    case GRAMA:
        return "grass";
    case TERRA:
        return "ground";
    case GELO:
        return "ice";
    case NORMAL:
        return "normal";
    case VENENOSO:
        return "poison";
    case PSIQUICO:
        return "psychic";
    case PEDRA:
        return "rock";
    case METAL:
        return "steel";
    case AGUA:
        return "water";
    default:
        return "unknown";
    }
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

    while (fgetc(csv) != '\n')
        ;

    while (n < MAX_POKEMON && getline(&input, &tamanho, csv) != -1)
        pokemon[n++] = pokemon_de_string(input);
    fclose(csv);

    while (getline(&input, &tamanho, stdin) != -1)
    {
        if (strcmp(input, "FIM\n"))
        {
            int idx = atoi(input) - 1;
            if (idx > -1 && idx < n)
                imprimir_pokemon(pokemon[idx]);
            else
                puts("erro");
        }
    }

    for (int i = 0; i < n; ++i)
        liberar_pokemon(pokemon[i]);
    free(input);

    return EXIT_SUCCESS;
}
