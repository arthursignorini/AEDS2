#include<stdio.h>
#include<string.h>

int main()
{
    int num;
    scanf("%d",&num);
    int vet[100];
    int n = 0;
    for(int i=0; i<num; i++) {
        char opcao[10];
        scanf("%s",opcao);

        if(strcmp(opcao, "MIN")==0) {
            int min = vet[0];
            for(int i=1; i<n; i++) {
                if(vet[i-1] > vet[i]) {
                    min = vet[i];
                }
            }
            printf("%d\n",min);
        } else if(strcmp(opcao, "POP")==0) { // retirar do final
            n--;
        } else if(strcmp(opcao, "PUSH")==0) { // colocar no final
            int valor;
            scanf("%d",&valor);
            vet[n] = valor;
            n++;
        }
    }

}
