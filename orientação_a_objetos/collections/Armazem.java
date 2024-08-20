package collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

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

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Preço: " + preco;
    }
}

public class Armazem {
    private static int nextId = 1; // Static ID generator counter

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Produtos> produtos = new ArrayList<>();
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
            sc.nextLine();  // Consumes the newline character

            switch (x) {
                case 1:
                    System.out.println();
                    int id = nextId++;
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
                    Produtos produtoEncontrado = buscarProdutoPorId(produtos, id);
                    if (produtoEncontrado != null) {
                        System.out.print("Digite o novo nome do produto: ");
                        nome = sc.nextLine();
                        produtoEncontrado.setNome(nome);
                        System.out.print("Digite o novo preco do produto: ");
                        preco = sc.nextDouble();
                        produtoEncontrado.setPreco(preco);
                        System.out.println("Produto atualizado!");
                        System.out.println();
                    } else {
                        System.out.println("Esse produto nao foi encontrado");
                        System.out.println();
                    }
                    break;

                case 3:
                    System.out.println();
                    System.out.println("Lista de produtos:");
                    produtos.sort(Comparator.comparingInt(Produtos::getId)); // Ensure sorted for display
                    for (Produtos p : produtos) {
                        System.out.println(p);
                    }
                    System.out.println();
                    break;

                case 4:
                    System.out.println();
                    System.out.print("Digite o id do produto que voce quer remover: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    Produtos produtoParaRemover = buscarProdutoPorId(produtos, id);
                    if (produtoParaRemover != null) {
                        produtos.remove(produtoParaRemover);
                        atualizarIDs(produtos); // Update IDs after removal
                        System.out.println("Produto removido!");
                    } else {
                        System.out.println("Esse produto não foi encontrado");
                    }
                    System.out.println();
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

    private static Produtos buscarProdutoPorId(List<Produtos> produtos, int id) {
        // Ordenar produtos por ID
        produtos.sort(Comparator.comparingInt(Produtos::getId));
        
        // Buscar produto usando busca binária
        int esq = 0;
        int dir = produtos.size() - 1;

        while (esq <= dir) {
            int meio = esq + (dir - esq) / 2;
            Produtos produto = produtos.get(meio);

            if (produto.getId() == id) {
                return produto;
            } else if (produto.getId() < id) {
                esq = meio + 1;
            } else {
                dir = meio - 1;
            }
        }
        return null;
    }

    private static void atualizarIDs(List<Produtos> produtos) {
        // Sort products by ID
        produtos.sort(Comparator.comparingInt(Produtos::getId));
        for (int i = 0; i < produtos.size(); i++) {
            produtos.get(i).setId(i + 1); // Reset IDs to 1, 2, 3, ...
        }
    }
}
