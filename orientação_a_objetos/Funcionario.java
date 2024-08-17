import java.util.Scanner;

public abstract class Funcionario {
    private String nome;
    protected double salario; // Modificado para 'protected'

    public Funcionario(String nome, double salario) {
        this.nome = nome;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public abstract double calcularImposto();
}

class FuncionarioRegular extends Funcionario {
    public FuncionarioRegular(String nome, double salario) {
        super(nome, salario);
    }

    @Override
    public double calcularImposto() {
        return salario * 0.10; // Corrigido o cálculo do imposto
    }
}

class FuncionarioTemporario extends Funcionario {
    private int horas_trabalhadas;
    private double taxaHora;

    public FuncionarioTemporario(String nome, int horas_trabalhadas, double taxaHora) {
        super(nome, horas_trabalhadas * taxaHora);
        this.horas_trabalhadas = horas_trabalhadas;
        this.taxaHora = taxaHora;
    }

    @Override
    public double calcularImposto() {
        return salario * 0.05;
    }
}

class FuncionarioTerceirizado extends Funcionario {
    private double bonus;

    public FuncionarioTerceirizado(String nome, double salario, double bonus) {
        super(nome, salario);
        this.bonus = bonus;
    }

    @Override
    public double calcularImposto() {
        return (salario + bonus) * 0.15;
    }
}

class Empresa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Funcionario[] funcionarios = new Funcionario[3];

        funcionarios[0] = new FuncionarioRegular("Arthur", 1500.00);
        funcionarios[1] = new FuncionarioTemporario("Pedro", 8, 100);
        funcionarios[2] = new FuncionarioTerceirizado("Joao", 2300, 350);

        for (Funcionario x : funcionarios) {
            System.out.println("O funcionário " + x.getNome() + " deve pagar de imposto cerca de " + x.calcularImposto());
        }

        sc.close();
    }
}
