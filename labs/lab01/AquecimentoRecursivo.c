#include <stdio.h>
#include <string.h>

int contar(char string[], int i) {
    if (string[i] == '\0') {
        return 0;
    } else {
        if (string[i] >= 'A' && string[i] <= 'Z') {
            return 1 + contar(string, i + 1);
        } else {
            return contar(string, i + 1);
        }
    }
}

int main() {

    char palavra[1000];
    while (1) 
    {
        scanf(" %[^\n]", palavra);

        if (strcmp(palavra, "FIM") == 0) {
            break;
        } else {
            int num = contar(palavra, 0);
            printf("%d\n", num); 
        }
    }
    
    return 0; 
}
