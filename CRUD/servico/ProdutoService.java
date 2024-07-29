package servico;

import CRUD.modelo.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoService {
    private List<Main> produtos = new ArrayList<>();
    private int currentId = 1;

    // Create
    public void adicionarProduto(String nome, double preco) {
        Main produto = new Main(currentId++, nome, preco);
        produtos.add(produto);
    }

    // Read
    public List<Main> listarProdutos() {
        return new ArrayList<>(produtos);
    }

    public Main buscarProdutoPorId(int id) {
        return produtos.stream()
                .filter(produto -> produto.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Update
    public boolean atualizarProduto(int id, String nome, double preco) {
        Optional<Main> produtoOpt = produtos.stream()
                .filter(produto -> produto.getId() == id)
                .findFirst();

        if (produtoOpt.isPresent()) {
            Main produto = produtoOpt.get();
            produto.setNome(nome);
            produto.setPreco(preco);
            return true;
        }
        return false;
    }

    // Delete
    public boolean removerProduto(int id) {
        return produtos.removeIf(produto -> produto.getId() == id);
    }
}
