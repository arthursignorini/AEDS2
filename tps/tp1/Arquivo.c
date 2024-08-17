#include <stdio.h>
#include <math.h>

int main() {
    int n;
    scanf("%d", &n);  // Número de valores a serem lidos

    // Abrir o arquivo para escrita em modo texto
    FILE *arquivo = fopen("Questao8.txt", "w");

    // Escrever os números reais no arquivo
    for (int i = 0; i < n; i++) {
        double num;
        scanf("%lf", &num);
        fprintf(arquivo, "%.10lf\n", num);  // Escreve o número em formato texto
    }

    fclose(arquivo);

    // Reabrir o arquivo para leitura em modo texto
    arquivo = fopen("Questao8.txt", "r");

    // Mover o ponteiro para o final do arquivo
    fseek(arquivo, 0, SEEK_END);
    long pos = ftell(arquivo);  // Obter a posição atual (tamanho do arquivo)
    
    // Ler os números de trás para frente, ignorando a leitura duplicada do primeiro número
    int first_number_printed = 0;
    while (pos > 0) {
        fseek(arquivo, --pos, SEEK_SET);  // Voltar uma posição no arquivo

        if (fgetc(arquivo) == '\n' && pos != 0) {
            fseek(arquivo, pos + 1, SEEK_SET);
            double num;
            fscanf(arquivo, "%lf", &num);

            // Verificar se o número é praticamente inteiro
            if (num == (int)num) {
                printf("%d\n", (int)num);  // Imprime como inteiro
            } else {
                printf("%g\n", num);  // Imprime como double com casas decimais significativas
            }

            first_number_printed = 1;
        }
    }
    fclose(arquivo);
    return 0;
}
