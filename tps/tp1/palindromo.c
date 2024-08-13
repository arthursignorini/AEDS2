#include<stdio.h>
#include<string.h>
#include<stdbool.h>


bool conferir(char string[]){
    int x = strlen(string);
    for(int i=0; i<x/2; i++){
        if(string[i] != string[x-1-i]){
            return false;
        }
    }
    return true;
}

int main(){
    char string[50];
    printf("Digite um string: ");
    fgets(string, 50, stdin);
    string[strcspn(string, "\n")] = '\0';
    
    if(conferir(string)){
        printf("SIM");
    } else {
        printf("NAO");
    }

}