import java.util.Date;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Scanner;

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
    private Date captureDate;

    // MÉTODOS

    // Construtor

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
            ArrayList<String> abilities, double weight, double height, int captureRate,
            boolean isLegendary, Date captureDate) {
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

    public Pokemon(String[] infos) throws Exception {
        for (int i = 0; i < infos.length; i++)
            if (infos[i].isEmpty())
                infos[i] = "0";
        this.id = Integer.parseInt(infos[0]);
        this.generation = Integer.parseInt(infos[1]);
        this.name = infos[2];
        this.description = infos[3];
        this.types = new ArrayList<>();
        infos[4] = "'" + infos[4] + "'";
        this.types.add(infos[4]);
        if (!infos[5].equals("0")) {
            infos[5] = "'" + infos[5].trim() + "'";
            this.types.add(infos[5]);
        }
        infos[6] = infos[6].replace("\"", "");
        infos[6] = infos[6].replace("[", "");
        infos[6] = infos[6].replace("]", "");
        String[] tmp = infos[6].split(",");
        this.abilities = new ArrayList<>();
        for (String s : tmp)
            abilities.add(s.trim());
        this.weight = Double.parseDouble(infos[7]);
        this.height = Double.parseDouble(infos[8]);
        this.captureRate = Integer.parseInt(infos[9]);
        this.isLegendary = infos[10].equals("1");
        if (infos[11].isEmpty()) {
            this.captureDate = null;
        } else {
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            this.captureDate = inputDateFormat.parse(infos[11]);
        }
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

    public Date getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(Date captureDate) {
        this.captureDate = captureDate;
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

    // leitura do csv
    public ArrayList<Pokemon> Ler() {
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        String csvFile = "/tmp/pokemon.csv";
        String linha;

        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            br.readLine();

            while ((linha = br.readLine()) != null) {
                if (linha.equals("FIM")) {
                    break;
                }

                linha = formatar(linha);

                Pokemon pokemon = new Pokemon(linha.split(";"));
                pokemons.add(pokemon);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pokemons;
    }

    // imprimir
    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = (captureDate != null) ? formatter.format(captureDate) : "Data não disponível";
        return "[#" + id + " -> " + name + ": " + description +
                " - " + types + " - " + abilities +
                " - " + weight + "kg - " + height + "m - " +
                captureRate + "% - " + isLegendary +
                " - " + generation + " gen] - " + formattedDate;
    }

    // aqui a string está sendo tratada
    private static String formatar(String linha) {
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

public class PilhaFlex {
    public static void main(String[] args) {
        Pokemon pokemonManager = new Pokemon();
        ListaFlex lista = new ListaFlex();
        ArrayList<Pokemon> removidos = new ArrayList<>();
        
        ArrayList<Pokemon> pokemons = pokemonManager.Ler();

        

        if (pokemons.isEmpty()) {
            System.out.println("Nenhum Pokémon encontrado.");
            return;
        }
        Scanner sc = new Scanner(System.in);

        String input = " ";
        while(!(input=sc.nextLine()).equals("FIM")) {
            int num = Integer.parseInt(input);
            for(Pokemon p : pokemons) {
                if(num == p.getId()) {
                    lista.inserirInicio(p);
                }
            }
        }
        
        int tam = sc.nextInt(); 
        
        for(int i = 0; i < tam; i++) {
            String comando = sc.next();
            int id;
            Pokemon pokemon;

            switch (comando) {
                case "I":
                    if (sc.hasNextInt()) {
                        id = sc.nextInt();
                        pokemon = searchPokemonId(pokemons, id);
                        if (pokemon != null) {
                            lista.inserirInicio(pokemon);
                        }
                    }
                    break;

                case "R":
                    Pokemon pokeRemovedInicio = lista.removerInicio();
                    if (pokeRemovedInicio != null) {
                        removidos.add(pokeRemovedInicio);
                    }
                    break;
            }
        }

        for(Pokemon p : removidos) {
            System.out.println("(R) " + p.getName());
        }

        lista.mostrar();
    }

    public static Pokemon searchPokemonId(ArrayList<Pokemon> pokemons, int id) {
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getId() == id) {
                return pokemon;
            }
        }
        return null; 
    }
}

class Celula {
    Pokemon pokemon;
    Celula prox;

    Celula() {
        this.pokemon = null;
        this.prox = null;
    }

    Celula(Pokemon x) {
        this.pokemon = x;
        this.prox = null;
    }
}

class ListaFlex {
    Celula primeiro;
    Celula ultimo;

    ListaFlex() {
        primeiro = ultimo = new Celula();
    }


    void inserirInicio(Pokemon x) {
        Celula temp = new Celula(x);
        temp.prox = primeiro.prox;
        primeiro.prox = temp;
        if (primeiro.prox == ultimo) {
            ultimo = temp;
        }
        temp = null;
    }

    Pokemon removerInicio() { 
        if (primeiro.prox == null) { 
            return null; 
        }
        Celula temp = primeiro.prox;
        primeiro.prox = primeiro.prox.prox; 
        Pokemon pokemon = temp.pokemon;
        temp.prox = null; 
        return pokemon; 
    }

    void mostrar() {
        mostrarRecursivo(primeiro.prox); 
    }
    
    private void mostrarRecursivo(Celula celula) {
        if (celula != null) {
            mostrarRecursivo(celula.prox); 
            System.out.println("[" + (tamanho() - celulaIndex(celula) + 40) + "] " + celula.pokemon); // Imprimir após a chamada recursiva
        }
    }
    
    private int celulaIndex(Celula celula) {
        int index = 0;
        for (Celula i = primeiro.prox; i != celula; i = i.prox) {
            index++;
        }
        return index;
    }

    int tamanho() {
        int tam = 0;
        for (Celula i = primeiro; i != ultimo; i = i.prox, tam++)
            ;
        return tam;
    }
}
