#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int T, F;
    double ratio;
} Dragao;

int compara(const void *a, const void *b) {
    Dragao *dragaoA = (Dragao *)a;
    Dragao *dragaoB = (Dragao *)b;
    
    if (dragaoA->ratio < dragaoB->ratio) return -1;
    if (dragaoA->ratio > dragaoB->ratio) return 1;
    return 0;
}

int main() {
    Dragao dragoes[100000];
    int n = 0;
    
    while (scanf("%d %d", &dragoes[n].T, &dragoes[n].F) != EOF) {
        dragoes[n].ratio = (double)dragoes[n].T / dragoes[n].F;
        n++;
    }
    
    qsort(dragoes, n, sizeof(Dragao), compara);
    
    long long multa_total = 0;
    long long tempo_acumulado = 0;
    
    for (int i = 0; i < n; i++) {
        multa_total += tempo_acumulado * dragoes[i].F;
        tempo_acumulado += dragoes[i].T;
    }
    
    printf("%lld\n", multa_total);
    return 0;
}
