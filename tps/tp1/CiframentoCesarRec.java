import java.util.Scanner;

public class CiframentoCesarRec {
    public static String criptografar(String str, int chave, int i) {
        if (i == str.length()) {
            return "";
        }
        
        char caractere = str.charAt(i); 
        int codigo = (int) caractere; 
        
        if (codigo >= 32 && codigo <= 126) {
            codigo = (codigo - 32 + chave) % 95 + 32;
            return (char) codigo + criptografar(str, chave, i + 1);
        } else {
            return caractere + criptografar(str, chave, i + 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int chave = 3;
        String string;
        
        while(!(string = scanner.nextLine()).equals("FIM")){
            String palavraCriptografada = criptografar(string, chave, 0);
            System.out.println(palavraCriptografada);
        }
        
        scanner.close();
    }     
}
