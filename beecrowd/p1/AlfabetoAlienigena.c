#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool comparar(char alfa[], char str[], int n1, int n2)
{
    int cont = 0;
    for (int i = 0; i < n1; i++)
    {
        for (int j = 0; j < n2; j++)
        {
            if (alfa[i] == str[j])
            {
                cont++;
            }
        }
    }
    if (cont == n2)
    {
        return true;
    }
    else
    {
        return false;
    }
}

int main()
{
    int n1, n2;
    scanf("%d %d", &n1, &n2);
    getchar();

    char alfa[n1 + 1];
    char str[n2 + 1];


    scanf("%s",&alfa);
    scanf("%s",&str);

    bool resposta = comparar(alfa, str, n1, n2);
    if (resposta)
    {
        printf("S");
    }
    else
    {
        printf("N");
    }

    return 0;
}
