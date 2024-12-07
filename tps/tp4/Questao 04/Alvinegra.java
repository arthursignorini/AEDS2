import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

enum Cor {
    VERMELHO, PRETO
}

class NoRN {
    public Pokemon pokemon;
    public NoRN esq;
    public NoRN dir;
    public NoRN pai;
    public Cor cor;

    public NoRN(Pokemon pokemon) {
        this.pokemon = pokemon;
        this.esq = null;
        this.dir = null;
        this.pai = null;
        this.cor = Cor.VERMELHO;
    }
}

class ArvoreRN {
    private NoRN raiz;
    private final NoRN folhaNula;

    public ArvoreRN() {
        folhaNula = new NoRN(null);
        folhaNula.cor = Cor.PRETO;
        this.raiz = folhaNula;
    }

    public void inserir(Pokemon pokemon) {
        NoRN novoNo = new NoRN(pokemon);
        novoNo.esq = folhaNula;
        novoNo.dir = folhaNula;
        raiz = inserirRec(raiz, novoNo);
        balancearInsercao(novoNo);
    }

    private NoRN inserirRec(NoRN atual, NoRN novoNo) {
        if (atual == folhaNula) {
            return novoNo;
        }

        if (novoNo.pokemon.getName().compareTo(atual.pokemon.getName()) < 0) {
            atual.esq = inserirRec(atual.esq, novoNo);
            atual.esq.pai = atual;
        } else if (novoNo.pokemon.getName().compareTo(atual.pokemon.getName()) > 0) {
            atual.dir = inserirRec(atual.dir, novoNo);
            atual.dir.pai = atual;
        }

        return atual;
    }

    private void balancearInsercao(NoRN no) {
        while (no.pai != null && no.pai.cor == Cor.VERMELHO) {
            if (no.pai == no.pai.pai.esq) {
                NoRN tio = no.pai.pai.dir;

                if (tio.cor == Cor.VERMELHO) {
                    no.pai.cor = Cor.PRETO;
                    tio.cor = Cor.PRETO;
                    no.pai.pai.cor = Cor.VERMELHO;
                    no = no.pai.pai;
                } else {
                    if (no == no.pai.dir) {
                        no = no.pai;
                        rotacaoEsquerda(no);
                    }
                    no.pai.cor = Cor.PRETO;
                    no.pai.pai.cor = Cor.VERMELHO;
                    rotacaoDireita(no.pai.pai);
                }
            } else {
                NoRN tio = no.pai.pai.esq;

                if (tio.cor == Cor.VERMELHO) {
                    no.pai.cor = Cor.PRETO;
                    tio.cor = Cor.PRETO;
                    no.pai.pai.cor = Cor.VERMELHO;
                    no = no.pai.pai;
                } else {
                    if (no == no.pai.esq) {
                        no = no.pai;
                        rotacaoDireita(no);
                    }
                    no.pai.cor = Cor.PRETO;
                    no.pai.pai.cor = Cor.VERMELHO;
                    rotacaoEsquerda(no.pai.pai);
                }
            }
        }
        raiz.cor = Cor.PRETO;
    }

    private void rotacaoEsquerda(NoRN no) {
        NoRN novoPai = no.dir;
        no.dir = novoPai.esq;

        if (novoPai.esq != folhaNula) {
            novoPai.esq.pai = no;
        }

        novoPai.pai = no.pai;

        if (no.pai == null) {
            raiz = novoPai;
        } else if (no == no.pai.esq) {
            no.pai.esq = novoPai;
        } else {
            no.pai.dir = novoPai;
        }

        novoPai.esq = no;
        no.pai = novoPai;
    }

    private void rotacaoDireita(NoRN no) {
        NoRN novoPai = no.esq;
        no.esq = novoPai.dir;

        if (novoPai.dir != folhaNula) {
            novoPai.dir.pai = no;
        }

        novoPai.pai = no.pai;

        if (no.pai == null) {
            raiz = novoPai;
        } else if (no == no.pai.dir) {
            no.pai.dir = novoPai;
        } else {
            no.pai.esq = novoPai;
        }

        novoPai.dir = no;
        no.pai = novoPai;
    }

    public void mostrar() {
        mostrarRec(raiz);
    }

