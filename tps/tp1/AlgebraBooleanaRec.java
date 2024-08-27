package tps.tp1;

import java.util.Scanner;

public class AlgebraBooleanaRec {

    // Converte um caractere numérico para um número inteiro
    public static int parseInt(char str) {
        return str - '0';
    }

    // Converte um caractere '0' ou '1' para um booleano
    public static boolean parseBoolean(char str) {
        return str == '1';
    }

    // Extrai uma parte de uma string
    public static String substring(String str, int start, int end) {
        return str.substring(start, end);
    }

    // Substitui uma parte da string
    public static String replace(String str, int start, int end, String subReplace) {
        return str.substring(0, start) + subReplace + str.substring(end);
    }

    // Extrai os argumentos de uma expressão lógica
    public static char[] getArgs(String str, int index) {
        int count = 0;
        // esse primeiro conta quantos argumentos lógicos tem dentro do parenteses
        for (int i = index + 1; str.charAt(i) != ')'; i++) {
            if (str.charAt(i) == '0' || str.charAt(i) == '1') {
                count++;
            }
        }
        // nesse loop ele extrai os argumentos e os armazena dentro de um array
        char[] args = new char[count];
        int j = 0;
        for (int i = index + 1; str.charAt(i) != ')'; i++) {
            if (str.charAt(i) == '0' || str.charAt(i) == '1') {
                args[j++] = str.charAt(i);
            }
        }

        return args;
    }

    // Operação NOT
    public static char not(char arg) {
        return arg == '0' ? '1' : '0';
    }

    // Operação AND
    public static char and(char[] args) {
        for (char arg : args) {
            if (arg == '0') return '0';
        }
        return '1';
    }

    // Operação OR
    public static char or(char[] args) {
        for (char arg : args) {
            if (arg == '1') return '1';
        }
        return '0';
    }

    // Resolve a expressão lógica - substitui partes da equação pelos seus resultados
    public static String solveEquation(String str) throws Exception {
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == '(') {
                char op = str.charAt(i - 1);
                char[] args = getArgs(str, i);
                char result;
                switch (op) { // vai ler a ultima letra das palavras
                    case 'd': // AND
                        result = and(args);
                        str = replace(str, i - 3, str.indexOf(')', i) + 1, Character.toString(result));
                        break;
                    case 't': // NOT
                        result = not(args[0]);
                        str = replace(str, i - 3, i + 3, Character.toString(result));
                        break;
                    case 'r': // OR
                        result = or(args);
                        str = replace(str, i - 2, str.indexOf(')', i) + 1, Character.toString(result));
                        break;
                    default:
                        throw new Exception("Operação desconhecida.");
                }
            }
        }
        return str;
    }

    // Função principal
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String str;

        while (!(str = sc.nextLine()).equals("0")) {
            int qnt = parseInt(str.charAt(0));
            boolean[] values = new boolean[qnt];

            // Armazena os valores das variáveis
            for (int i = 0; i < qnt; i++) {
                values[i] = parseBoolean(str.charAt(2 + 2 * i));
            }

            // Substitui variáveis na expressão
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == 'A') {
                    str = replace(str, i, i + 1, values[0] ? "1" : "0");
                } else if (str.charAt(i) == 'B') {
                    str = replace(str, i, i + 1, values[1] ? "1" : "0");
                } else if (str.charAt(i) == 'C') {
                    str = replace(str, i, i + 1, values[2] ? "1" : "0");
                }
            }

            // Resolve a expressão e imprime o resultado
            System.out.println(solveEquation(str));
        }

        sc.close();
    }
}
