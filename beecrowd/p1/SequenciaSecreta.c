#include<stdio.h>
#include<string.h>

void ordenar(char vet[][50], int num_palavras)
{
    for(int i=0;i<num_palavras-1; i++) {
        for(int j=0; j<num_palavras-1-i; j++) {
            if(strlen(vet[j]) < strlen(vet[j+1])){
                char temp[50];
                strcpy(temp, vet[j+1]);
                strcpy(vet[j+1], vet[j]);
                strcpy(vet[j], temp);
            }
        }
    }
}

int main()
{
    int num;
    scanf("%d",&num);
    getchar();
    char vet[50][50];

    for(int i=0; i<num; i++) {
        char palavra[20];
        int pos = 0;
        while(scanf("%s",&palavra)) {
            strcpy(vet[pos],palavra);
            pos++;
            char c = getchar();
            if(c == '\n'){
                break;
            }
        }
        ordenar(vet, pos);
        for(int j=0; j<pos; j++) {
            printf("%s ",vet[j]);
        }
        printf("\n");
    }
}
