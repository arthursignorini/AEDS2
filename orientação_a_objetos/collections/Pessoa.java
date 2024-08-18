package collections;

import java.util.ArrayList;
import java.util.List;

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
    }
}
