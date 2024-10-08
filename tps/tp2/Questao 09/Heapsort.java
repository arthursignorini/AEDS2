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

    public double getHeight() {
        return height;
    }

    public String getName() {
        return name;
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

public class Heapsort {
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

            ordenar(pokemonsOrdenados, comparacoes, movimentacoes);

            long fim = System.currentTimeMillis(); // Fim do tempo de execução
            long tempoExecucao = fim - inicio; // Tempo em milissegundos

            for (Pokemon pokemon : pokemonsOrdenados) {
                System.out.println(pokemon);
            }

            // Escrever o arquivo de log
            escreverLog(tempoExecucao, comparacoes, movimentacoes);
        }
    }

    // Ordenar utilizando HeapSort
    public static void ordenar(ArrayList<Pokemon> pokemons, int comparacoes, int movimentacoes) {
        int n = pokemons.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(pokemons, n, i, comparacoes, movimentacoes);
        }

        for (int i = n - 1; i > 0; i--) {
            Pokemon temp = pokemons.get(0);
            pokemons.set(0, pokemons.get(i));
            pokemons.set(i, temp);
            movimentacoes++; // Conta a movimentação

            heapify(pokemons, i, 0, comparacoes, movimentacoes);
        }

        // Comparação adicional para ordenar alfabeticamente Pokémon de mesma altura
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                comparacoes++; // Comparação entre alturas
                if (pokemons.get(i).getHeight() == pokemons.get(j).getHeight()) {
                    if (pokemons.get(i).getName().compareTo(pokemons.get(j).getName()) < 0) {
                        Pokemon temporario = pokemons.get(i);
                        pokemons.set(i, pokemons.get(j));
                        pokemons.set(j, temporario);
                        movimentacoes++; // Conta a movimentação
                    }
                }
            }
        }
    }

    // Método heapify com contadores de comparações e movimentações
    private static void heapify(ArrayList<Pokemon> pokemons, int n, int i, int comparacoes, int movimentacoes) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        comparacoes++; // Comparação entre o nó atual e o filho esquerdo
        if (left < n && pokemons.get(left).getHeight() > pokemons.get(largest).getHeight()) {
            largest = left;
        }

        comparacoes++; // Comparação entre o nó atual e o filho direito
        if (right < n && pokemons.get(right).getHeight() > pokemons.get(largest).getHeight()) {
            largest = right;
        }

        if (largest != i) {
            Pokemon swap = pokemons.get(i);
            pokemons.set(i, pokemons.get(largest));
            pokemons.set(largest, swap);
            movimentacoes++; // Conta a movimentação
            heapify(pokemons, n, largest, comparacoes, movimentacoes);
        }
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
        try (PrintWriter writer = new PrintWriter(new FileWriter("848122_heapsort.txt"))) {
            writer.printf("848122\t%dms\t%d comparacoes\t%d movimentacoes\n", tempoExecucao, comparacoes, movimentacoes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
