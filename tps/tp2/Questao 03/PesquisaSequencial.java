import java.util.Date;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
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

    // Construtores, Getters e Setters, Clone, etc.
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
        this.isLegendary = false;
        this.captureDate = null;
    }

    // Construtor para aceitar dados do CSV
    public Pokemon(String[] infos) throws Exception {
        for (int i = 0; i < infos.length; i++) {
            if (infos[i].isEmpty()) {
                infos[i] = "0";
            }
        }

        this.id = Integer.parseInt(infos[0]);
        this.generation = Integer.parseInt(infos[1]);
        this.name = infos[2];
        this.description = infos[3];
        this.types = new ArrayList<>();
        infos[4] = infos[4].replace("'", "");
        this.types.add(infos[4]);
        if (!infos[5].equals("0")) {
            infos[5] = infos[5].replace("'", "");
            this.types.add(infos[5]);
        }
        infos[6] = infos[6].replace("\"", "").replace("[", "").replace("]", "");
        String[] tmp = infos[6].split(",");
        this.abilities = new ArrayList<>();
        for (String s : tmp) {
            abilities.add(s.trim());
        }
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

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Função para formatar a string
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

    // Método para leitura do CSV, agora filtrando pelos IDs
    public ArrayList<Pokemon> Ler(ArrayList<Integer> idsFiltrados) {
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        String csvFile = "/tmp/pokemon.csv";
        String linha;

        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            br.readLine(); // Ignorar cabeçalho

            while ((linha = br.readLine()) != null) {
                if (linha.equals("FIM")) {
                    break;
                }

                linha = formatar(linha);

                try {
                    Pokemon pokemon = new Pokemon(linha.split(";"));
                    if (idsFiltrados.contains(pokemon.getId())) {
                        pokemons.add(pokemon);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pokemons;
    }

    // Imprimir informações do Pokémon
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
}

public class PesquisaSequencial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long startTime = System.nanoTime(); // Iniciar tempo de execução
        int numComparacoes = 0;

        // Leitura dos IDs
        ArrayList<Integer> ids = new ArrayList<>();
        String input;
        while (!(input = sc.nextLine()).equals("FIM")) {
            ids.add(Integer.parseInt(input));
        }

        // Criando uma instância para ler os pokémons
        Pokemon pokemonManager = new Pokemon();
        ArrayList<Pokemon> pokemons = pokemonManager.Ler(ids);

        if (pokemons.isEmpty()) {
            System.out.println("Nenhum Pokémon encontrado.");
        } else {
            // Procurar pelos nomes fornecidos
            if (!searchPokemonNome(pokemons, sc, numComparacoes)) {
                System.out.println("NAO");
            }
        }

        long endTime = System.nanoTime(); // Fim do tempo de execução
        long executionTime = (endTime - startTime) / 1_000_000; // Convertendo para milissegundos

        // Criar o arquivo de log
        try (PrintWriter writer = new PrintWriter("848122_sequencial.txt")) {
            writer.printf("848122\t%dms\t%d comparações\n", executionTime, numComparacoes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }

    // aqui está lendo a entrada e achando o pokemon
    public static boolean searchPokemonNome(ArrayList<Pokemon> pokemons, Scanner sc, int numComparacoes) {
        String nomePokemon;
        boolean foundAny = false;
        while (!(nomePokemon = sc.nextLine()).equals("FIM")) {
            boolean found = false;
            for (Pokemon pokemon : pokemons) {
                numComparacoes++; // Incrementa o número de comparações
                if (nomePokemon.equalsIgnoreCase(pokemon.getName())) {
                    System.out.println("SIM");
                    found = true;
                    foundAny = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("NAO");
            }
        }
        return foundAny;
    }
}
