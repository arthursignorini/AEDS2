#include <stdio.h>

// Função para mesclar duas sublistas e contar inversões
int merge_and_count(int arr[], int temp[], int left, int mid, int right) {
    int i = left;    // Índice inicial para a sublista esquerda
    int j = mid + 1; // Índice inicial para a sublista direita
    int k = left;    // Índice inicial para a lista mesclada
    int inv_count = 0;

    while (i <= mid && j <= right) {
        if (arr[i] <= arr[j]) {
            temp[k++] = arr[i++];
        } else {
            temp[k++] = arr[j++];
            inv_count += (mid - i + 1); // Contagem de inversões
        }
    }

    // Copia os elementos restantes da sublista esquerda
    while (i <= mid) {
        temp[k++] = arr[i++];
    }

    // Copia os elementos restantes da sublista direita
    while (j <= right) {
        temp[k++] = arr[j++];
    }

    // Copia a lista mesclada de volta para o array original
    for (i = left; i <= right; i++) {
        arr[i] = temp[i];
    }

    return inv_count;
}

// Função recursiva para contar inversões
int merge_sort_and_count(int arr[], int temp[], int left, int right) {
    int inv_count = 0;
    if (left < right) {
        int mid = (left + right) / 2;

        inv_count += merge_sort_and_count(arr, temp, left, mid);
        inv_count += merge_sort_and_count(arr, temp, mid + 1, right);
        inv_count += merge_and_count(arr, temp, left, mid, right);
    }
    return inv_count;
}

// Função principal para contar ultrapassagens
int count_inversions(int arr[], int n) {
    int temp[n];
    return merge_sort_and_count(arr, temp, 0, n - 1);
}

int main() {
    int casos;
    scanf("%d", &casos);
    
    for (int t = 0; t < casos; t++) {
        int n;
        scanf("%d", &n);

        int grid_largada[n];
        for (int i = 0; i < n; i++) {
            scanf("%d", &grid_largada[i]);
        }

        int grid_chegada[n];
        for (int i = 0; i < n; i++) {
            scanf("%d", &grid_chegada[i]);
        }

        // Mapeia a posição de cada competidor no grid de largada
        int pos[n + 1];
        for (int i = 0; i < n; i++) {
            pos[grid_largada[i]] = i;
        }

        // Cria um novo array com a posição dos competidores na ordem de chegada
        int ordem_chegada[n];
        for (int i = 0; i < n; i++) {
            ordem_chegada[i] = pos[grid_chegada[i]];
        }

        // Conta as ultrapassagens (inversões)
        int resultado = count_inversions(ordem_chegada, n);
        printf("%d\n", resultado);
    }
    return 0;
}
