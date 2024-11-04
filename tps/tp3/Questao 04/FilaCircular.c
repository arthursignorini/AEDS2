#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>
#include <time.h>
#include <math.h>

#define MAX_STRING_LENGTH 1000
#define MAX_ABILITIES 10
#define MAX_LINE 2000
#define MAX 5


// Structure to store date
typedef struct {
    int day;
    int month;
    int year;
} Date;

// Structure to store Pokemon data
typedef struct {
    int id;
    int generation;
    char name[MAX_STRING_LENGTH];
    char description[MAX_STRING_LENGTH];
    char type1[MAX_STRING_LENGTH];
    char type2[MAX_STRING_LENGTH];
    char abilities[MAX_ABILITIES][MAX_STRING_LENGTH];
    int abilitiesCount;
    double weight;
    double height;
    int captureRate;
    bool isLegendary;
    Date captureDate;
} Pokemon;

typedef struct {
    Pokemon items[MAX];
    int front;
    int rear;
} FilaCircular;


// Function prototypes
void readPokemon(Pokemon* pokemon, char* id);
char** split(char* str, char delimiter, int* count);
void parseAbilities(char* abilitiesStr, Pokemon* pokemon);
void trim(char* str);
void removeQuotes(char* str);
char* capitalizeFirstLetter(char* str);
void printPokemon(Pokemon pokemon, int index);
char* parseCsvField(char** cursor);
void inicializaFila(FilaCircular* fila);
bool filaCheia(FilaCircular* fila);
bool filaVazia(FilaCircular* fila);
void enqueue(FilaCircular* fila, Pokemon valor);
Pokemon dequeue(FilaCircular* fila);
void printFila(FilaCircular* fila);


void inicializaFila(FilaCircular* fila) {
    fila->front = -1;
    fila->rear = -1;
}

bool filaCheia(FilaCircular* fila) {
    return (fila->front == (fila->rear + 1) % MAX);
}

bool filaVazia(FilaCircular* fila) {
    return (fila->front == -1);
}


void enqueue(FilaCircular* fila, Pokemon valor) {
    if (filaCheia(fila)) {
        dequeue(fila);
    }
    if (filaVazia(fila)) {
        fila->front = 0;
        fila->rear = 0;  
    }
    fila->rear = (fila->rear + 1) % MAX; 
    fila->items[fila->rear] = valor;

    int soma = 0;
    int cont = 0;
    int i = fila->front;

    while (true) {
        soma += fila->items[i].captureRate;
        cont++; 
        if (i == fila->rear) break;
        i = (i + 1) % MAX; 
    }

    float media = (float)soma / cont;
    int mediaArredondada = (int)ceil(media);
    printf("Média: %d\n", mediaArredondada); 
}


Pokemon dequeue(FilaCircular* fila) {
    if (filaVazia(fila)) {
       return (Pokemon){0}; 
    }
    Pokemon valor = fila->items[fila->front];
    if (fila->front == fila->rear) {
        
        fila->front = -1;
        fila->rear = -1;
    } else {
        fila->front = (fila->front + 1) % MAX; 
    }
    return valor;
}

// Read Pokemon data from CSV file
void readPokemon(Pokemon* pokemon, char* id) {
    FILE* file = fopen("/tmp/pokemon.csv", "r");
    if (file == NULL) {
        printf("ERROR: File Not Found.\n");
        return;
    }

    char line[MAX_LINE];
    fgets(line, sizeof(line), file); // Skip header

    bool found = false;
    while (fgets(line, sizeof(line), file)) {
        char* cursor = line;
        char* currentId = parseCsvField(&cursor);
        
        if (strcmp(currentId, id) == 0) {
            found = true;
            pokemon->id = atoi(currentId);
            pokemon->generation = atoi(parseCsvField(&cursor));
            
            char* name = parseCsvField(&cursor);
            strcpy(pokemon->name, name);
            
            char* desc = parseCsvField(&cursor);
            strcpy(pokemon->description, desc);
            
            char* type1 = parseCsvField(&cursor);
            strcpy(pokemon->type1, type1);
            
            char* type2 = parseCsvField(&cursor);
            strcpy(pokemon->type2, type2);
            
            char* abilities = parseCsvField(&cursor);
            parseAbilities(abilities, pokemon);
            
            pokemon->weight = atof(parseCsvField(&cursor));
            pokemon->height = atof(parseCsvField(&cursor));
            pokemon->captureRate = atoi(parseCsvField(&cursor));
            pokemon->isLegendary = atoi(parseCsvField(&cursor)) > 0;
            
            char* dateStr = parseCsvField(&cursor);
            if (dateStr[0] != '\0') {
                sscanf(dateStr, "%d/%d/%d", 
                    &pokemon->captureDate.day,
                    &pokemon->captureDate.month,
                    &pokemon->captureDate.year);
            }
            break;
        }
        
        // Skip to next line
        while (*cursor != '\0' && *cursor != '\n') cursor++;
    }

    if (!found) {
        printf("Pokémon não encontrado.\n");
    }

    fclose(file);
}

