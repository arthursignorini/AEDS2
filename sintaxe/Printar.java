// FORMAS DE PRINTAR EM JAVA

import java.util.Locale;

public class Printar {
    public static void main(String[] args) {
        double x = 10.35784;
        System.out.println(x); // printa com uma nova linha //
        System.out.printf("%.2f%n",x); // não printa com nova linhas mas formata //
        System.out.printf("%.4f%n",x);
        Locale.setDefault(Locale.US);
        System.out.printf("%.2f%n",x); // dessa forma vai ser usado o . //
        System.out.printf("%.4f%n",x);

        System.out.println("RESULTADO: " + x + " metros");
        System.out.printf("RESULTADO: %.2f metros %n",x);

        String nome = "Arthur";
        int idade = 18;
        double renda = 1800.56;
        System.out.printf("O %s tem %d anos e possui uma renda de %.2f", nome, idade,renda);
    }
}
