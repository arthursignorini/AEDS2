package beecrowd;

import java.util.ArrayList;
import java.util.Scanner;

public class Pontos_de_Feno {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int op = sc.nextInt();
        int cargos = sc.nextInt();

        ArrayList<String> nomes = new ArrayList<>(op);
        ArrayList<Integer> valores = new ArrayList<>(op);

        // Leitura dos nomes e valores
        for (int i = 0; i < op; i++) {
            String nome = sc.next();
            int valor = sc.nextInt();
            nomes.add(nome);
            valores.add(valor);
        }

        // Processamento da entrada
        int ponto = 0;
        while (ponto < cargos) {
            int contador = 0;
            // Leitura de cada palavra
            while (sc.hasNext()) {
                String palavra = sc.next();
                if (palavra.equals(".")) {
                    ponto++;
                    break;
                } else {
                    // Verifica se a palavra está na lista de nomes e acumula o valor
                    for (int i = 0; i < op; i++) {
                        if (nomes.get(i).equals(palavra)) {
                            contador += valores.get(i);
                        }
                    }
                }
            }
            // Imprime o resultado acumulado para a seção
            System.out.println(contador);
        }
        sc.close();
    }
}
