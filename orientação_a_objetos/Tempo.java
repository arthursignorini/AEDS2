// Classe abstrata Sensor
abstract class Sensor {
    protected String nome;

    public Sensor(String nome) {
        this.nome = nome;
    }

    // Método abstrato para gerar o relatório
    public abstract void gerarRelatorio();
}

// Classe SensorTemperatura que estende Sensor
class SensorTemperatura extends Sensor {
    private double temperaturaCelsius;

    public SensorTemperatura(String nome, double temperaturaCelsius) {
        super(nome);
        this.temperaturaCelsius = temperaturaCelsius;
    }

    // Converte a temperatura para Fahrenheit e gera o relatório
    @Override
    public void gerarRelatorio() {
        double temperaturaFahrenheit = (temperaturaCelsius * 9/5) + 32;
        System.out.println("Relatório de " + nome + ": " + temperaturaFahrenheit + "°F");
    }
}

// Classe SensorUmidade que estende Sensor
class SensorUmidade extends Sensor {
    private double[] valoresUmidade;

    public SensorUmidade(String nome, double[] valoresUmidade) {
        super(nome);
        this.valoresUmidade = valoresUmidade;
    }

    // Calcula a média da umidade e gera o relatório
    @Override
    public void gerarRelatorio() {
        double soma = 0.0;
        for (double valor : valoresUmidade) {
            soma += valor;
        }
        double mediaUmidade = soma / valoresUmidade.length;
        System.out.println("Relatório de " + nome + ": Média de " + mediaUmidade + "%");
    }
}

// Classe SensorPressao que estende Sensor
class SensorPressao extends Sensor {
    private double pressaoPascal;

    public SensorPressao(String nome, double pressaoPascal) {
        super(nome);
        this.pressaoPascal = pressaoPascal;
    }

    // Converte a pressão para mmHg e gera o relatório
    @Override
    public void gerarRelatorio() {
        double pressaoMmHg = pressaoPascal / 133.322;
        System.out.println("Relatório de " + nome + ": " + pressaoMmHg + " mmHg");
    }
}

// Classe EstacaoMeteorologica
class EstacaoMeteorologica {
    private Sensor[] sensores;
    private int contadorSensores;

    public EstacaoMeteorologica(int capacidadeMaxima) {
        sensores = new Sensor[capacidadeMaxima];
        contadorSensores = 0;
    }

    // Adiciona um sensor à lista
    public void adicionarSensor(Sensor sensor) {
        if (contadorSensores < sensores.length) {
            sensores[contadorSensores++] = sensor;
        } else {
            System.out.println("Capacidade máxima de sensores atingida!");
        }
    }

    // Gera relatórios de todos os sensores
    public void gerarRelatorios() {
        for (int i = 0; i < contadorSensores; i++) {
            sensores[i].gerarRelatorio();
        }
    }
}

// Classe principal para testar o código
public class Tempo {
    public static void main(String[] args) {
        // Cria uma estação meteorológica com capacidade para 3 sensores
        EstacaoMeteorologica estacao = new EstacaoMeteorologica(3);

        // Adiciona sensores
        estacao.adicionarSensor(new SensorTemperatura("Temperatura", 24.0));
        estacao.adicionarSensor(new SensorUmidade("Umidade", new double[]{55.0, 60.0, 50.9}));
        estacao.adicionarSensor(new SensorPressao("Pressão", 101325.0));

        // Gera os relatórios
        estacao.gerarRelatorios();
    }
}
