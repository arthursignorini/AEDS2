#include<stdio.h>

void funcao(int vet[])
{
    int maior = vet[0];
    int menor = vet[0];

    for(int i=0; i<5; i++){
        if(vet[i] > maior) {
            maior = vet[i];
        }
        if(vet[i] < menor){
            menor = vet[i];
        }
    }
    printf("O maior numero eh %d e o menor numero eh %d",maior, menor);
}

int main()
{
    int vet[5];
    for(int i=0; i<5;i++){
        printf("Digite um valor: ");
        scanf("%d",&vet[i]);
    }
    funcao(vet);
}
