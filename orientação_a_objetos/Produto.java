public class Produto {
    private int cod;
    private String nome;
    private String categoria;
    private double valor;

    public Produto (int cod, String nome, String categoria, double valor) {
        this.cod = cod;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
    }
    public int getCod(){
        return cod;
    }
    public String getNome(){
        return nome;
    }
    public String getCategoria(){
        return categoria;
    }
    public double getValor(){
        return valor;
    }
    public void setCod(int cod){
        this.cod = cod;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setCod(String categoria){
        this.categoria = categoria;
    }
    public void setCod(double valor){
        this.valor = valor;
    }
    public void info(){
        
    }
}
