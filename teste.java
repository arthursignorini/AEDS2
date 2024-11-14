public class teste {
    int contarPalavras(String padrao) {
        int[] vet = new int[1];
        pesquisar(raiz,padrao,vet);
        return vet[0];
    }

    void pesquisar(No i, String str, int[] vet) {
        if(i == null) {
            return;
        } else if(i.letra == str.charAt(0)) {
            pesquisar(i.raiz, str, vet);
        } else if(str.charAt(0) < i.letra) {
            pesquisar(i.esq, str,vet);
        } else if(str.charAt(0) > i.letra) {
            pesquisar(.dir, str, vet);
        }
    }

    void pesquisar(No2 i, String str, int[]vet) {
        if(i!=null) {
            if(i.palavra.length() == str.length()) {
                vet[0]++;
            }
            pesquisar(i.esq, str,vet);
            pesquisar(i.dir, str,vet);
        }
    }
}
