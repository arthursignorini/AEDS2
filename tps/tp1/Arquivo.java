package tps.tp1;

import java.io.RandomAccessFile;
import java.util.Scanner;

public class Arquivo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // Número de valores a serem lidos

        try {
            // Abrir o arquivo para leitura e escrita
            RandomAccessFile raf = new RandomAccessFile("Questao08.txt", "rw");

            // Escrever os números reais no arquivo
            for (int i = 0; i < n; i++) {
                double num = sc.nextDouble();
                raf.writeDouble(num);
            }

            // Agora vamos ler os números de trás para frente
            // Tamanho de um double em bytes
            long doubleSize = 8;

            // Ir para o último número no arquivo
            for (int i = n - 1; i >= 0; i--) {
                raf.seek(i * doubleSize);  // Posicionar o ponteiro no início do número i
                double num = raf.readDouble();
                if(isInteiro(num)){
                    System.out.println((int)num);
                } else {
                    System.out.println(num);
                }
            }

            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }

    public static boolean isInteiro(double num) {
        if(num == (int)num){
            return true;
        } else {
            return false;
        }
    }
}
