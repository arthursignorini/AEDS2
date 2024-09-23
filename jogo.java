import java.util.Random;
import java.util.Scanner;

public class jogo {
    public static void main(String[] args) {

        // Criar um scanner para entrada de dados
        Scanner sc = new Scanner(System.in);

        // Gerar um número aleatório de 4 dígitos
        Random random = new Random();
        String randomNum = String.format("%04d", random.nextInt(10000));
        
        System.out.println("BORA JOGAR");

        // Remova o comentário abaixo para ver o número aleatório durante testes
        // System.out.println(randomNum); 

        for (int x = 0; x < 7; x++) {
            int tentativasRestantes = 7 - x;
            System.out.println();
            System.out.println("Você tem " + tentativasRestantes + " tentativa(s) restante(s).");
            System.out.println("========================================");
            System.out.println("Digite um número de 4 dígitos: ");
            String num = sc.nextLine();

            // Verificar se o número digitado tem 4 dígitos
            while (num.length() != 4) {
                System.out.println("Esse número não é válido. Digite um número de 4 dígitos: ");
                num = sc.nextLine();
            }

            // Comparar o número digitado com o número aleatório
            int corretosNaPosicao = 0; // Números na posição correta
            int corretosForaDaPosicao = 0; // Números corretos, mas em posição errada

            // Array para controlar se um dígito já foi utilizado
            boolean[] usadoNoAleatorio = new boolean[4];
            boolean[] usadoNoInput = new boolean[4];

            // Primeiro, verificar números na posição correta
            for (int i = 0; i < 4; i++) {
                if (num.charAt(i) == randomNum.charAt(i)) {
                    corretosNaPosicao++;
                    usadoNoAleatorio[i] = true;
                    usadoNoInput[i] = true;
                }
            }

            // Depois, verificar números corretos, mas em posições erradas
            for (int i = 0; i < 4; i++) {
                if (!usadoNoInput[i]) { // Se o dígito do input não foi usado
                    for (int j = 0; j < 4; j++) {
                        if (!usadoNoAleatorio[j] && num.charAt(i) == randomNum.charAt(j)) {
                            corretosForaDaPosicao++;
                            usadoNoAleatorio[j] = true;
                            break;
                        }
                    }
                }
            }

            if (corretosNaPosicao == 4) {
                System.out.println("PARABÉNS, VOCÊ ACERTOU TODOS OS NÚMEROS E VENCEU O JOGO!!!");
                System.exit(0); // Finaliza o jogo
            } else {
                System.out.println("Você tem " + corretosNaPosicao + " número(s) na posição correta.");
                System.out.println("Você tem " + corretosForaDaPosicao + " número(s) correto(s) em posição(ões) errada(s).");
            }
        }

        System.out.println("Suas tentativas acabaram! O número correto era: " + randomNum);
        sc.close(); // Fechar o scanner para evitar vazamentos de recurso
    }
}
