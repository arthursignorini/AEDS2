public class listaSequencial {
    int[] vet;
    int n;

    listaSequencial (int tam) {
        vet = new int[tam];
        n = 0;
    }

    void inserirInicio(int x) throws Exception {

        if(n >= vet.length) {
            throw new Exception ("ERRO");
        }

        for(int i=n; i>0; i--) {
            vet[i] = vet[i-1];
        }
        vet[0] = x;
        n++;
    }

    void inserirFim(int x) throws Exception {
        if(n >= vet.length) {
            throw new Exception ("ERRO");
        }

        vet[n] = x;
        n++;
    }

    void inserir(int x, int pos) throws Exception {
        if(n >= vet.length || pos >= vet.length || pos > n) {
            throw new Exception("ERRO");
        }
        for(int i=n-1;i>pos-1; i--) {
            vet[i] = vet[i+1];
        }
        vet[pos] = x;
        n++;
    }

    int removerInicio() throws Exception {
        if(n<=0 || n>vet.length) {
            throw new Exception("ERRO");
        }
        int resp = vet[0];
        for(int i=1; i<n; i++) {
            vet[i-1] = vet[i];
        }
        n--;
        return resp;
    }

    int removerFim() throws Exception {
        if(n<=0 || n>vet.length) {
            throw new Exception("ERRO");
        }
        int resp = vet[n-1];

        n--;
        return resp;
    }

    int remover(int pos) throws Exception {
        if(n<=0 || n>vet.length) {
            throw new Exception("ERRO"); 
        }
        int resp = vet[pos];
        for(int i=pos; i<n+1; i++) {
            vet[i] = vet[i+1]; 
        }
        n--;
        return resp;
    }

    void mostrar() {
        for(int i=0; i<n; i++) {
            System.out.println(vet[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        try {
            listaSequencial lista = new listaSequencial(5);
            lista.inserirInicio(1);
            lista.mostrar();

            lista.inserirFim(8);
            lista.mostrar();

            lista.inserir(4, 2);  
            lista.mostrar();

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
