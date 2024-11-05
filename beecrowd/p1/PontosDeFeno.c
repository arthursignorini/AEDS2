#include<stdio.h>
#include<string.h>

int main()
{
    int n1, n2;
    scanf("%d %d",&n1, &n2);
    char cargos[n1][50];
    int valores[n1];
    for(int i=0; i<n1; i++) {
        scanf("%s",cargos[i]);
        scanf("%d",&valores[i]);
    }
    for(int i=0; i<n2; i++) {
        int cont = 0;
        char palavra[30];
        while(1){
            scanf("%s",&palavra);
            if(strcmp(palavra, ".")==0) {
                printf("%d",cont);
                break;
            }
            for(int j=0; j<n1; j++) {
                if(strcmp(palavra, cargos[j])==0) {
                    cont += valores[j];
                }
            }
        }
    }
}
