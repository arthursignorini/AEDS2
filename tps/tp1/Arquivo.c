#include <stdio.h>
#include <stdlib.h>

int main() {
    FILE *file;
    int n;
    
    // Lê o número de entradas
    scanf("%d", &n);

    // Abre o arquivo para escrita binária
    file = fopen("arq.bin", "wb");
    if (file == NULL) {
        return 1;
    }

    // Lê e escreve os números doubles no arquivo
    for (int i = 0; i < n; i++) {
        double num;
        scanf("%lf", &num);
        fwrite(&num, sizeof(double), 1, file);
    }
    fclose(file);

    // Abre o arquivo para leitura binária
    file = fopen("arq.bin", "rb");
    if (file == NULL) {
        return 1;
    }

    // Move o ponteiro para o final do arquivo
    fseek(file, 0, SEEK_END);
    long fileSize = ftell(file);
    long doubleSize = sizeof(double);
    long pointer = fileSize - doubleSize;

    // Lê e imprime os números doubles em ordem reversa
    for (int i = 0; i < n; i++) {
        fseek(file, pointer, SEEK_SET);  // Move o ponteiro para a posição desejada
        double num;
        fread(&num, sizeof(double), 1, file);

        if (num == (int)num) {
            printf("%d\n", (int)num);
        } else {
            printf("%g\n", num);
        }
        pointer -= doubleSize;  // Move o ponteiro para o número anterior
    }

    fclose(file);
    return 0;
}
