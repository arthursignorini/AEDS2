import java.util.Scanner;

public class Carro {
    private String marca;
    private String modelo;
    private int ano;
    private String cor;

    public Carro(String marca, String modelo, int ano, String cor) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
    }

    public void ligar() {
        System.out.println("O carro foi ligado.");
    }

    public void desligar() {
        System.out.println("O carro foi desligado.");
    }

    public void exibirInformacoes() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Ano: " + ano);
        System.out.println("Cor: " + cor);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a marca do carro: ");
        String make = scanner.nextLine();
        System.out.print("Digite o modelo do carro: ");
        String model = scanner.nextLine();
        System.out.print("Digite a cor do carro: ");
        String color = scanner.nextLine();
        System.out.print("Digite o ano do carro: ");
        int year = scanner.nextInt();

        Carro meuCarro = new Carro(make, model, year, color);
        meuCarro.exibirInformacoes();
        meuCarro.ligar();
        meuCarro.desligar();
    }
}
