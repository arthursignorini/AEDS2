#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>
#include <time.h>

#define MAX_STRING_LENGTH 1000
#define MAX_ABILITIES 10
#define MAX_LINE 2000

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

// Structure for linked list node
typedef struct Cell {
    Pokemon pokemon;
    struct Cell* next;
} Cell;

// Estrutura para a pilha
typedef struct {
    Cell* top;
} Stack;


// Function prototypes
void readPokemon(Pokemon* pokemon, char* id);
void printList(Stack* list);
char** split(char* str, char delimiter, int* count);
void parseAbilities(char* abilitiesStr, Pokemon* pokemon);
void trim(char* str);
void removeQuotes(char* str);
char* capitalizeFirstLetter(char* str);
void printPokemon(Pokemon pokemon, int index);
char* parseCsvField(char** cursor);

// Inicializa a pilha
void initStack(Stack* stack) {
    stack->top = NULL;
}

// Insere um Pokémon no topo da pilha
void push(Stack* stack, Pokemon pokemon) {
    Cell* newCell = (Cell*)malloc(sizeof(Cell));
    newCell->pokemon = pokemon;
    newCell->next = stack->top;
    stack->top = newCell;
}

// Remove e retorna o Pokémon do topo da pilha
Pokemon pop(Stack* stack) {
    Pokemon empty = {0};
    if (stack->top == NULL) {
        printf("Erro: Pilha vazia\n");
        return empty;
    }
    
    Cell* temp = stack->top;
    Pokemon removed = temp->pokemon;
    stack->top = temp->next;
    free(temp);
    return removed;
}

// Verifica se a pilha está vazia
bool isEmpty(Stack* stack) {
    return stack->top == NULL;
}

// Função para imprimir a pilha (opcional)
void printStack(Stack* stack) {
    Cell* current = stack->top;
    int index = 0;
    while (current != NULL) {
        printPokemon(current->pokemon, index++);
        current = current->next;
    }
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


int countElements(Cell* current) {
    int count = 0;
    while (current != NULL) {
        count++;
        current = current->next;
    }
    return count; 
}

void printListReverse(Cell* current, int index, int total) {
    if (current == NULL) {
        return; 
    }
    printListReverse(current->next, index + 1, total);

    int printIndex = total - index - 1; 
    printPokemon(current->pokemon, printIndex);
}

// Função que inicia a impressão da lista inversa
void printList(Stack* list) {
    int totalElements = countElements(list->top); 
    printListReverse(list->top, 0, totalElements); 
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


void printRemovidosReverse(Cell* current) {
    if (current == NULL) {
        return;
    }
    printRemovidosReverse(current->next); 
    printf("(R) %s\n", current->pokemon.name);
}

void printRemovidos(Stack* stack) {
    printRemovidosReverse(stack->top); 
}


int main() {
    Stack stack;
     initStack(&stack);
    char input[MAX_STRING_LENGTH];
    
    // Read Pokemon IDs
    while (1) {
        fgets(input, sizeof(input), stdin);
        input[strcspn(input, "\n")] = 0;  
        
        if (strcmp(input, "FIM") == 0) break;
        
        Pokemon pokemon = {0};
        readPokemon(&pokemon, input);
        push(&stack, pokemon);
    }
    
    // Read number of commands
    fgets(input, sizeof(input), stdin);
    int n = atoi(input);

    Stack removidos;
    initStack(&removidos);
    
    // Process commands
    for (int i = 0; i < n; i++) {
        fgets(input, sizeof(input), stdin);
        input[strcspn(input, "\n")] = 0;  // Remove newline
        
        char command[2];
        strncpy(command, input, 1);
        command[1] = '\0';
        
        if (strcmp(command, "I") == 0) {
            Pokemon pokemon = {0};
            readPokemon(&pokemon, input + 2);
            push(&stack, pokemon);
        }
    
        else if (strcmp(command, "R") == 0) {
            Pokemon removed = pop(&stack);
            if (removed.id != 0) { 
                push(&removidos, removed);
            }
        }
    }
    
    // Print final list
    printRemovidos(&removidos);
    printList(&stack);
    
    while (!isEmpty(&stack)) {
        pop(&stack);
    }
    
    return 0;
}