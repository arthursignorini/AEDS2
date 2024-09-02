package provas_resolvidas.p1;

public class ex01 {
    public static void main(String[] args) {

        String str = "ola meu bom homem";
        if(isPalindromo(str)) {
            System.out.println("SIM");
        } else {
            System.out.println("NAO");
        }
    }
}

class Exercicio {
    boolean isPalindromo(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}

