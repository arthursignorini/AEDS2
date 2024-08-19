#include <stdio.h>
#include <string.h>

int main() {
    char string1[100], string2[100];

    while (1) {
 
        scanf("%s %s", string1, string2);
        
        if (strcmp(string1, " ") == 0 || strcmp(string2, " ") == 0) {
            break;
        }

        int tam1 = strlen(string1);
        int tam2 = strlen(string2);

        if (tam1 < tam2) {
            for (int i = 0; i < tam1; i++) {
                printf("%c%c", string1[i], string2[i]);
            }
            for (int i = tam1; i < tam2; i++) {
                printf("%c", string2[i]);
            }
        } else {
            for (int i = 0; i < tam2; i++) {
                printf("%c%c", string1[i], string2[i]);
            }
            for (int i = tam2; i < tam1; i++) {
                printf("%c", string1[i]);
            }
        }
        printf("\n");
    }

    return 0;
}
