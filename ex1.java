import java.util.Locale;
import java.util.Scanner;

public class ex1 {

    public static void main(String[]args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o produto 1: ");
        String p1 = scanner.nextLine();

        System.out.print("Digite o produto 2: ");
        String p2 = scanner.nextLine();

        System.out.print("Digitea sua idade: ");
        int age = scanner.nextInt();

        System.out.print("Digite o seu codigo: ");
        int codigo = scanner.nextInt();

        System.out.print("Digite o preco sem desconto: ");
        double preco = scanner.nextFloat();

        double newpreco;

        if(preco > 100){
            newpreco = preco * 0.8;
        } else {
            newpreco = preco * 1.2;
        }
        Locale.setDefault(Locale.US);
        System.out.printf("O produto 1 é %s e o produto 2 é %s %n",p1,p2);
        System.out.printf("A pessoa tem %d anos e o codigo dela é %d%n", age, codigo);
        System.out.printf("O preco normal é %.2f e o preco novo é %.2f",preco,newpreco);
    }
}