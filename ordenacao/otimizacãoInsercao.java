package ordenacao;

public class otimizacãoInsercao {
    public static void main(String[] args) {
        int[] vet = {5, 1, 7, 4, 9};

        for (int i = 1; i < vet.length; i++) {
            int temp = vet[i];
            int newPos = pesquisaBinaria(0, i - 1, vet, temp); // Encontrar posição correta

            // Mover todos os elementos para a direita para liberar espaço
            for (int j = i; j > newPos; j--) {
                vet[j] = vet[j - 1];
            }

            vet[newPos] = temp; // Inserir o elemento na posição correta
        }

        // Exibir o vetor ordenado
        for (int num : vet) {
            System.out.print(num + " ");
        }
    }

    // Pesquisa binária para encontrar a posição de inserção correta
    public static int pesquisaBinaria(int esq, int dir, int[] vet, int num) {
        while (esq <= dir) {
            int meio = (esq + dir) / 2;
            if (vet[meio] == num) {
                return meio;
            }

            if (vet[meio] > num) {
                dir = meio - 1;
            } else {
                esq = meio + 1;
            }
        }
        return esq; // Retorna a posição correta para inserção
    }
}
