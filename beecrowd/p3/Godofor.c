#include<stdio.h>
#include<string.h>

typedef struct Godofor
{
    char nome[101];
    int nivelPoder;
    int qntdDeuses;
    int qntdMortes;
} Godofor;


int main() {
    int x;
    scanf("%d",&x);

    if (x <= 0) {
        return 0; 
    }

    Godofor pessoas[x];

    for(int i=0; i<x; i++) {
        scanf("%101s", pessoas[i].nome); 
        scanf("%d",&pessoas[i].nivelPoder);
        scanf("%d",&pessoas[i].qntdDeuses);
        scanf("%d",&pessoas[i].qntdMortes);
    }

    Godofor maisPoderoso = pessoas[0];
    for(int i=1; i<x; i++) {
        if((pessoas[i].nivelPoder > maisPoderoso.nivelPoder) ||
           (pessoas[i].nivelPoder == maisPoderoso.nivelPoder && pessoas[i].qntdDeuses > maisPoderoso.qntdDeuses) ||
           (pessoas[i].nivelPoder == maisPoderoso.nivelPoder && pessoas[i].qntdDeuses == maisPoderoso.qntdDeuses && pessoas[i].qntdMortes < maisPoderoso.qntdMortes) || 
           (pessoas[i].nivelPoder == maisPoderoso.nivelPoder && pessoas[i].qntdDeuses == maisPoderoso.qntdDeuses && pessoas[i].qntdMortes == maisPoderoso.qntdMortes && strcmp(pessoas[i].nome, maisPoderoso.nome) < 0) 
        ) {
            maisPoderoso = pessoas[i];
        } 
    }
    printf("%s",maisPoderoso.nome);
}