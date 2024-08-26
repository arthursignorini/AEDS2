#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// Função recursiva para substituir o caracter na string
void substituicaoString(char *str, char caracter, char substituto, int index) {
    if (str[index] == '\0') {
        return;  // Caso base: fim da string
    }
    
    // Substitui o caracter se ele for igual ao caracter de busca
    if (str[index] == caracter) {
        str[index] = substituto;
    }
    
    // Chamada recursiva para o próximo caractere
    substituicaoString(str, caracter, substituto, index + 1);
}

int main() {
    char caracter, substituto;
    char str[100];
    
    // Inicializa o gerador de números aleatórios com uma seed fixa
    srand(4);

    while (1) {
        // Lê a string do usuário
        fgets(str, 100, stdin);
        
        // Remove o newline ('\n') adicionado pelo fgets
        str[strcspn(str, "\n")] = 0;

        // Verifica se a string é "FIM"
        if (strcmp(str, "FIM") == 0) {
            break;
        }

        // Gera caracteres aleatórios entre 'a' e 'z'
        caracter = 'a' + (rand() % 26);
        substituto = 'a' + (rand() % 26);

        // Realiza a substituição recursivamente
        substituicaoString(str, caracter, substituto, 0);

        // Exibe a nova string
        printf("%s\n", str);
    }

    return 0;
}
