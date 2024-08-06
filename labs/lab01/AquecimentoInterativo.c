#include<stdio.h>
#include<string.h>

int contar (char string[]){
    int tam = strlen(string);
    int cont = 0;
    for(int i=0; i<tam; i++){
        if(string[i] >= 'A' && string[i] <= 'Z') {
            cont++;
        }
    }
    return cont;
}

int main(){
    char string[50];
    printf("Digite uma string: ");
    fgets(string, 50, stdin);
    int x = contar(string);
    printf("%d",x);
}