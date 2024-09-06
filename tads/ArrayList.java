public class ArrayList {
    int n;
    int[] vet;

    ArrayList (int tam ) {
        vet = new int[tam];
        n = 0;
    }

    void inserirInicio(int x ) throws Exception{ // o N só pode ir até o tamanho do vetor
        if(n >= vet.length) {
            throw new Exception("ERRO");
        }

        if(n == 0) {
            vet[0] = x;
        } else {
            for(int i=n-1; i>=0; i--) {
                vet[n] =  vet[i]; 
            }
            vet[0] = x;
        }
        n++;
    }

    void inserirFim (int x) throws Exception {
        if(n >= vet.length) {
            throw new Exception ("ERRO");
        }
        
        vet[n] = x;
        n++;
    }

    void inserir (int x, int pos) throws Exception {
        if(n >= vet.length) {
            throw new Exception ("ERRO");
        }

        for(int i=n-1; i<pos; i--) {
            
        }
    }
}


