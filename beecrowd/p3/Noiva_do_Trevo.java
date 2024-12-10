import java.util.Scanner;
import java.util.ArrayList;

public class Noiva_do_Trevo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int min = sc.nextInt();
        int qntd = sc.nextInt();

        ArrayList<String> nomes = new ArrayList<>();
        ArrayList<Integer> horarios = new ArrayList<>();

        int minMeiaNoite = 24 * 60; 

        for (int i = 0; i < qntd; i++) {
        
            String entrada = sc.nextLine();
            String[] partes = entrada.split(":"); 

            try {
                int hora = Integer.parseInt(partes[0]);
                String[] minutoENome = partes[1].split(" "); 
                int minutos = Integer.parseInt(minutoENome[0]);
                String nome = minutoENome[1];

                int minEntrada = (hora * 60) + minutos;

                if ((hora == 23 && (minMeiaNoite - minEntrada) <= min) || 
                    (hora == 0 && minEntrada <= min)) {
                    nomes.add(nome);

                    if (hora == 23) {
                        horarios.add(minMeiaNoite - minEntrada);
                    } else {
                        horarios.add(minEntrada);
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro na leitura da entrada: " + entrada);
            }
        }

        for (int i = 0; i < nomes.size(); i++) {
            System.out.println(nomes.get(i) + " - " + horarios.get(i) + " minutos");
        }

        sc.close();
    }
}
