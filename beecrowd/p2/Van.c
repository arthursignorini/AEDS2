#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char nome[20];
    char regiao[3]; 
    int distancia;
} Aluno;

int main() {
    int qntd;
    scanf("%d", &qntd);

    Aluno *alunos = (Aluno *)malloc(qntd * sizeof(Aluno));

   
    for (int i = 0; i < qntd; i++) {
        scanf("%s", alunos[i].nome);
        scanf("%s", alunos[i].regiao);
        scanf("%d", &alunos[i].distancia);
    }

    for (int i = 0; i < qntd - 1; i++) {
        for (int j = 0; j < qntd - 1 - i; j++) {
            if (
                (alunos[j].distancia > alunos[j + 1].distancia) ||
                (alunos[j].distancia == alunos[j + 1].distancia && strcmp(alunos[j].regiao, alunos[j + 1].regiao) > 0) ||
                (alunos[j].distancia == alunos[j + 1].distancia && strcmp(alunos[j].regiao, alunos[j + 1].regiao) == 0 && strcmp(alunos[j].nome, alunos[j + 1].nome) > 0)
            ) {
                Aluno temp = alunos[j];
                alunos[j] = alunos[j + 1];
                alunos[j + 1] = temp;
            }
        }
    }

    for (int i = 0; i < qntd; i++) {
        printf("%s\n", alunos[i].nome);
    }

    free(alunos);
    return 0;
}
