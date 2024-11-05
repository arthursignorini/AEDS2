package beecrowd;

import java.util.ArrayList;
import java.util.Scanner;

class Time_Duendes {
    String nome;
    int idade;

    public Time_Duendes(String n, int i) {
        nome = n;
        idade = i;
    }

    public String toString() {
        return nome + " " + idade;
    }
}

public class Duendes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Time_Duendes> duendes = new ArrayList<>();
        int qntd;

        qntd = sc.nextInt();
        sc.nextLine(); 

        // ler as informações dos duendes
        for (int i = 0; i < qntd; i++) {
            String nome = sc.next();
            int idade = sc.nextInt();
            sc.nextLine(); 

            Time_Duendes duende = new Time_Duendes(nome, idade);
            duendes.add(duende);
        }

        // ordenar os duendes - idades
        bubbleSort(duendes);

        // separando os duendes por times
        int times = qntd / 3;
        int grupo = 0;
        while (grupo < times) {
            System.out.println("Time " + (grupo + 1));
            
            int index = grupo;
            // líder
            if (index < duendes.size()) {
                System.out.println(duendes.get(index));
                index += times;
            }
            
            // entregadores
            if (index < duendes.size()) {
                System.out.println(duendes.get(index));
                index += times;
            }
            
            // pilotos
            if (index < duendes.size()) {
                System.out.println(duendes.get(index));
                index += times;
            }
            System.out.println();
            grupo++;
        }
        sc.close();
    }

    // ordenar os mais velhos
    public static void bubbleSort(ArrayList<Time_Duendes> duendes) {
        int n = duendes.size();
        boolean trocou;
        do {
            trocou = false;
            for (int i = 0; i < n - 1; i++) {
                Time_Duendes d1 = duendes.get(i);
                Time_Duendes d2 = duendes.get(i + 1);
                
                // verificar se uma idade é menor e se forem iguais, ver a ordem alfabetica
                if (d1.idade < d2.idade || (d1.idade == d2.idade && d1.nome.compareTo(d2.nome) > 0)) {
                    // troca os objetos
                    duendes.set(i, d2);
                    duendes.set(i + 1, d1);
                    trocou = true;
                }
            }
            n--; 
        } while (trocou);
    }
}

