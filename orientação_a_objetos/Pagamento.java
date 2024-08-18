public abstract class Pagamento {
    public abstract void processarPagamento(double valor);
}

class CartaoCredito extends Pagamento {
    private String nomeTitular;

    public CartaoCredito(String nome) {
        nomeTitular = nome;
    }

    @Override
    public void processarPagamento(double valor) {
        System.out.println("Pagamento efetuado no valor de R$ " + valor + " no cartão de crédito de " + nomeTitular);
    }
}

class PayPal extends Pagamento {
    private String email;

    public PayPal(String e) {
        email = e;
    }

    @Override
    public void processarPagamento(double valor) {
        System.out.println("Pagamento efetuado no valor de R$ " + valor + " pelo PayPal no email: " + email);
    }
}

class SistemaPagamento {
    public void realizarPagamento(Pagamento pagamento, double valor) {
        pagamento.processarPagamento(valor);
    }
}

class Efetuar {
    public static void main(String[] args) {
        CartaoCredito cartao = new CartaoCredito("Arthur");
        PayPal paypal = new PayPal("arthursigmiranda@gmail.com");

        SistemaPagamento sistema = new SistemaPagamento();
        sistema.realizarPagamento(cartao, 150.00); // Valor do pagamento com cartão
        sistema.realizarPagamento(paypal, 75.00);  // Valor do pagamento com PayPal
    }
}
