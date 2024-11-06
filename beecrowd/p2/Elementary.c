#include <stdio.h>

void swap(int a, int b, int vet[]) {
    int temp = vet[a];
    vet[a] = vet[b];
    vet[b] = temp;
}

int main() {
    int qntd;
    scanf("%d", &qntd);
    for (int i = 0; i < qntd; i++) {
        int tam;
        scanf("%d", &tam);
        int vet[tam];
        for (int j = 0; j < tam; j++) {
            scanf("%d", &vet[j]);
        }
        int cont = 0;
        for (int i = 0; i < tam - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < tam; j++) {
                if (vet[menor] > vet[j]) {
                    menor = j;
                }
            }
            if (menor != i) { 
                swap(menor, i, vet);
                cont++;
            }
        }
        printf("%d\n", cont); 
    }
    return 0;
}
