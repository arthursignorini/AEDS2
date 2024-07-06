#include<stdio.h>
#include<stdbool.h>

bool conferir(int vet[])
{
    int x;
    bool resp = false;
    printf("Digite um numero: ");
    scanf("%d",&x);
    for(int i=0; i<5; i++){
        if(x == vet[i]){
            resp = true;
            break;
        }
    }
    return resp;
}

int main()
{
    int vet[5];
    for(int i=0; i<5; i++){
        printf("Digite um valor: ");
        scanf("%d",&vet[i]);
    }

    int temp;
    for(int i=0; i<5-1; i++){
        for(int j=0;j<5-i-1;j++){
            if(vet[j] > vet[j+1]){
                temp = vet[j+1];
                vet[j+1] = vet[j];
                vet[j] = temp;
            }
        }
    }

    bool resposta = conferir(vet);
    if(resposta){
        printf("Existe um numero igual no vetor");
    } else {
        printf("Nao existe um numero igual no vetor");
    }
}
