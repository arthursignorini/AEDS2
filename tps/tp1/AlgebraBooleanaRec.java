package tps.tp1;

import java.util.Scanner;

public class AlgebraBooleanaRec {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        String entrada = "";

        do {
            entrada = scanner.nextLine(); 
            if (!isEquals(entrada, "0")) {
                if (solveEquation(entrada)) {
                    System.out.println("1"); 
                } else {
                    System.out.println("0"); 
                }
            }
        } while (!isEquals(entrada, "0"));

        scanner.close(); 
    }

    public static String treatment(String input) {
        String expressao = "";
        int n = 0;

        n = input.charAt(0) - 48; // capturar quantos bits serão utilizados

        expressao = removeBlank(input); // remover espaços em branco
        expressao = replaceAll("and", 'a', expressao); // trocar "and" por 'a'
        expressao = replaceAll("or", 'o', expressao); // trocar "or" por 'o'
        expressao = replaceAll("not", 'n', expressao); // trocar "not" por 'n'

        // trocar 'A', 'B' e 'C' por seus respectivos bits
        for (int x = 0; x < n; x = x + 1) {
            char c = (char) ('A' + x);
            char newC = expressao.charAt(x + 1);
            expressao = replaceAll(c, newC, expressao);
        }

        // pegar apenas a expressão lógica
        expressao = subString(expressao, n + 1, expressao.length());

        return (expressao);
    }

    public static String solveExp(String input) {
        int inputLen = input.length();
        char operation = input.charAt(0);
        boolean flag = true;
        switch (operation) {
            case 'a':
                flag = true;
                for (int y = 2; y < inputLen && flag; y = y + 1) {
                    if (input.charAt(y) == '0') {
                        flag = false;
                        return ("0");
                    }
                }
                return ("1");
            case 'o':
                flag = false;
                for (int y = 2; y < inputLen && !flag; y = y + 1) {
                    if (input.charAt(y) == '1') {
                        flag = true;
                        return ("1");
                    }
                }
                return ("0");
            case 'n':
                if (input.charAt(2) == '0') {
                    return ("1");
                } else {
                    return ("0");
                }
            default:
                return ("");
        }
    }

    public static boolean solveEquation(String input) {
        String expressao = treatment(input);
        return (solveEquationRec(expressao));
    }

    public static boolean solveEquationRec(String input) {
        if (input.contains("(")) {
            int start = input.lastIndexOf('(');
            int end = input.indexOf(')', start);

            String subExp = subString(input, start - 1, end + 1);
            String resultString = solveExp(subExp);

            input = subString(input, 0, start - 1) +
                    resultString +
                    subString(input, end + 1, input.length());

            return (solveEquationRec(input));
        } else {
            return (isEquals(input, "1"));
        }
    }

    public static boolean isEquals(String obj1, String obj2) {
        boolean result = true;
        if (obj1.length() != obj2.length()) {
            result = false;
        } else {
            for (int x = 0; x < obj1.length() && result; x = x + 1) {
                if (obj1.charAt(x) != obj2.charAt(x)) {
                    result = false;
                }
            }
        }
        return (result);
    }

    public static String subString(String s, int start, int end) {
        String result = "";
        for (int x = start; x < end && x < s.length(); x = x + 1) {
            result += s.charAt(x);
        }
        return (result);
    }

    public static String removeBlank(String input) {
        String result = "";
        for (int x = 0; x < input.length(); x = x + 1) {
            if (input.charAt(x) != ' ') {
                result = result + input.charAt(x);
            }
        }
        return (result);
    }

    public static String replaceAll(Object base, char newChar, String input) {
        String result = "";
        int inputLen = input.length();
        String baseString = base.toString();
        int baseLen = baseString.length();
        for (int x = 0; x < inputLen; x = x + 1) {
            boolean ok = x < inputLen - baseLen + 1;
            boolean match = isEquals(subString(input, x, x + baseLen), baseString);
            if (ok && match) {
                result += newChar;
                x += baseLen - 1;
            } else {
                result += input.charAt(x);
            }
        }
        return (result);
    }
}