// Parse CSV field handling quotes
char* parseCsvField(char** cursor) {
    static char field[MAX_STRING_LENGTH];
    int i = 0;
    bool inQuotes = false;
    
    
    while (**cursor == ' ' || **cursor == '\t') (*cursor)++;
    
    
    if (**cursor == '"') {
        inQuotes = true;
        (*cursor)++;
    }
    
    // Copy field content
    while (**cursor != '\0' && **cursor != '\n' &&
           (inQuotes || **cursor != ',')) {
        if (**cursor == '"' && *(*cursor + 1) == '"') {
            
            field[i++] = '"';
            (*cursor) += 2;
        } else if (**cursor == '"') {
            inQuotes = !inQuotes;
            (*cursor)++;
        } else {
            field[i++] = **cursor;
            (*cursor)++;
        }
    }
    
    
    if (**cursor == ',') (*cursor)++;
    
    field[i] = '\0';
    trim(field);
    return field;
}

// Parse abilities string into array
void parseAbilities(char* abilitiesStr, Pokemon* pokemon) {
    removeQuotes(abilitiesStr);
    if (abilitiesStr[0] == '[') abilitiesStr++;
    if (abilitiesStr[strlen(abilitiesStr)-1] == ']') 
        abilitiesStr[strlen(abilitiesStr)-1] = '\0';
    
    pokemon->abilitiesCount = 0;
    char* ability = strtok(abilitiesStr, ",");
    while (ability != NULL && pokemon->abilitiesCount < MAX_ABILITIES) {
        trim(ability);
        strcpy(pokemon->abilities[pokemon->abilitiesCount++], ability);
        ability = strtok(NULL, ",");
    }
}

// Print Pokemon information
void printPokemon(Pokemon pokemon, int index) {
    
    printf("[%d] [#%d -> %s: %s - ['%s%s%s'] - [", 
        index, pokemon.id, pokemon.name, pokemon.description,
        pokemon.type1,
        (strlen(pokemon.type2) > 0 ? "', '" : ""),
        (strlen(pokemon.type2) > 0 ? pokemon.type2 : ""));

    // Print abilities
    for (int i = 0; i < pokemon.abilitiesCount; i++) {
        printf("%s%s", pokemon.abilities[i],
               (i < pokemon.abilitiesCount - 1 ? ", " : ""));
    }

    printf("] - %.1fkg - %.1fm - %d%% - %s - %d gen] - ",
        pokemon.weight, pokemon.height, pokemon.captureRate,
        pokemon.isLegendary ? "true" : "false", pokemon.generation);

    if (pokemon.captureDate.year != 0) {
        printf("%02d/%02d/%04d\n",
            pokemon.captureDate.day,
            pokemon.captureDate.month,
            pokemon.captureDate.year);
    } else {
        printf("N/A\n");
    }
}

// Utility functions
void trim(char* str) {
    char* start = str;
    char* end = str + strlen(str) - 1;
    
    while(isspace((unsigned char)*start)) start++;
    while(end > start && isspace((unsigned char)*end)) end--;
    
    size_t len = (end - start) + 1;
    memmove(str, start, len);
    str[len] = '\0';
}

void removeQuotes(char* str) {
    if (str[0] == '"') {
        memmove(str, str + 1, strlen(str));
    }
    int len = strlen(str);
    if (len > 0 && str[len-1] == '"') {
        str[len-1] = '\0';
    }
}

char* capitalizeFirstLetter(char* str) {
    static char result[MAX_STRING_LENGTH];
    strcpy(result, str);
    if (strlen(result) > 0) {
        result[0] = toupper(result[0]);
    }
    return result;
}

void printFila(FilaCircular* fila) {
    if (filaVazia(fila)) {
        return;
    }

    int i = fila->front;
    while (true) {
        printPokemon(fila->items[i], i); 

        if (i == fila->rear) {
            break; 
        }

        i = (i + 1) % MAX; 
    }
}


int main() {
    FilaCircular fila;
    inicializaFila(&fila);
    char input[MAX_STRING_LENGTH];
    
    // Read Pokemon IDs
    while (1) {
        fgets(input, sizeof(input), stdin);
        input[strcspn(input, "\n")] = 0;  
        
        if (strcmp(input, "FIM") == 0) break;
        
        Pokemon pokemon = {0};
        readPokemon(&pokemon, input);
        enqueue(&fila, pokemon);
    }
    
    // Read number of commands
    fgets(input, sizeof(input), stdin);
    int n = atoi(input);
    
    // Process commands
    for (int i = 0; i < n; i++) {
        fgets(input, sizeof(input), stdin);
        input[strcspn(input, "\n")] = 0; 
        
        char command[2];
        strncpy(command, input, 1);
        command[1] = '\0';
        
        if (strcmp(command, "I") == 0) {
            Pokemon pokemon = {0};
            readPokemon(&pokemon, input + 2);
            enqueue(&fila, pokemon);
        }
    
        else if (strcmp(command, "R") == 0) {
            Pokemon removed = dequeue(&fila);
            printf("(R) %s\n",removed.name);
        }
    }
    
    // Print final fila
    printFila(&fila);
    
    return 0;
}