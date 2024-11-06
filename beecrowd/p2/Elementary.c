#include <stdio.h>

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
        for (int x = 1; x < tam; x++) {
            int temp = vet[x];
            int y = x - 1;
            int moved = 0;

            // Inserção de `temp` no local correto
            while (y >= 0 && vet[y] > temp) {
                vet[y + 1] = vet[y];
                y--;
            }
            vet[y + 1] = temp;
            cont++; // Incrementa `cont` quando `temp` é colocado em sua posição final
        }
        printf("%d\n", cont); // Exibe o número de inserções
    }
    return 0;
}
