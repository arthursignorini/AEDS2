import java.util.Date;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
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

    public Pokemon(String[] infos) throws Exception {
        for (int i = 0; i < infos.length; i++)
            if (infos[i].isEmpty())
                infos[i] = "0";
        this.id = Integer.parseInt(infos[0]);
        this.generation = Integer.parseInt(infos[1]);
        this.name = infos[2];
        this.description = infos[3];
        this.types = new ArrayList<>();
        this.types.add("'" + infos[4] + "'");
        if (!infos[5].equals("0")) {
            this.types.add("'" + infos[5].trim() + "'");
        }
        infos[6] = infos[6].replace("\"", "").replace("[", "").replace("]", "");
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

    public int getCaptureRate() {
        return captureRate;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    // leitura do csv
    public ArrayList<Pokemon> Ler() {
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        String csvFile = "/tmp/pokemon.csv";
        String linha;

        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            br.readLine(); // Ignora cabeçalho

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

public class MergeSort {
    public static void main(String[] args) {
        Pokemon pokemonManager = new Pokemon();
        ArrayList<Pokemon> pokemons = pokemonManager.Ler();
        ArrayList<Pokemon> pokemonsOrdenados = new ArrayList<>();

        if (pokemons.isEmpty()) {
            System.out.println("Nenhum Pokémon encontrado.");
        } else {
            searchPokemonId(pokemons, pokemonsOrdenados);

            // Inicializando contadores
            int comparacoes = 0;
            int movimentacoes = 0;

            long inicio = System.currentTimeMillis(); // Início do tempo de execução

            pokemonsOrdenados = mergeSort(pokemonsOrdenados, comparacoes);

            long fim = System.currentTimeMillis(); // Fim do tempo de execução
            long tempoExecucao = fim - inicio; 

            for (Pokemon pokemon : pokemonsOrdenados) {
                System.out.println(pokemon);
            }

            // Escrever o arquivo de log
            escreverLog(tempoExecucao, comparacoes, movimentacoes);
        }
    }

    // Ordenar utilizando Merge Sort com desempate por tipos e nome
    public static ArrayList<Pokemon> mergeSort(ArrayList<Pokemon> pokemons, int comparacoes) {
        if (pokemons.size() <= 1) {
            return pokemons;
        }
    
        int mid = pokemons.size() / 2;
        ArrayList<Pokemon> left = new ArrayList<>(pokemons.subList(0, mid));
        ArrayList<Pokemon> right = new ArrayList<>(pokemons.subList(mid, pokemons.size()));
    
        return merge(mergeSort(left, comparacoes), mergeSort(right, comparacoes), comparacoes);
    }
    
    // Método para mesclar duas listas ordenadas com contagem de comparações
    private static ArrayList<Pokemon> merge(ArrayList<Pokemon> left, ArrayList<Pokemon> right, int comparacoes) {
        ArrayList<Pokemon> sorted = new ArrayList<>();
        int i = 0, j = 0;
    
        while (i < left.size() && j < right.size()) {
            Pokemon leftPokemon = left.get(i);
            Pokemon rightPokemon = right.get(j);
    
            
            int typeComparison = compareTypes(leftPokemon, rightPokemon);
            comparacoes++; 
    
            if (typeComparison < 0) {
                sorted.add(leftPokemon);
                i++;
            } else if (typeComparison > 0) {
                sorted.add(rightPokemon);
                j++;
            } else {
                // Se os tipos são iguais, desempatar pelo nome
                comparacoes++; 
                if (leftPokemon.getName().compareTo(rightPokemon.getName()) < 0) {
                    sorted.add(leftPokemon);
                    i++;
                } else {
                    sorted.add(rightPokemon);
                    j++;
                }
            }
        }
    
        
        while (i < left.size()) {
            sorted.add(left.get(i));
            i++;
        }
    
        while (j < right.size()) {
            sorted.add(right.get(j));
            j++;
        }
    
        return sorted;
    }

// Método para comparar tipos
private static int compareTypes(Pokemon leftPokemon, Pokemon rightPokemon) {
    String leftType = leftPokemon.getTypes().isEmpty() ? "" : leftPokemon.getTypes().get(0);
    String rightType = rightPokemon.getTypes().isEmpty() ? "" : rightPokemon.getTypes().get(0);
    return leftType.compareTo(rightType);
}

    // Método para ler a entrada e encontrar os Pokémon pelo ID
    public static void searchPokemonId(ArrayList<Pokemon> pokemons, ArrayList<Pokemon> pokemonsOrdenados) {
        Scanner sc = new Scanner(System.in);
        String resp;
        while (!(resp = sc.nextLine()).equals("FIM")) {
            int id = Integer.parseInt(resp);
            for (Pokemon pokemon : pokemons) {
                if (pokemon.getId() == id) {
                    pokemonsOrdenados.add(pokemon);
                }
            }
        }
        sc.close();
    }

    // Escrever o arquivo de log
    public static void escreverLog(long tempoExecucao, int comparacoes, int movimentacoes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("848122_mergesort.txt"))) {
            writer.printf("848122\t%dms\t%d comparacoes\t%d movimentacoes\n", tempoExecucao, comparacoes, movimentacoes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
