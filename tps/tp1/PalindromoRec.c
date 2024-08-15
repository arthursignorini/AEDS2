#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool palindromo(char palavra[], int i, int j) {
    if (i >= j) {
        return true;
    } else if (palavra[i] != palavra[j]) {
        return false;
    } else {
        return palindromo(palavra, i + 1, j - 1);
    }
}
int main() {
    char palavra[1000];

    while (true) {
        scanf("%s", palavra);

        if (strcmp(palavra, "FIM") == 0) {
            break;
        } else {
            int len = strlen(palavra);
            if (palindromo(palavra, 0, len - 1)) {
                printf("SIM\n");
            } else {
                printf("NAO\n");
            }
        }
    }
    return 0;
}