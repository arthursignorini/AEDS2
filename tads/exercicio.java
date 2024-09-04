public class exercicio {
    boolean isPalindromo(String str) throws Exception {
        
        lista lista = new lista(str.length());

        for (int i = 0; i < str.length(); i++) {
            lista.inserirFim(str.charAt(i));
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
