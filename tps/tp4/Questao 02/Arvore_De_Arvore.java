import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

class No {
    public int captureRate; 
    public No esq, dir;
    public No2 outraArvore; 

    public No(int captureRate) {
        this.captureRate = captureRate;
        this.esq = this.dir = null;
        this.outraArvore = null; 
    }
}

class Arvore {
    public No raiz;

    public Arvore() {
        raiz = null;
    }

    public void inserir(int captureRate, String nome) {
        raiz = inserir(raiz, captureRate, nome);
    }

    private No inserir(No no, int captureRate, String nome) {
        int chave = captureRate % 15;
        if (no == null) {
            No novo = new No(chave);
            novo.outraArvore = new No2(nome);
            return novo;
        }

        if (chave < no.captureRate) {
            no.esq = inserir(no.esq, captureRate, nome);
        } else if (chave > no.captureRate) {
            no.dir = inserir(no.dir, captureRate, nome);
        } else {
            no.outraArvore = inserirNo2(no.outraArvore, nome);
        }
        return no;
    }

    private No2 inserirNo2(No2 no, String nome) {
        if (no == null) {
            return new No2(nome);
        }
        int comparacao = nome.compareTo(no.elemento);
        if (comparacao < 0) {
            no.esq = inserirNo2(no.esq, nome);
        } else if (comparacao > 0) {
            no.dir = inserirNo2(no.dir, nome);
        }
        return no;
    }

    // PESQUISAR COM PRINT FORMATADO
    public boolean pesquisar(int captureRate, String nome) {
        StringBuilder caminhoCompleto = new StringBuilder();
        No no = pesquisarPrimeiraArvoreComCaminho(raiz, captureRate % 15, caminhoCompleto);

        if (no != null) {
            caminhoCompleto.append(" => Segunda árvore: ");
            boolean encontrado = pesquisarSegundaArvoreComCaminho(no.outraArvore, nome, caminhoCompleto);
            return encontrado;
        }
        return false;
    }

    
    private No pesquisarPrimeiraArvoreComCaminho(No no, int captureRate, StringBuilder caminho) {
        if (no == null) {
            return null;
        }
        caminho.append("raiz ");
        if (captureRate == no.captureRate) {
            return no;
        } else if (captureRate < no.captureRate) {
            caminho.append("ESQ ");
            return pesquisarPrimeiraArvoreComCaminho(no.esq, captureRate, caminho);
        } else {
            caminho.append("DIR ");
            return pesquisarPrimeiraArvoreComCaminho(no.dir, captureRate, caminho);
        }
    }

    
    private boolean pesquisarSegundaArvoreComCaminho(No2 no, String nome, StringBuilder caminho) {
        if (no == null) {
            return false;
        }
        int comparacao = nome.compareTo(no.elemento);
        if (comparacao == 0) {
            return true;
        } else if (comparacao < 0) {
            caminho.append("esq ");
            return pesquisarSegundaArvoreComCaminho(no.esq, nome, caminho);
        } else {
            caminho.append("dir ");
            return pesquisarSegundaArvoreComCaminho(no.dir, nome, caminho);
        }
    }

}

class No2 {
    public String elemento;
    public No2 esq, dir;

    public No2(String elemento) {
        this.elemento = elemento;
        this.esq = this.dir = null;
    }
}

public class Arvore_De_Arvore {
    public static void main(String[] args) {
        Pokemon pokemonManager = new Pokemon();
        Arvore arvore = new Arvore();
        ArrayList<Pokemon> pokemons = pokemonManager.Ler();

        if (pokemons.isEmpty()) {
            System.out.println("Nenhum Pokémon encontrado.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        String input = " ";
        while (!(input = sc.nextLine()).equals("FIM")) {
            int num = Integer.parseInt(input);
            for (Pokemon p : pokemons) {
                if (num == p.getId()) {
                    arvore.inserir(p.getCaptureRate(), p.getName());
                }
            }
        }

        while (!(input = sc.nextLine()).equals("FIM")) {
            Pokemon poke = searchPokemonId(pokemons,input);
            if(poke!=null) {
                System.out.println(" =>" + input);
                arvore.pesquisar(poke.getCaptureRate(), input);
            }
        }
    }
    public static Pokemon searchPokemonId(ArrayList<Pokemon> pokemons, String nome) {
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getName().equals(nome)) {
                return pokemon;
            }
        }
        return null; 
    }
}