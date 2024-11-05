#include<stdio.h>
#include<string.h>

void swapNome (char vet[][20], int i, int j) {
    char temp[20];
    strcpy(temp, vet[i]);
    strcpy(vet[i], vet[j]);
    strcpy(vet[j], temp);
}

void swapIdade (int vet[], int i, int j) {
    int temp = vet[i];
    vet[i] = vet[j];
    vet[j] = temp;
}

int main() {
    int qntdDuendes;
    scanf("%d",&qntdDuendes);
    char duendesNome[qntdDuendes][20];
    int duendesIdade[qntdDuendes];

    char str[30];
    for(int i=0; i<qntdDuendes; i++) {
        char nome[20];
        scanf("%s",duendesNome[i]);
        int idade;
        scanf("%d",&duendesIdade[i]);
    }

    for(int i=0; i<qntdDuendes-1; i++) {
        for(int j=0; j<qntdDuendes-1-i; j++) {
            if(duendesIdade[j] < duendesIdade[j+1] || (duendesIdade[j] == duendesIdade[j+1] && strcmp(duendesNome[j], duendesNome[j+1])> 1)){
                swapIdade(duendesIdade, j, j+1);
                swapNome(duendesNome, j, j+1);
            }
        }
    }

    int qntdgrupos = qntdDuendes / 3;
    int cont = 0;
    while(cont < qntdgrupos) {
        int index = cont;
        printf("Time %d\n",cont+1);
        // lideres
        printf("%s %d\n",duendesNome[index], duendesIdade[index]);
        index += qntdgrupos;
        // entregadores
        printf("%s %d\n",duendesNome[index], duendesIdade[index]);
        index += qntdgrupos;
        // pilotos
        printf("%s %d\n\n",duendesNome[index], duendesIdade[index]);
        index += qntdgrupos;
        cont++;
    }
}
