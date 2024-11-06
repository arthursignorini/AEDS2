#include<stdio.h>
#include<string.h>
#include<stdlib.h>

typedef struct {
    char nome[100];
    int peso;
    int idade;
    float altura;
} Rena;

int main() {
    int x;
    scanf("%d",&x);
    for(int i=0; i<x; i++) {
        int qntd;
        int mostrar;
        scanf("%d",&qntd);
        scanf("%d",&mostrar);

        Rena *renas = (Rena*)malloc(qntd*sizeof(Rena));

        for(int i=0; i<qntd; i++) {
            scanf("%s",renas[i].nome);
            scanf("%d",&renas[i].peso);
            scanf("%d",&renas[i].idade);
            scanf("%f",&renas[i].altura);
        }

        for(int x=0; x<qntd-1; x++) {
            for(int j=0; j<qntd-1-i; j++) {
                if (renas[j].peso < renas[j+1].peso ||
                    renas[j].peso == renas[j+1].peso && renas[j].idade > renas[j+1].idade ||
                    renas[j].peso == renas[j+1].peso && renas[j].idade == renas[j+1].idade && renas[j].altura > renas[j+1].altura ||
                    renas[j].peso == renas[j+1].peso && renas[j].idade == renas[j+1].idade && renas[j].altura == renas[j+1].altura && strcmp(renas[j].nome, renas[j+1].nome) > 0
                ) {
                    Rena temp = renas[j];
                    renas[j] = renas[j+1];
                    renas[j+1] = temp;
                }
            }
        }
        printf("CENARIO {%d}\n",x);
        for(int q=0; q<mostrar; q++) {
            printf("%d - %s\n",q+1, renas[q].nome);
        }
    }
}