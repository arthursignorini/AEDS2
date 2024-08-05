#include <stdio.h>
#include <limits.h>

int main() {
    signed char b = 0; // byte em C é o mesmo que signed char
    short s = 0;
    int i = 0;
    long l = 0;

    while (1) { // loop infinito
        b++;
        s++;
        i++;
        l++;
        printf("%d %d %d %ld\n", b, s, i, l);

        // Para evitar a execução infinita sem controle, podemos adicionar uma condição para parar o loop após transbordar
        // Por exemplo, interromper o loop se 'b' voltar a 0:
        if (b == 0) {
            break;
        }
    }

    return 0;
}
