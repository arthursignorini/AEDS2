import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {
    private String nome;
    private ArrayList<Livro> livros;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void removerLivro(String isbn) {
        livros.removeIf(livro -> livro.getIsbn().equals(isbn));
    }

    public void listarLivros() {
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca("Biblioteca Municipal");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Quantos livros você deseja adicionar? ");
        int quantidadeLivros = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após o próximoInt()

        // Adicionando livros
        for (int i = 0; i < quantidadeLivros; i++) {
            System.out.println("\nLivro " + (i + 1) + ":");
            System.out.print("Título do livro: ");
            String titulo = scanner.nextLine();

            System.out.print("Autor do livro: ");
            String autor = scanner.nextLine();

            System.out.print("ISBN do livro: ");
            String isbn = scanner.nextLine();

            Livro novoLivro = new Livro(titulo, autor, isbn);
            biblioteca.adicionarLivro(novoLivro);
        }

        System.out.println("\nLivros na biblioteca:");
        biblioteca.listarLivros();

        // Exemplo de remoção de livro
        System.out.println("\nRemover livro (digite ISBN):");
        String isbnRemover = scanner.nextLine();
        biblioteca.removerLivro(isbnRemover);

        System.out.println("\nLivros na biblioteca após remoção:");
        biblioteca.listarLivros();

        scanner.close();
    }
}

class Livro {
    private String titulo;
    private String autor;
    private String isbn;

    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
