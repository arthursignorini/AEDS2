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
    public String elemento;
    public No esq, dir;
    public int altura;

    No(String elemento) {
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
        this.altura = 1; // Altura inicial é 1 (contando com o próprio nó)
    }
}

class Arvore {
    public No raiz;

    Arvore() {
        raiz = null;
    }

    // PESQUISAR
    public boolean pesquisar(String elemento) {
        StringBuilder caminho = new StringBuilder();
        boolean encontrado = pesquisar(raiz, elemento, caminho);
        System.out.println("=>raiz" + caminho.toString() + (encontrado ? " SIM" : " NAO"));
        return encontrado;
    }

    private boolean pesquisar(No no, String elemento, StringBuilder caminho) {
        if (no == null) {
            return false;
        }
        int comparacao = elemento.compareTo(no.elemento);
        if (comparacao == 0) {
            return true;
        } else if (comparacao < 0) {
            caminho.append(" esq");
            return pesquisar(no.esq, elemento, caminho);
        } else {
            caminho.append(" dir");
            return pesquisar(no.dir, elemento, caminho);
        }
    }

    // INSERIR
    public void inserir(String elemento) {
        raiz = inserir(raiz, elemento);
    }

    private No inserir(No no, String elemento) {
        if (no == null) {
            return new No(elemento);
        }

        int comparacao = elemento.compareTo(no.elemento);
        if (comparacao < 0) {
            no.esq = inserir(no.esq, elemento);
        } else if (comparacao > 0) {
            no.dir = inserir(no.dir, elemento);
        }

        no.altura = 1 + Math.max(altura(no.esq), altura(no.dir));

        int balanceamento = getFatorBalanceamento(no);

        // Rotação à direita
        if (balanceamento > 1 && elemento.compareTo(no.esq.elemento) < 0) {
            return rotDir(no);
        }

        // Rotação à esquerda
        if (balanceamento < -1 && elemento.compareTo(no.dir.elemento) > 0) {
            return rotEsq(no);
        }

        // Rotação dupla (esquerda-direita)
        if (balanceamento > 1 && elemento.compareTo(no.esq.elemento) > 0) {
            no.esq = rotEsq(no.esq);
            return rotDir(no);
        }

        // Rotação dupla (direita-esquerda)
        if (balanceamento < -1 && elemento.compareTo(no.dir.elemento) < 0) {
            no.dir = rotDir(no.dir);
            return rotEsq(no);
        }

        return no;
    }

    // MÉTODOS DE ROTAÇÃO

    private No rotDir(No y) {
        No x = y.esq;
        No T2 = x.dir;

        x.dir = y;
        y.esq = T2;

        y.altura = 1 + Math.max(altura(y.esq), altura(y.dir));
        x.altura = 1 + Math.max(altura(x.esq), altura(x.dir));

        return x; 
    }

    private No rotEsq(No x) {
        No y = x.dir;
        No T2 = y.esq;

        y.esq = x;
        x.dir = T2;

        x.altura = 1 + Math.max(altura(x.esq), altura(x.dir));
        y.altura = 1 + Math.max(altura(y.esq), altura(y.dir));

        return y; 
    }

    private int altura(No no) {
        return (no == null) ? 0 : no.altura;
    }

    private int getFatorBalanceamento(No no) {
        return (no == null) ? 0 : altura(no.esq) - altura(no.dir);
    }
}

public class AVL {
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
        while(!(input=sc.nextLine()).equals("FIM")) {
            int num = Integer.parseInt(input);
            for(Pokemon p : pokemons) {
                if(num == p.getId()) {
                    arvore.inserir(p.getName());
                }
            }
        }

        while(!(input=sc.nextLine()).equals("FIM")) {
            System.out.println(input);
            arvore.pesquisar(input);
        }
    }
}