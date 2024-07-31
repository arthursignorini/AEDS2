import java.util.Scanner;

class ContaBancaria {
    private String numeroConta;
    private double saldo;

    public ContaBancaria(String numeroConta, double saldo) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        this.saldo += valor;
    }

    public void sacar(double valor) {
        if (valor <= this.saldo) {
            this.saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }

    public void exibirSaldo() {
        System.out.println("Saldo: " + this.saldo);
    }
}

class ContaCorrente extends ContaBancaria {
    private double limite;

    public ContaCorrente(String numeroConta, double saldo, double limite) {
        super(numeroConta, saldo);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public void sacar(double valor) {
        if (valor <= (getSaldo() + limite)) {
            setSaldo(getSaldo() - valor);
        } else {
            System.out.println("Saldo e limite insuficientes!");
        }
    }
}

class ContaPoupanca extends ContaBancaria {
    private double taxaJuros;

    public ContaPoupanca(String numeroConta, double saldo, double taxaJuros) {
        super(numeroConta, saldo);
        this.taxaJuros = taxaJuros;
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public void aplicarJuros() {
        double juros = getSaldo() * (taxaJuros / 100);
        setSaldo(getSaldo() + juros);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ContaCorrente contaCorrente = new ContaCorrente("12345", 1000.0, 500.0);
        ContaPoupanca contaPoupanca = new ContaPoupanca("67890", 2000.0, 1.5);

        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Depositar em Conta Corrente");
            System.out.println("2. Sacar de Conta Corrente");
            System.out.println("3. Exibir saldo da Conta Corrente");
            System.out.println("4. Depositar em Conta Poupança");
            System.out.println("5. Aplicar juros na Conta Poupança");
            System.out.println("6. Exibir saldo da Conta Poupança");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor para depositar na Conta Corrente: ");
                    double valorDepositoCC = scanner.nextDouble();
                    contaCorrente.depositar(valorDepositoCC);
                    break;
                case 2:
                    System.out.print("Digite o valor para sacar da Conta Corrente: ");
                    double valorSaqueCC = scanner.nextDouble();
                    contaCorrente.sacar(valorSaqueCC);
                    break;
                case 3:
                    contaCorrente.exibirSaldo();
                    break;
                case 4:
                    System.out.print("Digite o valor para depositar na Conta Poupança: ");
                    double valorDepositoCP = scanner.nextDouble();
                    contaPoupanca.depositar(valorDepositoCP);
                    break;
                case 5:
                    contaPoupanca.aplicarJuros();
                    break;
                case 6:
                    contaPoupanca.exibirSaldo();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }
}