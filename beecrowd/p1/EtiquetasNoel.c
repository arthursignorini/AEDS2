#include<stdio.h>
#include<string.h>

typedef struct {
    char idioma[15];
    char traducao[50];
} Tradutor;

int main() {
    int num;
    scanf("%d",&num);
    Tradutor tradutor[num];
    getchar();
    for(int i=0; i<num; i++) {
        scanf("%s", tradutor[i].idioma);
        getchar();
        fgets(tradutor[i].traducao, 50, stdin);
        tradutor[i].traducao[strcspn(tradutor[i].traducao, "\n")] = '\0';
    }

    int num2;
    scanf("%d",&num2);
    char nome[50];
    char idiomafalado[15];
    getchar();
    for(int i=0; i<num2; i++) {
        fgets(nome, 15, stdin);
        nome[strcspn(nome, "\n")] = '\0';
        scanf("%s", idiomafalado);
        for(int j=0; j<num; j++) {
            if(strcmp(idiomafalado, tradutor[j].idioma)==0) {
                printf("%s\n",nome);
                printf("%s\n",tradutor[j].traducao);
            }
        }
        getchar();
    }
}
