#include<stdio.h>
#include<string.h>

typedef struct {
    char nome[20];
    int ouro;
    int prata;
    int bronze;
} Pais;

void swap(Pais paises[], int i, int j)
{
    Pais temp = paises[i];
    paises[i] = paises[j];
    paises[j] = temp;
}

int main()
{
    int num;
    scanf("%d",&num);
    Pais paises[num];
    for(int i=0; i<num; i++) {
        scanf("%s", paises[i].nome);
        scanf("%d",&paises[i].ouro);
        scanf("%d",&paises[i].prata);
        scanf("%d",&paises[i].bronze);
    }
    for(int i=0; i<num-1; i++) {
        for(int j=0; j<num-1-i; j++) {
            if(paises[j].ouro > paises[j+1].ouro) {
                swap(paises,j, j+1);
            } else if (paises[j].ouro == paises[j+1].ouro && paises[j].prata > paises[j+1].prata) {
                swap (paises, j, j+1);
            } else if (paises[j].ouro == paises[j+1].ouro && paises[j].prata == paises[j+1].prata && paises[j].bronze > paises[j+1].bronze) {
                swap (paises, j, j+1);
            } else {
                if(paises[j].ouro == paises[j+1].ouro && paises[j].prata == paises[j+1].prata && paises[j].bronze == paises[j+1].bronze && strcmp(paises[j].nome, paises[j+1].nome)< 1) {
                    swap (paises, j, j+1);
                }
            }
        }
    }

    for(int i=num-1; i>=0; i--) {
        printf("%s %d %d %d\n",paises[i].nome, paises[i].ouro, paises[i].prata, paises[i].bronze);
    }

}
