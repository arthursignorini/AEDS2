package CRUD;

import CRUD.modelo.Produto;
import CRUD.servico.ProdutoService;

public class Main {
    public static void main(String[] args) {
        ProdutoService produtoService = new ProdutoService();

        // Adicionar produtos
        produtoService.adicionarProduto("Produto A", 10.0);
        produtoService.adicionarProduto("Produto B", 20.0);

        // Listar produtos
        System.out.println("Lista de Produtos:");
        for (Produto produto : produtoService.listarProdutos()) {
            System.out.println(produto);
        }

        // Buscar produto por ID
        Produto produto = produtoService.buscarProdutoPorId(1);
        System.out.println("Buscar Produto por ID 1: " + produto);

        // Atualizar produto
        boolean atualizado = produtoService.atualizarProduto(1, "Produto A Atualizado", 15.0);
        System.out.println("Produto Atualizado: " + atualizado);

        // Remover produto
        boolean removido = produtoService.removerProduto(2);
        System.out.println("Produto Removido: " + removido);

        // Listar produtos após atualização e remoção
        System.out.println("Lista de Produtos Após Atualização e Remoção:");
        for (Produto p : produtoService.listarProdutos()) {
            System.out.println(p);
        }
    }
}
