package tps.tp1;

import java.util.Scanner;

public class CiframentoCesar {
    public static String criptografar(String str, int chave) {
        String cifrado = "";
        int size = str.length();

        for (int i = 0; i < size; i++) {
            char caractere = str.charAt(i); 
            int codigo = (int) caractere; 
            if (codigo >= 32 && codigo <= 126) {
               
                codigo = (codigo - 32 + chave) % 95 + 32;
                cifrado += (char) codigo;
            } else {
                cifrado += caractere;
            }
        }
        return cifrado;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int chave = 3;
        String string;
        while(!(string = scanner.nextLine()).equals("FIM")){
                String palavraCriptografada = criptografar(string, chave);
                System.out.println(palavraCriptografada);
        }
        scanner.close();
    }     
}

