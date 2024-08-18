public abstract class Animais {
    protected String nome;

    public Animais(String n) {
        nome = n;
    }

    public abstract void fazerSom();
} 

class Cachorro extends Animais {
    public Cachorro (String n) {
        super(n);
    }

    @Override
    public void fazerSom() {
        System.out.println("AU AU AU e o nome do cachorro é " + nome);
    }
}

class Gato extends Animais {
    public Gato (String n) {
        super(n);
    }

    @Override
    public void fazerSom() {
        System.out.println("MIAU MIAU e o nome do gato é " + nome);
    }
}

class Dono {
    public static void main(String[] args) {
        Cachorro c1 = new Cachorro("Jeremias");
        Gato g1 = new Gato("Alisson");

        c1.fazerSom();
        g1.fazerSom();
    }
}