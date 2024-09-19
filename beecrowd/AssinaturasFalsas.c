#include <stdio.h>
#include <string.h>

#define MAX_ASSINATURA 21


int contarDiferencas(char assinatura1[], char assinatura2[]) {
    int diferencas = 0;

    for (int i = 0; i < strlen( assinatura1 ); i++) {
        char ch1 = assinatura1[i];
        char ch2 = assinatura2[i];


        if (ch1 >= 'A' && ch1 <= 'Z') {
            ch1 = ch1 + ('a' - 'A');
        }
        if (ch2 >= 'A' && ch2 <= 'Z') {
            ch2 = ch2 + ('a' - 'A');
        }

        if (ch1 != ch2) {
            diferencas++;
        }
    }

    return diferencas;
}

int main() {
    while(1) {

    int numAlunos;
    scanf("%d", &numAlunos);
    if(numAlunos == 0) {
        break;
    }
    char assinaturas[numAlunos][MAX_ASSINATURA];


    for (int i = 0; i < numAlunos; i++) {
        char nome[21];
        scanf("%s", nome);
        scanf("%s", assinaturas[i]);
    }

    int alunosAula;
    scanf("%d", &alunosAula);
    char assinaturasAula[alunosAula][MAX_ASSINATURA];


    for (int i = 0; i < alunosAula; i++) {
        char nomeAula[21];
        scanf("%s", nomeAula);
        scanf("%s", assinaturasAula[i]);
    }

    int contadorFalsas = 0;


    for (int i = 0; i < numAlunos; i++) {
        for (int j = 0; j < alunosAula; j++) {
            if (strlen(assinaturas[i]) == strlen(assinaturasAula[j])) {
                int diferencas = contarDiferencas(assinaturas[i], assinaturasAula[j]);
                if (diferencas > 1) {
                    contadorFalsas++;
                }
            }
        }
    }

    printf("%d\n", contadorFalsas);

    return 0;
    }
}
