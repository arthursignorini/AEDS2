#include <stdio.h>

int main() {
    int n;
    scanf("%d", &n); 

    FILE *arquivo = fopen("valores.txt", "w");

    for (int i = 0; i < n; i++) {
        double num;
        scanf("%lf", &num);
        fwrite(&num, sizeof(double), 1, arquivo);  // Escreve o número como binário no arquivo
    }

    fclose(arquivo);  // Fecha o arquivo após a escrita

    arquivo = fopen("valores.txt", "r");

    fseek(arquivo, 0, SEEK_END);  // Move o ponteiro para o final do arquivo
    long pos = ftell(arquivo);    // Obtem o tamanho do arquivo em bytes
    
    // Cada double ocupa 8 bytes, então vamos ler de trás para frente
    while (pos > 0) {
        pos -= sizeof(double);   // Move o ponteiro para o início do número anterior
        fseek(arquivo, pos, SEEK_SET);  // Ajusta o ponteiro

        double num;
        fread(&num, sizeof(double), 1, arquivo);  // Lê o número

        // Verifica se o número é praticamente inteiro
        if (num == (int)num) {
            printf("%d\n", (int)num);  // Imprime como inteiro
        } else {
            printf("%g\n", num);  // Imprime como double
        }
    }

    fclose(arquivo);  // Fecha o arquivo após a leitura

    return 0;
}
