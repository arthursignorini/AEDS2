#include <stdio.h>

long long merge_and_count(int arr[], int temp[], int left, int mid, int right) {
    int i = left, j = mid + 1, k = left;
    long long inversions = 0;

    while (i <= mid && j <= right) {
        if (arr[i] <= arr[j]) {
            temp[k++] = arr[i++];
        } else {
            temp[k++] = arr[j++];
            inversions += (mid - i + 1);
        }
    }

    while (i <= mid) temp[k++] = arr[i++];
    while (j <= right) temp[k++] = arr[j++];
    for (i = left; i <= right; i++) arr[i] = temp[i];

    return inversions;
}

long long merge_sort_and_count(int arr[], int temp[], int left, int right) {
    long long inversions = 0;
    if (left < right) {
        int mid = (left + right) / 2;
        inversions += merge_sort_and_count(arr, temp, left, mid);
        inversions += merge_sort_and_count(arr, temp, mid + 1, right);
        inversions += merge_and_count(arr, temp, left, mid, right);
    }
    return inversions;
}

int main() {
    int n;
    while (scanf("%d", &n) != EOF) {
        int grid_start[n], grid_end[n], position[n + 1], temp[n];

        for (int i = 0; i < n; i++) scanf("%d", &grid_start[i]);
        for (int i = 0; i < n; i++) {
            scanf("%d", &grid_end[i]);
            position[grid_end[i]] = i;
        }

        for (int i = 0; i < n; i++) {
            grid_start[i] = position[grid_start[i]];
        }

        long long inversions = merge_sort_and_count(grid_start, temp, 0, n - 1);

        printf("%lld\n", inversions);
    }
    return 0;
}
