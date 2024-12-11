import java.util.Scanner;

public class TDA_Racional {
    // Método para calcular o máximo divisor comum (MDC)
    public static int mdc(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Método para simplificar uma fração
    public static String simplificar(int numerador, int denominador) {
        int divisor = mdc(Math.abs(numerador), Math.abs(denominador));
        numerador /= divisor;
        denominador /= divisor;
        return numerador + "/" + denominador;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();

        for (int i = 0; i < x; i++) {
            int n1 = sc.nextInt(); 
            sc.next();
            int n2 = sc.nextInt(); 
            String operacao = sc.next(); 
            int n3 = sc.nextInt(); 
            sc.next(); 
            int n4 = sc.nextInt(); 

            int resultadoNumerador = 0;
            int resultadoDenominador = 0;

            switch (operacao) {
                case "+":
                    
                    resultadoNumerador = n1 * n4 + n3 * n2;
                    resultadoDenominador = n2 * n4;
                    break;
                case "-":
                    
                    resultadoNumerador = n1 * n4 - n3 * n2;
                    resultadoDenominador = n2 * n4;
                    break;
                case "*":
                    
                    resultadoNumerador = n1 * n3;
                    resultadoDenominador = n2 * n4;
                    break;
                case "/":
                    
                    resultadoNumerador = n1 * n4;
                    resultadoDenominador = n2 * n3;
                    break;
                default:
                    System.out.println("Operação inválida.");
                    continue;
            }

            System.out.println(resultadoNumerador + "/"+ resultadoDenominador + " = " + simplificar(resultadoNumerador, resultadoDenominador));
        }

        sc.close();
    }
}
