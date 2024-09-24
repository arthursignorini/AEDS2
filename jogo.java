import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class jogo {

    private JFrame frame;
    private JTextField inputField;
    private JLabel messageLabel;
    private JLabel tentativaLabel;
    private int tentativasRestantes;
    private String randomNum;

    public jogo() {
        Random random = new Random();
        randomNum = gerarNumeroAleatorioSemRepeticao(random); // Gera o número aleatório
        tentativasRestantes = 7;
        initialize();
    }

    private void initialize() {
        // Configurações da janela principal
        frame = new JFrame("Jogo de Adivinhação de Números");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(5, 1));

        // Adiciona uma mensagem inicial
        JLabel titleLabel = new JLabel("BORA JOGAR! Adivinhe o número de 4 dígitos.");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(titleLabel);

        // Campo para o jogador inserir o número
        inputField = new JTextField();
        inputField.setHorizontalAlignment(JTextField.CENTER);
        frame.getContentPane().add(inputField);
        inputField.setColumns(10);

        // Rótulo para exibir mensagens de status
        messageLabel = new JLabel("Você tem 7 tentativas.");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(messageLabel);

        // Rótulo para exibir quantas tentativas restam
        tentativaLabel = new JLabel("Tentativas restantes: 7");
        tentativaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(tentativaLabel);

        // Botão para o jogador enviar o número
        JButton btnEnviar = new JButton("Enviar");
        frame.getContentPane().add(btnEnviar);

        // Listener para quando o botão de envio for clicado
        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jogar();
            }
        });

        // Exibe a janela
        frame.setVisible(true);
    }

    // Função que trata a lógica do jogo
    private void jogar() {
        String num = inputField.getText();

        // Verifica se o número digitado tem exatamente 4 dígitos
        if (num.length() != 4) {
            messageLabel.setText("Por favor, insira um número de 4 dígitos.");
            return;
        }

        tentativasRestantes--;
        tentativaLabel.setText("Tentativas restantes: " + tentativasRestantes);

        int corretosNaPosicao = 0;
        int corretosForaDaPosicao = 0; 

        boolean[] usadoNoAleatorio = new boolean[4];
        boolean[] usadoNoInput = new boolean[4];

        // Verifica dígitos corretos na posição correta
        for (int i = 0; i < 4; i++) {
            if (num.charAt(i) == randomNum.charAt(i)) {
                corretosNaPosicao++;
                usadoNoAleatorio[i] = true;
                usadoNoInput[i] = true;
            }
        }

        // Verifica dígitos corretos em posições incorretas
        for (int i = 0; i < 4; i++) {
            if (!usadoNoInput[i]) { 
                for (int j = 0; j < 4; j++) {
                    if (!usadoNoAleatorio[j] && num.charAt(i) == randomNum.charAt(j)) {
                        corretosForaDaPosicao++;
                        usadoNoAleatorio[j] = true;
                        break;
                    }
                }
            }
        }

        // Verifica se o jogador ganhou
        if (corretosNaPosicao == 4) {
            messageLabel.setText("PARABÉNS, VOCÊ ACERTOU O NÚMERO!");
            inputField.setEditable(false); // Desabilita o campo de entrada
        } else {
            messageLabel.setText(corretosNaPosicao + " dígito(s) correto(s) na posição certa e " + corretosForaDaPosicao + " correto(s) na posição errada.");
        }

        // Verifica se as tentativas acabaram
        if (tentativasRestantes == 0 && corretosNaPosicao != 4) {
            messageLabel.setText("Fim de jogo! O número correto era: " + randomNum);
            inputField.setEditable(false); // Desabilita o campo de entrada
        }
    }

    // Função que gera um número aleatório de 4 dígitos sem repetição
    public static String gerarNumeroAleatorioSemRepeticao(Random random) {
        StringBuilder numAleatorio = new StringBuilder();
        while (numAleatorio.length() < 4) {
            int digito = random.nextInt(10); // Gera um número entre 0 e 9
            String digitoStr = Integer.toString(digito);
            if (!numAleatorio.toString().contains(digitoStr)) { // Verifica se o dígito já está no número gerado
                numAleatorio.append(digitoStr);
            }
        }
        return numAleatorio.toString();
    }

    public static void main(String[] args) {
        // Inicia a aplicação GUI no thread principal
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    jogo window = new jogo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
