package beecrowd;

import java.util.ArrayList;
import java.util.Scanner;

// é falsa se tiver mais de uma diferença entre a original e a checada
// 'A' e 'a' também é uma diferença

public class Assinaturas_Falsas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String qntdAlunos;
        String zero = "0";

        while (!(qntdAlunos = sc.nextLine()).equals(zero)) {
            
            int result = Integer.parseInt(qntdAlunos);
            ArrayList<String> nomes = new ArrayList<>(result);
            for (int i = 0; i < result; i++) {
                String nome = sc.nextLine();
                nomes.add(nome);
            }

            int qntdPresentes = sc.nextInt();
            sc.nextLine();
            ArrayList<String> presentes = new ArrayList<>(qntdPresentes);
            for (int i = 0; i < qntdPresentes; i++) {
                String nomeP = sc.nextLine();
                presentes.add(nomeP);
            }

            int contador = 0;
            for(int i=0; i<result; i++) {
                for(int j=0; j<qntdPresentes; j++) {
                    if(nomes.get(i).toLowerCase().equals(presentes.get(j).toLowerCase())) {
                        int cont = verificar(nomes.get(i), presentes.get(j));
                        if (cont > 1) {
                            contador++;
                        }
                    }
                }
            }
            System.out.println(contador);
        }
        sc.close();
    }

    public static int verificar (String palavra1, String palavra2) {
        int cont = 0;
        for(int i=0; i<palavra1.length(); i++) {
            if(palavra1.charAt(i) != palavra2.charAt(i)) {
                cont++;
            }
        }
        return cont;
    }
}
