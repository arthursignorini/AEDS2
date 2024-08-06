#include<stdio.h>
#include<string.h>

int contar(char string[], int i){
    if(string[i] == '\0') {
        return 0;
    } else {
        if(string[i] >= 'A' && string[i] <= 'Z'){
            return 1 + contar(string, i+1);
        } else {
            return contar(string, i+1);
        }
    }
}

int main() {
    char string[50];
    printf("Digite uma frase: ");
    fgets(string, 50, stdin);
    int num = contar(string, 0);
    printf("%d",num);
}