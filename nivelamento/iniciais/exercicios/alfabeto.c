#include<stdio.h>

void result (int x, char alfabeto[])
{
    if(x >= 1 && x <= 26) {
        for(int i=0; i<x; i++){
            alfabeto[i] = 'a' + i;
        }
        alfabeto[x] = '\0';
    } else {
        alfabeto[0] = '\0';
    }
}

int main()
{
    int x;
    printf("Digite um numero: ");
    scanf("%d",&x);
    char alfabeto[27];
    result(x, alfabeto);
    if(alfabeto[0]  != '\0'){
        printf("As primeiras %d letras do alfabeto sao: %s\n",x,alfabeto);
    } else {
        printf("Numero invalido!");
    }
}
