import java.util.ArrayList;
import java.util.Scanner;

public class Time_Duendes {
    String nome;
    int idade;

    public Time_Duendes(String n, int i) {
        nome = n;
        idade = i;
    }

    @Override
    public String toString() {
        return nome + " " + idade;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int qntd = sc.nextInt();
        sc.nextLine();
        ArrayList<Time_Duendes> duendes = new ArrayList<>(qntd);

        for(int i = 0; i < qntd; i++) {
            String nome = sc.nextLine();
            int idade = sc.nextInt();
            sc.nextLine();
            Time_Duendes duende = new Time_Duendes(nome, idade);
            duendes.add(duende);
        }

        bubblesort(duendes, qntd);

        for(int i = 0; i < qntd; i++) {
            System.out.println(duendes.get(i));
        }

        int times = qntd / 3;
        
    }

    public static void bubblesort(ArrayList<Time_Duendes> duendes, int qntd) {
        for(int i = 0; i < qntd - 1; i++) {
            for(int j = 0; j < qntd - 1 - i; j++) {
                if(duendes.get(j).idade > duendes.get(j + 1).idade) {
                    Time_Duendes temp = duendes.get(j + 1);
                    duendes.set(j + 1, duendes.get(j));
                    duendes.set(j, temp);
                }
            }
        }
    }
}
