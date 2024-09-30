#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

#define MAX_CHAR 1000
#define MAX_TYPES 10
#define MAX_ABILITIES 10
#define CSV_FILE "/tmp/pokemon.csv"

typedef struct {
    int id;
    int generation;
    char name[100];
    char description[200];
    char types[MAX_TYPES][50];
    int typesCount;
    char abilities[MAX_ABILITIES][50];
    int abilitiesCount;
    double weight;
    double height;
    int captureRate;
    bool isLegendary;
    char captureDate[20];
} Pokemon;

void initPokemon(Pokemon *pokemon) {
    pokemon->id = 0;
    pokemon->generation = 0;
    strcpy(pokemon->name, "");
    strcpy(pokemon->description, "");
    pokemon->typesCount = 0;
    pokemon->abilitiesCount = 0;
    pokemon->weight = 0.0;
    pokemon->height = 0.0;
    pokemon->captureRate = 0;
    pokemon->isLegendary = false;
    strcpy(pokemon->captureDate, "Data não disponível");
}

Pokemon parsePokemon(char *line) {
    Pokemon pokemon;
    initPokemon(&pokemon);
    char *token;
    int fieldIndex = 0;

    token = strtok(line, ";");
    while (token != NULL) {
        switch (fieldIndex) {
            case 0:
                pokemon.id = atoi(token);
                break;
            case 1:
                pokemon.generation = atoi(token);
                break;
            case 2:
                strcpy(pokemon.name, token);
                break;
            case 3:
                strcpy(pokemon.description, token);
                break;
            case 4:
                strcpy(pokemon.types[pokemon.typesCount++], token);
                break;
            case 5:
                if (strcmp(token, "0") != 0)
                    strcpy(pokemon.types[pokemon.typesCount++], token);
                break;
            case 6: {
                char abilitiesString[MAX_CHAR];
                strcpy(abilitiesString, token);
                abilitiesString[strcspn(abilitiesString, "\n")] = 0; 
                char *ability = strtok(abilitiesString, ",");
                while (ability != NULL) {
                    strcpy(pokemon.abilities[pokemon.abilitiesCount++], ability);
                    ability = strtok(NULL, ",");
                }
                break;
            }
            case 7:
                pokemon.weight = atof(token);
                break;
            case 8:
                pokemon.height = atof(token);
                break;
            case 9:
                pokemon.captureRate = atoi(token);
                break;
            case 10:
                pokemon.isLegendary = (strcmp(token, "1") == 0);
                break;
            case 11:
                if (strcmp(token, "0") != 0)
                    strcpy(pokemon.captureDate, token);
                break;
            default:
                break;
        }
        token = strtok(NULL, ";");
        fieldIndex++;
    }

    return pokemon;
}

void formatString(char *line) {
    bool in_list = false;
    for (int i = 0; line[i] != '\0'; i++) {
        if (!in_list && line[i] == ',') {
            line[i] = ';';
        } else if (line[i] == '"') {
            in_list = !in_list;
        }
    }
}

void readPokemons(Pokemon *pokemons, int *pokemonCount) {
    FILE *file = fopen(CSV_FILE, "r");
    if (!file) {
        printf("Erro ao abrir o arquivo CSV.\n");
        exit(1);
    }

    char line[MAX_CHAR];
    fgets(line, MAX_CHAR, file); // Pular cabeçalho

    while (fgets(line, MAX_CHAR, file)) {
        if (strncmp(line, "FIM", 3) == 0) {
            break;
        }

        formatString(line);
        pokemons[*pokemonCount] = parsePokemon(line);
        (*pokemonCount)++;
    }

    fclose(file);
}

void printPokemon(const Pokemon *pokemon) {
    printf("[#%d -> %s: %s - [", pokemon->id, pokemon->name, pokemon->description);
    for (int i = 0; i < pokemon->typesCount; i++) {
        printf("'%s'", pokemon->types[i]);
        if (pokemon->typesCount > 2) printf(", ");
    }
    printf("] - [");
    for (int i = 0; i < pokemon->abilitiesCount; i++) {
        printf("'%s'", pokemon->abilities[i]);
        if (pokemon->abilitiesCount > 2) printf(", ");
    }
    printf("] - %.1lfkg - %.1lfm - %d%% - %s - %d gen] - %s\n",
           pokemon->weight, pokemon->height, pokemon->captureRate,
           pokemon->isLegendary ? "true" : "false", pokemon->generation,
           pokemon->captureDate);
}

void searchPokemonById(Pokemon *pokemons, int pokemonCount) {
    char input[10];
    while (true) {
        scanf("%s", input);
        if (strcmp(input, "FIM") == 0) {
            break;
        }

        int id = atoi(input);
        for (int i = 0; i < pokemonCount; i++) {
            if (pokemons[i].id == id) {
                printPokemon(&pokemons[i]);
            }
        }
    }
}

int main() {
    Pokemon pokemons[1000];
    int pokemonCount = 0;

    readPokemons(pokemons, &pokemonCount);

    if (pokemonCount == 0) {
        printf("Nenhum Pokémon encontrado.\n");
    } else {
        searchPokemonById(pokemons, pokemonCount);
    }

    return 0;
}
