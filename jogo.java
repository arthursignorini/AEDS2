import java.util.Random;
import java.util.Scanner;

public class jogo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Random random = new Random();
        String randomNum = String.format("%04d", random.nextInt(10000));
        
        System.out.println("BORA JOGAR");


        for (int x = 0; x < 7; x++) {
            int tentativasRestantes = 7 - x;
            System.out.println();
            System.out.println("Você tem " + tentativasRestantes + " tentativa(s) restante(s).");
            System.out.println("========================================");
            System.out.println("Digite um número de 4 dígitos: ");
            String num = sc.nextLine();

            
            while (num.length() != 4) {
                System.out.println("Esse número não é válido. Digite um número de 4 dígitos: ");
                num = sc.nextLine();
            }

            
            int corretosNaPosicao = 0;
            int corretosForaDaPosicao = 0; 

            
            boolean[] usadoNoAleatorio = new boolean[4];
            boolean[] usadoNoInput = new boolean[4];

            
            for (int i = 0; i < 4; i++) {
                if (num.charAt(i) == randomNum.charAt(i)) {
                    corretosNaPosicao++;
                    usadoNoAleatorio[i] = true;
                    usadoNoInput[i] = true;
                }
            }

            
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

            if (corretosNaPosicao == 4) {
                System.out.println("PARABÉNS, VOCÊ ACERTOU TODOS OS NÚMEROS E VENCEU O JOGO!!!");
                System.exit(0); 
            } else {
                System.out.println("Você tem " + corretosNaPosicao + " número(s) na posição correta.");
                System.out.println("Você tem " + corretosForaDaPosicao + " número(s) correto(s) em posição(ões) errada(s).");
            }
        }

        System.out.println("Suas tentativas acabaram! O número correto era: " + randomNum);
        sc.close(); 
    }
}
