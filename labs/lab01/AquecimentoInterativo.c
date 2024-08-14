#include <stdio.h>
#include <string.h>

int contar(char string[]) {
    int tam = strlen(string);
    int cont = 0;
    for(int i = 0; i < tam; i++) {
        if(string[i] >= 'A' && string[i] <= 'Z') {
            cont++;
        }
    }
    return cont;
}

int main() {

    char string[1000];
    while(1){
        scanf(" %[^\n]", string);

        if (strcmp(string, "FIM") == 0) {
            break;
        } else {
            char string[50];
        
            fgets(string, 50, stdin);
            string[strcspn(string, "\n")] = 0;

            int x = contar(string);
            printf("%d\n", x);
        }
    }
    return 0;
}
