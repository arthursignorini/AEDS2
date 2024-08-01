import java.util.Scanner;

public class Pessoa {
    private String nome;
    private int idade;

    public Pessoa (String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public int getIdade() {
        return idade;
    }
    public void apresentar() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
    }
}

class Aluno extends Pessoa {
    private String curso;

    public Aluno(String nome, int idade, String curso) {
        super(nome, idade);
        this.curso = curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public String getCurso() {
        return curso;
    }

    @Override
    public void apresentar() {
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Curso: " + curso);
    }
}

public class Main {
    public static void main(String[]args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o seu nome: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o seu curso: ");
        String curso = scanner.nextLine();
        System.out.println("Digite a sua idade: ");
        int idade = scanner.nextInt();
        Aluno a1 = new Aluno(nome, idade, curso);
        Pessoa p1 = new Pessoa(nome, idade);
        a1.apresentar();
        p1.apresentar();
    }
}
