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

public class Counting {
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

            pokemonsOrdenados = countingSort(pokemonsOrdenados, comparacoes, movimentacoes);

            long fim = System.currentTimeMillis(); // Fim do tempo de execução
            long tempoExecucao = fim - inicio; // Tempo em milissegundos

            for (Pokemon pokemon : pokemonsOrdenados) {
                System.out.println(pokemon);
            }

            // Escrever o arquivo de log
            escreverLog(tempoExecucao, comparacoes, movimentacoes);
        }
    }

 // Ordenar utilizando Counting Sort com desempate por nome
 public static ArrayList<Pokemon> countingSort(ArrayList<Pokemon> pokemons, int comparacoes, int movimentacoes) {
    int maxCaptureRate = getMaxCaptureRate(pokemons);
    int[] countArray = new int[maxCaptureRate + 1];
    ArrayList<ArrayList<Pokemon>> buckets = new ArrayList<>(maxCaptureRate + 1);

    // Arrays para armazenar as comparações e movimentações, pois são efetivamente finais
    int[] comparacoesArray = {comparacoes};
    int[] movimentacoesArray = {movimentacoes};

    // Inicializar as listas de buckets para cada valor de captureRate
    for (int i = 0; i <= maxCaptureRate; i++) {
        buckets.add(new ArrayList<>());
    }

    // Preencher os buckets com os Pokémon de acordo com seu captureRate
    for (Pokemon pokemon : pokemons) {
        buckets.get(pokemon.getCaptureRate()).add(pokemon);
    }

    // Ordenar cada bucket individualmente por nome usando a comparação de strings
    for (ArrayList<Pokemon> bucket : buckets) {
        bucket.sort((p1, p2) -> {
            comparacoesArray[0]++; // Contar a comparação
            return p1.getName().compareTo(p2.getName());
        });
    }

    // Reunir os Pokémon ordenados a partir dos buckets
    ArrayList<Pokemon> sortedList = new ArrayList<>();
    for (ArrayList<Pokemon> bucket : buckets) {
        for (Pokemon pokemon : bucket) {
            sortedList.add(pokemon);
            movimentacoesArray[0]++; // Contar a movimentação
        }
    }

    // Atualizar os contadores finais
    comparacoes = comparacoesArray[0];
    movimentacoes = movimentacoesArray[0];

    return sortedList;
}


    // Método para obter o valor máximo do captureRate
    public static int getMaxCaptureRate(ArrayList<Pokemon> pokemons) {
        int max = pokemons.get(0).getCaptureRate();
        for (int i = 1; i < pokemons.size(); i++) {
            if (pokemons.get(i).getCaptureRate() > max) {
                max = pokemons.get(i).getCaptureRate();
            }
        }
        return max;
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
        try (PrintWriter writer = new PrintWriter(new FileWriter("848122_countingsort.txt"))) {
            writer.printf("848122\t%dms\t%d comparacoes\t%d movimentacoes\n", tempoExecucao, comparacoes, movimentacoes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
