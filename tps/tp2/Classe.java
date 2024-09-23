import java.util.*;

class Pokemon {
    // atributos
    private int id;
    private int generation;
    private String name;
    private String description;
    private Lista types;
    private Lista abilities;
    private double weight;
    private double height;
    private int captureRate;
    private boolean isLegendary;
    private Date caputreDate;

    // MÉTODOS

    // Contrutor
    public Pokemon(int id, int generation, String name, String description, Lista types, Lista abilities, double weight,
            double height, int captureRate, boolean isLegendary, Date caputreDate) {
        this.id = id;
        this.generation = generation;
        this.name = name;
        this.description = description;
        this.types = types;
        this.abilities = abilities;
        this.weight = weight;
        this.height = height;
        this.captureRate = captureRate;
        this.isLegendary = isLegendary;
        this.caputreDate = caputreDate;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Lista getTypes() {
        return types;
    }

    public void setTypes(Lista types) {
        this.types = types;
    }

    public Lista getAbilities() {
        return abilities;
    }

    public void setAbilities(Lista abilities) {
        this.abilities = abilities;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getCaptureRate() {
        return captureRate;
    }

    public void setCaptureRate(int captureRate) {
        this.captureRate = captureRate;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    public void setLegendary(boolean isLegendary) {
        this.isLegendary = isLegendary;
    }

    public Date getCaputreDate() {
        return caputreDate;
    }

    public void setCaputreDate(Date caputreDate) {
        this.caputreDate = caputreDate;
    }

    // Imprimir
    @Override
    public String toString() {
        return "Pokemon [id=" + id + ", generation=" + generation + ", name=" + name + ", description=" + description
                + ", types=" + types + ", abilities=" + abilities + ", weight=" + weight + ", height=" + height
                + ", captureRate=" + captureRate + ", isLegendary=" + isLegendary + ", caputreDate=" + caputreDate
                + "]";
    }

    // Clone
    public Pokemon clone() {
        Pokemon clone = new Pokemon();

        clone.id = this.id;
        clone.generation = this.generation;
        clone.name = this.name;
        clone.description = this.description;
        clone.types = this.types != null ? this.types.clone() : null; 
        clone.abilities = this.abilities != null ? this.abilities.clone() : null; 
        clone.weight = this.weight;
        clone.height = this.height;
        clone.captureRate = this.captureRate;
        clone.isLegendary = this.isLegendary;
        clone.caputreDate = this.caputreDate != null ? (Date) this.caputreDate.clone() : null; 
        return clone;
    }

    // Leitura
    public void ler () {
        Scanner sc = new Scanner(System.in);

        sc.nextInt();
        sc.nextInt();
        sc.nextLine();
        sc.nextLine();
        // ler lista tipos
        // ler lista habilidades
        sc.nextDouble();
        sc.nextInt();
        sc.nextBoolean();
        // ler date

    }
}

/**
 * Classe
 */
public class Classe {

    public static void main(String[] args) {
        
    }
}