    private void mostrarRec(NoRN no) {
        if (no != folhaNula) {
            mostrarRec(no.esq);
            System.out.println(no.pokemon);
            mostrarRec(no.dir);
        }
    }

    public void pesquisar(String nome) {
        StringBuilder caminho = new StringBuilder("raiz");
        boolean encontrado = pesquisarRec(raiz, nome, caminho);
        System.out.println(caminho.toString() + (encontrado ? " SIM" : " NAO"));
    }

    private boolean pesquisarRec(NoRN no, String nome, StringBuilder caminho) {
        if (no == folhaNula) {
            return false;
        }

        if (no.pokemon.getName().equals(nome)) {
            return true;
        } else if (nome.compareTo(no.pokemon.getName()) < 0) {
            caminho.append(" esq");
            return pesquisarRec(no.esq, nome, caminho);
        } else {
            caminho.append(" dir");
            return pesquisarRec(no.dir, nome, caminho);
        }
    }
}

public class Alvinegra {
    public static void main(String[] args) {
        List<Pokemon> pokemons = ReadCSV.readCSV("/tmp/pokemon.csv");

        ArvoreRN AB_Poke = new ArvoreRN();

        Scanner sc = new Scanner(System.in);
        String string_entrada;
        int id_entrada;

        while (!(string_entrada = sc.nextLine()).equals("FIM")) {
            id_entrada = Integer.parseInt(string_entrada);
            Pokemon foundPokemon = PokemonSearch.searchPokemonIdSequential(pokemons, id_entrada);
            if (foundPokemon != null) {
                AB_Poke.inserir(foundPokemon);
            } else {
                System.out.println("Pokemon ID not found: " + id_entrada);
            }
        }

        MyLog.startTimer();

        while (!(string_entrada = sc.nextLine()).equals("FIM")) {
            String nomePoke = string_entrada;
            System.out.println(nomePoke);
            AB_Poke.pesquisar(nomePoke);
        }

        MyLog.endTimer();

        MyLog.createLog("853733", "arvoreBinaria");

    }
}


class Pokemon {

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

    // Construtor padrao para inicializar os atributos
    public Pokemon(){
        this.id = 0;
        this.generation = 0;
        this.name = "";
        this.description = "";
        this.types = new ArrayList<>();
        this.abilities = new ArrayList<>();
        this.weight = 0;
        this.height = 0;
        this.captureRate = 0;
        this.isLegendary = false;
        this.captureDate = new Date();
    }

