import java.util.ArrayList;
import java.util.Scanner;

public class Etiquetas_Noel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int qntdIdiomas = sc.nextInt();
        sc.nextLine();
        ArrayList<String> idiomas = new ArrayList<>(qntdIdiomas);
        ArrayList<String> traducoes = new ArrayList<>(qntdIdiomas);

        for(int i=0; i<qntdIdiomas; i++) {
            String idioma = sc.nextLine();
            String traducao = sc.nextLine();
            idiomas.add(idioma);
            traducoes.add(traducao);
        }

        int qntdPessoa = sc.nextInt();
        sc.nextLine();
        for(int i=0; i<qntdPessoa; i++) {
            String nome = sc.nextLine();
            String idiomaFalado = sc.nextLine();
            for(int j=0; j<qntdIdiomas; j++) {
                if(idiomaFalado.equals(idiomas.get(j))) {
                    System.out.println(nome);
                    System.out.println(traducoes.get(j));
                    System.out.println();
                }
            }
        }
    }
}
