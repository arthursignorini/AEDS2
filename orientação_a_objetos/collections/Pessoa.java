package collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pessoa {
    private int idade;
    private String nome;

    public Pessoa(int i, String n) {
        idade = i;
        nome = n;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setNome(String n) {
        nome = n;
    }

    public void setIdade(int i) {
        idade = i;
    }
}

class Aluno  {
    private int idade;
    private String nome;

    public Aluno(int i, String n) {
        idade = i;
        nome = n;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setNome(String n) {
        nome = n;
    }

    public void setIdade(int i) {
        idade = i;
    }
}

class Main {
    public static void main(String[] args) {
        List<Pessoa> pessoas = new ArrayList<>(); // faz uma lista com vários objetos Pessoa
        pessoas.add(new Pessoa(18, "Arthur")); // ADD é um metodo para adicionar objetos na lista
        Pessoa a = pessoas.get(0); // tá selecionando a primeira pessoa dessa lista
        pessoas.remove(a); // remove esse especifico objeto que foi criado anteriormente 
        pessoas.remove(0); // remove a pessoa desse index

        for(Pessoa pessoa : pessoas) { // interagindo sobre todas as pessoas
             System.out.println(pessoa.getNome());
        }
        // No list voce poderia adicionar o mesmo objeto 2 vezes e assim teria duas referencias para ele, mas no Set isso não acontece

        Set<Aluno> alunos = new HashSet<>(); // Nesse metodo nao vai poder adicionar o mesmo objeto
        Aluno a1 = new Aluno(19, "Pedro");
        alunos.add(a1);

        for(Aluno aluno : alunos) {
            System.out.println(aluno.getNome());
        }

        boolean adicionou = alunos.add(new Aluno(19, "Pedro")); // o metodo add retorna true se deu certo e nao tem o mesmo objeto já adicionado e false se tiver dado errado
        if(adicionou) {
            System.out.println("Adicionou");
        } else {
            System.out.println("Não adicionou");
        }
    }
}
