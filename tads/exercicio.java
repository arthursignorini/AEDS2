public class exercicio {
    boolean isPalindromo(String s) throws Exception {
        
        lista lista = new lista(s.length());

        for (int i = 0; i < s.length(); i++) {
            lista.inserirFim(s.charAt(i));
        }
        
        boolean resp = true;
        while (lista.n > 1 && resp) {  
            char inicio = lista.removerInicio();  
            char fim = lista.removerFim();  
            if (inicio != fim) {
                resp = false;  
            }
        }
        return resp; 
    }
    public static void main(String[] args) {
        try {
            exercicio ex = new exercicio();
            System.out.println(ex.isPalindromo("arara"));  
            System.out.println(ex.isPalindromo("palindromo"));  
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
