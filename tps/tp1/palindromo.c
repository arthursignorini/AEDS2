#include <stdio.h>
#include <string.h>
#include <stdbool.h>

int main()
{
    char palavra[1000];
    while (true)
    {
        scanf(" %[^\n]", palavra);

        if (strcmp(palavra, "FIM") == 0)
        {
            break;
        }
        else
        {
            bool resp = true;
            int tam = strlen(palavra);
            
            for (int i = 0; i < (tam / 2); i++)
            {
                if (palavra[i] != palavra[tam - i - 1])
                {
                    resp = false;
                    break;
                }
            }

            if (resp)
            {
                printf("SIM\n");
            }
            else
            {
                printf("NAO\n");
            }
        }
    }

    return 0;
}
