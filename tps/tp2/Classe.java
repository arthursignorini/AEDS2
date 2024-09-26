import java.util.*;
import java.util.ArrayList;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


class Pokemon {
    // atributos
    private int id;
    private int generation;
    private String name;
    private String description;
    private ArrayList<String> types;
    private ArrayList<String> abilities;
    private double weight;
    private double height;
    private int captureRate;
    private boolean isLegendary;
    private LocalDate captureDate;

    // MÉTODOS

    // Contrutor

    public Pokemon() {
        this.id = 0;
        this.generation = 0;
        this.name = "";
        this.description = "";
        this.types = new ArrayList<>();
        this.abilities = new ArrayList<>();
        this.weight = 0.0;
        this.height = 0.0;
        this.captureRate = 0;
        this.isLegendary = true;
        this.captureDate = null;
    }

    public Pokemon(int id, int generation, String name, String description, ArrayList<String> types,
            ArrayList<String> abilities, double weight,
            double height, int captureRate, boolean isLegendary, LocalDate caputreDate) {
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
        this.captureDate = captureDate;
    }

    public Pokemon(String[] dados) {
        this.id = Integer.parseInt(dados[0]);
        this.generation = Integer.parseInt(dados[1]);
        this.name = dados[2];
        this.description = dados[3];
        this.types = new ArrayList<>(Arrays.asList(dados[4].split(",")));
        this.abilities = new ArrayList<>(Arrays.asList(dados[5].split(",")));
        this.weight = Double.parseDouble(dados[6]);
        this.height = Double.parseDouble(dados[7]);
        this.captureRate = Integer.parseInt(dados[8]);
        this.isLegendary = Boolean.parseBoolean(dados[9]);
        this.captureDate = LocalDate.parse(dados[10], DateTimeFormatter.ISO_DATE);
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

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types != null ? types : new ArrayList<>();
    }

    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<String> abilities) {
        this.abilities = abilities != null ? abilities : new ArrayList<>();
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

    public LocalDate getCaputreDate() {
        return captureDate;
    }

    public void setCaputreDate(LocalDate captureDate) {
        this.captureDate = captureDate;
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
        clone.types = new ArrayList<>(this.types);
        clone.abilities = new ArrayList<>(this.abilities);
        clone.weight = this.weight;
        clone.height = this.height;
        clone.captureRate = this.captureRate;
        clone.isLegendary = this.isLegendary;
        clone.captureDate = this.captureDate;
        return clone;
    }

    public ArrayList<Pokemon> Ler() {
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        String csvFile = "/tmp/pokemon.csv";
        String linha;

        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            br.readLine();

            while (!(linha = br.readLine()).equals("FIM")) {

                linha = lineFormat(linha);

                Pokemon pokemon = new Pokemon(linha.split(";"));
                pokemons.add(pokemon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pokemons;
    }

    private static String lineFormat(String linha) {
        boolean in_list = false;
        StringBuilder str = new StringBuilder(linha);
        for (int i = 0; i < linha.length(); i++) {
            if (!in_list && linha.charAt(i) == ',') {
                str.setCharAt(i, ';');
            } else if (str.charAt(i) == '"') {
                in_list = !in_list;
            }
        }
        return str.toString();
    }
}

public class Classe {
    public static void main(String[] args) {

    }
}