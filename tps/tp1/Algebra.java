package tps.tp1;

import java.util.Scanner;

public class Algebra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println(evaluateExpression(line) ? "SIM" : "NÃO");
        }
        sc.close();
    }

    public static boolean evaluateExpression(String input) {
        // Divida a linha em partes: número de entradas e a expressão booleana
        String[] parts = input.split(" ", 2);
        int n = Integer.parseInt(parts[0]); // Número de entradas
        String[] valuesAndExpression = parts[1].split(" ", n + 1);

        // Extraia os valores binários das entradas
        boolean[] values = new boolean[n];
        for (int i = 0; i < n; i++) {
            values[i] = valuesAndExpression[i].equals("1");
        }

        // Substitua as variáveis A, B, C, etc., pelos valores
        String expression = valuesAndExpression[n];
        for (int i = 0; i < n; i++) {
            char variable = (char) ('A' + i);
            expression = expression.replaceAll(String.valueOf(variable), String.valueOf(values[i]));
        }

        // Avalie a expressão booleana
        return evalBooleanExpression(expression);
    }

    // Método para avaliar a expressão booleana
    public static boolean evalBooleanExpression(String expression) {
        // Substitua as operações lógicas por operadores Java
        expression = expression.replaceAll("and", "&&");
        expression = expression.replaceAll("or", "||");
        expression = expression.replaceAll("not", "!");
        expression = expression.replaceAll(",", "");

        // Avalie a expressão usando ScriptEngineManager
        try {
            return (Boolean) new javax.script.ScriptEngineManager().getEngineByName("JavaScript").eval(expression);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
