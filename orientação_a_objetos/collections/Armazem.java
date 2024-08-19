package collections;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Produtos {
    private int id;
    private String nome;
    private double preco;

    public Produtos(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setNome(String n) {
        nome = n;
    }

    public void setPreco(double p) {
        preco = p;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Preço: " + preco;
    }
}

public class Armazem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Produtos> produtos = new HashSet<>();
        int x = 0;

        while (x != 5) {
            System.out.println("MENU DE OPCOES");
            System.out.println("1: Adicionar produto");
            System.out.println("2: Editar produto");
            System.out.println("3: Listar produtos");
            System.out.println("4: Remover produto");
            System.out.println("5: Sair do programa");
            System.out.print("Digite qual opcao voce quer: ");
            x = sc.nextInt();
            sc.nextLine();  // Limpa o buffer do scanner

            switch (x) {
                case 1:
                    System.out.println();
                    int id = produtos.size() + 1; 
                    System.out.print("Digite o nome do produto: ");
                    String nome = sc.nextLine();
                    System.out.print("Digite o preco do produto: ");
                    double preco = sc.nextDouble();

                    Produtos produto = new Produtos(id, nome, preco);
                    produtos.add(produto);
                    System.out.println("Produto adicionado!");
                    System.out.println();
                    break;

                case 2:
                    System.out.println();
                    System.out.print("Digite o id do produto que voce quer editar: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    boolean produtoEncontrado = false;
                    for (Produtos p : produtos) {
                        if (p.getId() == id) {
                            produtoEncontrado = true;
                            System.out.print("Digite o novo nome do produto: ");
                            nome = sc.nextLine();
                            p.setNome(nome);
                            System.out.print("Digite o novo preco do produto: ");
                            preco = sc.nextDouble();
                            p.setPreco(preco);
                            System.out.println();
                        }
                    }
                    if (!produtoEncontrado) {
                        System.out.println("Esse produto nao foi encontrado");
                        System.out.println();
                    }
                    break;

                case 3:
                    System.out.println();
                    System.out.println("Lista de produtos:");
                    for (Produtos p : produtos) {
                        System.out.println(p);
                    }
                    System.out.println();
                    break;

                case 4:
                    System.out.println();
                    System.out.print("Digite o id do produto que voce quer remover: ");
                    id = sc.nextInt();
                    boolean resp = false;
                    for (Produtos p : produtos) {
                        if (p.getId() == id) {
                            produtos.remove(p);
                            System.out.println("Essa produto foi removido");
                            resp = true;
                            System.out.println();
                            break;
                        }
                    }
                    if (!resp) {
                        System.out.println("Esse produto não foi encontrado");
                        System.out.println();
                    }

                    break;

                case 5:
                    System.out.println("Saindo do programa...");
                    System.out.println();
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    System.out.println();
            }
        }
        sc.close();
    }
}
