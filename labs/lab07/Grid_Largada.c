#include <stdio.h>

int main()
{
    for (int i = 0; i < 3; i++)
    {

        int qntd;
        scanf("%d", &qntd);

        int vetSaida[qntd];
        for (int i = 0; i < qntd; i++)
        {
            scanf("%d", &vetSaida[i]);
        }

        int vetChegada[qntd];
        for (int i = 0; i < qntd; i++)
        {
            scanf("%d", &vetChegada[i]);
        }

        int contador = 0;
        for (int i = 0; i < qntd; i++)
        {
            for (int j = 0; j < qntd; j++)
            {
                if (vetSaida[i] == vetChegada[j])
                {
                    if (j < i)
                    {
                        contador += (i-j);
                    }
                }
            }
        }
        printf("%d\n", contador);
    }
}
