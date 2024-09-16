#include <stdio.h>
#include <string.h>

int main () {
    int num;
    scanf("%d", &num);
    getchar();  // Descarta o '\n' restante no buffer

    int contPositivo = 0;
    int contNegativo = 0;

    char nomes[num][20];
    
    // Leitura dos nomes
    for(int i = 0; i < num; i++) {
        fgets(nomes[i], 20, stdin);
        
        // Remove o '\n' do final da string, se existir
        nomes[i][strcspn(nomes[i], "\n")] = 0;

        if(nomes[i][0] == '+') {
            contPositivo++;
        } else {
            contNegativo++;
        }
    }

    // Ordenação dos nomes com Bubble Sort
    char temp[20];
    for(int i = 0; i < num - 1; i++) {
        for(int j = 0; j < num - i - 1; j++) {
            if(strcmp(&nomes[j][2], &nomes[j+1][2]) > 0) {
                strcpy(temp, nomes[j]);
                strcpy(nomes[j], nomes[j+1]);
                strcpy(nomes[j+1], temp);
            }
        }
    }

    // Impressão dos nomes ordenados
    for(int i = 0; i < num; i++) {
        printf("%s\n", &nomes[i][2]);
    }

    // Exibição das contagens de positivos e negativos
    printf("Positivos: %d\n", contPositivo);
    printf("Negativos: %d\n", contNegativo);

    return 0;
}