    // Construtor com parametros para inicializar os atributos
    public Pokemon(int id, int generation, String name, String description, ArrayList<String> types, ArrayList<String> abilities, double weight, double height, int captureRate, boolean isLegendary, Date captureDate) {
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

    public static String formatAbilities(String abilities) {
        return abilities.replace("[", "").replace("]", "").replace("\"", "");
    }

    public Pokemon(String[] linhaCSV ){
        for (int i = 0; i < linhaCSV.length; i++) {
            if (linhaCSV[i].isEmpty()) {
                linhaCSV[i] = null;
            }

            this.id = Integer.parseInt(linhaCSV[0] != null ? linhaCSV[0] : "0");
            this.generation = Integer.parseInt(linhaCSV[1]);
            this.name = linhaCSV[2];
            this.description = linhaCSV[3];

            this.types = new ArrayList<String>();
            // Colocar as aspas para impressao
            this.types.add("'" + linhaCSV[4] + "'");
            if (linhaCSV[5] != null) {
                this.types.add("'" + linhaCSV[5] + "'");
            }

            // Formatar as strings de habilidades
            this.abilities = new ArrayList<String>();
            linhaCSV[6] = formatAbilities(linhaCSV[6]);
            String [] abilities = linhaCSV[6].split(",");
            for (String ability : abilities) {
                this.abilities.add(ability);
            }

            if (linhaCSV[7] == null || linhaCSV[7].isEmpty()) {
                this.weight = 0.0;
            } else {
                this.weight = Double.parseDouble(linhaCSV[7]);
            }

            if (linhaCSV[8] == null || linhaCSV[8].isEmpty()) {
                this.height = 0.0;
            } else {
                this.height = Double.parseDouble(linhaCSV[8]);
            }

            if (linhaCSV[9] == null || linhaCSV[9].isEmpty()) {
                this.captureRate = 0;
            } else {
                this.captureRate = Integer.parseInt(linhaCSV[9]);
            }

            if (linhaCSV[10] == null || linhaCSV[10].isEmpty()) {
                this.isLegendary = false;
            } else {
                this.isLegendary = (linhaCSV[10].equals("1"));

            }

            if (linhaCSV[11] == null || linhaCSV[11].isEmpty()) {
                this.captureDate = null;
            } else {
                SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    this.captureDate = inputDateFormat.parse(linhaCSV[11]);
                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }

        }
    }

    // Metodos de getters e setters
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
        this.types = types;
    }
    public ArrayList<String> getAbilities() {
        return abilities;
    }
    public void setAbilities(ArrayList<String> abilities) {
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
    public Date getCaptureDate() {
        return captureDate;
    }
    public void setCaptureDate(Date captureDate) {
        this.captureDate = captureDate;
    }

    // Metodo clone para copiar o objeto Pokemon
    @Override
    public Pokemon clone() {
        try {
            Pokemon cloned = (Pokemon) super.clone();
            // Clonando arrays e objetos mutáveis para garantir que o clone seja independente
            cloned.types = new ArrayList<>(this.types);
            cloned.abilities = new ArrayList<>(this.abilities);
            cloned.captureDate = (Date) this.captureDate.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            // Como estamos implementando Cloneable, não deve ocorrer essa exceção
            throw new RuntimeException("Erro ao clonar o objeto Pokemon", e);
        }
    }

    // Metodo para imprimir todos atributos da instancia apenas chamando ela no println
    public String toString() {
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String captureDateFormatted = (captureDate != null) ? outputDateFormat.format(captureDate) : "Data não disponível";

        // [#id -> name: description - [types] - [abilities] - weight - height - captureRate - isLegendary - generation] - captureDate].
        return "[#" + id + " -> " + name + ": " + description + " - " + types + " - " + abilities + " - " + weight + "kg - " + height + "m - " + captureRate + "% - " + isLegendary + " - " + generation + " gen] - " + captureDateFormatted;
    }

}


class ReadCSV {

    // Funcao para ler o arquivo CSV e retornar uma lista de Pokemons
    public static List<Pokemon> readCSV(String path) {
        List<Pokemon> pokemons = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader( new FileReader(path) );
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = formatarLinha(linha);
                String[] argumentos_poke = linha.split(";");
                Pokemon pokemon = new Pokemon(argumentos_poke);
                pokemons.add(pokemon);
            }

        } catch (IOException e) {
            pokemons = null;
            throw new RuntimeException(e);
        }

        return pokemons;
    }
    public static String formatarLinha(String linha) {
        char[] formattedArray = linha.toCharArray();
        boolean naLista = false;
        for (int i = 0 ; i < formattedArray.length; i++){
            if (!naLista && formattedArray[i] == ',') {
                formattedArray[i] = ';';
            } else if (formattedArray[i] == '"') {
                naLista = !naLista;
            }
        }
        return new String(formattedArray);
    }

}



class MyLog {

    // Variaveis "globais"
    private static long startTime = 0;
    private static long endTime = 0;
    private static int totalComp = 0;
    private static int totalMove = 0;

    // Função para regular comparações
    static void countComp(int x){
        totalComp += x;
    }

    // Função para regular movimentações
    static void countMove(int x){
        totalMove += x;
    }

    // Função para começar o cronometro
    public static void startTimer() {
        startTime = System.currentTimeMillis();
    }

    // Função para encerrar o cronometro
    public static void endTimer() {
        endTime = System.currentTimeMillis();
    }

    // Função para calcular o tempo gasto
    static long getTime() {
        return endTime - startTime;
    }

    // Função para criar o txt contendo as informações de comparações e tempo
    public static void createLog(final String matricula, final String metodo) {
        try {
            FileWriter logArq = new FileWriter(matricula + "_" + metodo +".txt");
            logArq.write(matricula + "\t" + getTime() + "\t" + totalComp + "\t" + totalMove + "\n");
            logArq.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao criar txt");
        }
    }
}


class PokemonSearch {

    // Funcao estatica que busca um Pokemon pelo ID
    public static Pokemon searchPokemonIdSequential(List<Pokemon> pokemons, int id) {
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getId() == id) {
                return pokemon;
            }
        }
        return null;
    }


}
