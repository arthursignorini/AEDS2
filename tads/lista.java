public class lista {
    char[] vet;
    int n;

    lista (int tam) {
        vet = new char[tam];
        n = 0;
    }

    void inserirInicio(char x) throws Exception {
        if(n >= vet.length) {
            throw new Exception ("ERRO");
        }

        for(int i=n; i>0; i--) {
            vet[i] = vet[i-1];
        }
        vet[0] = x;
        n++;
    }

    void inserirFim(char x) throws Exception {
        if(n >= vet.length) {
            throw new Exception ("ERRO");
        }

        vet[n] = x;
        n++;
    }

    void inserir(char x, int pos) throws Exception {
        if(n >= vet.length || pos >= vet.length || pos > n) {
            throw new Exception("ERRO");
        }
        for(int i=n; i>pos; i--) {
            vet[i] = vet[i-1];
        }
        vet[pos] = x;
        n++;
    }

    char removerInicio() throws Exception {
        if(n<=0 || n>vet.length) {
            throw new Exception("ERRO");
        }
        char resp = vet[0];
        for(int i=1; i<n; i++) {
            vet[i-1] = vet[i];
        }
        n--;
        return resp;
    }

    char removerFim() throws Exception {
        if(n<=0 || n>vet.length) {
            throw new Exception("ERRO");
        }
        char resp = vet[n-1];
        n--;
        return resp;
    }

    char remover(int pos) throws Exception {
        if(n<=0 || pos >= n) {
            throw new Exception("ERRO"); 
        }
        char resp = vet[pos];
        for(int i=pos; i<n-1; i++) {
            vet[i] = vet[i+1]; 
        }
        n--;
        return resp;
    }

    void mostrar() {
        for(int i=0; i<n; i++) {
            System.out.print(vet[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        try {
            lista lista = new lista(5);
            
            lista.removerInicio();
            lista.mostrar();

            lista.removerFim();
            lista.mostrar();

            lista.remover(0);  
            lista.mostrar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


