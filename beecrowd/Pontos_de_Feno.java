package beecrowd;

import java.util.ArrayList;
import java.util.Scanner;

public class Pontos_de_Feno {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int op = sc.nextInt(); // quantidade de opções de cargos que seriam avaliados
        int cargos = sc.nextInt(); // quantos textos seriam lidos

        ArrayList<String> nomes = new ArrayList<>(op);
        ArrayList<Integer> valores = new ArrayList<>(op);

       // aqui tá lendo os nomes dos cargos e os valores para cada
        for (int i = 0; i < op; i++) {
            String nome = sc.next();
            int valor = sc.nextInt();
            nomes.add(nome);
            valores.add(valor);
        }

        // aqui ta lendo os textos até chegar na variavel cargos
        int ponto = 0;
        while (ponto < cargos) {
            int contador = 0;
            
            while (sc.hasNext()) { // está sendo lida cada palavra
                String palavra = sc.next();
                if (palavra.equals(".")) {
                    ponto++;
                    break;
                } else {
                    // verifica se a palavra está na lista de nomes e acumula o valor
                    for (int i = 0; i < op; i++) {
                        if (nomes.get(i).equals(palavra)) {
                            contador += valores.get(i);
                        }
                    }
                }
            }
            // imprime a soma dos valores (salario deles)
            System.out.println(contador);
        }
        sc.close();
    }
}
