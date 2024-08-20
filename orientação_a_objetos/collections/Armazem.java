package collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Produto {
    private int id;
    private String nome;
    private double preco;

    public Produto(int id, String nome, double preco) {
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
    private static int nextId = 1; // Contador de ID estático

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Produto> produtos = new ArrayList<>();
        int opcao = 0;

        while (opcao != 5) {
            System.out.println("MENU DE OPÇÕES");
            System.out.println("1: Adicionar produto");
            System.out.println("2: Editar produto");
            System.out.println("3: Listar produtos");
            System.out.println("4: Remover produto");
            System.out.println("5: Sair do programa");
            System.out.print("Digite a opção desejada: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Consome o caractere de nova linha

            switch (opcao) {
                case 1:
                    int id = nextId++;
                    System.out.print("Digite o nome do produto: ");
                    String nome = sc.nextLine();
                    System.out.print("Digite o preço do produto: ");
                    double preco = sc.nextDouble();

                    Produto produto = new Produto(id, nome, preco);
                    produtos.add(produto);
                    System.out.println("Produto adicionado!");
                    System.out.println();
                    break;

                case 2:
                    System.out.print("Digite o ID do produto que deseja editar: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    Produto produtoEncontrado = buscarProdutoPorId(produtos, id);
                    if (produtoEncontrado != null) {
                        System.out.print("Digite o novo nome do produto: ");
                        nome = sc.nextLine();
                        produtoEncontrado.setNome(nome);
                        System.out.print("Digite o novo preço do produto: ");
                        preco = sc.nextDouble();
                        produtoEncontrado.setPreco(preco);
                        System.out.println("Produto atualizado!");
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    System.out.println();
                    break;

                case 3:
                    System.out.println("Lista de produtos:");
                    bubbleSort(produtos); // Ordena por ID usando bubble sort
                    for (Produto p : produtos) {
                        System.out.println(p);
                    }
                    System.out.println();
                    break;

                case 4:
                    System.out.print("Digite o ID do produto que deseja remover: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    Produto produtoParaRemover = buscarProdutoPorId(produtos, id);
                    if (produtoParaRemover != null) {
                        produtos.remove(produtoParaRemover);
                        atualizarIDs(produtos);
                        System.out.println("Produto removido!");
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    System.out.println();
                    break;

                case 5:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    System.out.println();
            }
        }
        sc.close();
    }

    private static Produto buscarProdutoPorId(List<Produto> produtos, int id) {
        bubbleSort(produtos); // Ordena a lista por ID para garantir a busca binária

        // Busca binária
        int esq = 0;
        int dir = produtos.size() - 1;

        while (esq <= dir) {
            int meio = esq + (dir - esq) / 2;
            Produto produto = produtos.get(meio);

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

    private static void bubbleSort(List<Produto> produtos) {
        int n = produtos.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (produtos.get(j).getId() > produtos.get(j + 1).getId()) {
                    // Troca os produtos
                    Produto temp = produtos.get(j);
                    produtos.set(j, produtos.get(j + 1));
                    produtos.set(j + 1, temp);
                }
            }
        }
    }

    private static void atualizarIDs(List<Produto> produtos) {
        // Atualiza IDs sequencialmente após remoção
        for (int i = 0; i < produtos.size(); i++) {
            produtos.get(i).setId(i + 1);
        }
    }
}
