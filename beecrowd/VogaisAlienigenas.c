#include <stdio.h>
#include <string.h>
#include <stdbool.h>

int main() {
    char alfa[20];
    char str[50];
    bool resp = true;

    while (resp) {

        if (fgets(alfa, sizeof(alfa), stdin) == NULL) {
            break;
        }
        
        if (fgets(str, sizeof(str), stdin) == NULL) {

            break;
        }

        int contador = 0;

        for (int i = 0; i < strlen(alfa); i++) {
            for (int j = 0; j < strlen(str); j++) {
                if (str[j] == alfa[i]) {
                    contador++;
                }
            }
        }
        printf("%d\n", contador);
    }
    return 0;
}
