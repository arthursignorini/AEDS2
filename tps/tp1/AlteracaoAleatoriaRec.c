#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

void trocar (char palavra[], int tam, char letra, char substituto, int i) {
    if(i == tam){
        return;
    }
    if(palavra[i] == letra){
        palavra[i] = substituto;
    }
    trocar(palavra, tam, letra, substituto, i+1);
}

int main() {
    char palavra[1000];

    srand(time(NULL)); 

    while(1){
        scanf(" %[^\n]", palavra);
        if(strcmp(palavra,"FIM") == 0){
            break;
        } else {
            char letra = 'a' + rand() % 26;
            char substituto = 'a' + rand() % 26;
            int tam = strlen(palavra);
            trocar(palavra, tam, letra, substituto, 0);
            printf("%s\n", palavra);
        }
    }
    return 0;
}
