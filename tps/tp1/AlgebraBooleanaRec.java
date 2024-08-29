import java.util.Scanner;

public class AlgebraBooleanaRec 
{
    /**
     *  Funcao principal.
     *  @param args
     */
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String entrada = "";

        do 
        {
            entrada = scanner.nextLine();
            if(!isEquals(entrada, "0"))
            {
                if(solveEquation(entrada))
                {
                    System.out.println("1");
                }
                else
                {
                    System.out.println("0");
                } // end if
            } // end if
        } while(!isEquals(entrada, "0")); // end do while
        
        scanner.close();
    } // end main ( )

    /**
     *  Funcao para tratar a expressao para fazer a algebra booleana.
     *  @param input - String.
     *  @return String tratada.
     */
    public static String treatment(String input)
    {
        String expressao = "";
        int n = 0;

        n = input.charAt(0) - 48; // capturar quantos bits serao utilizados

        expressao = removeBlank(input);                              // remover espacos em branco
        expressao = replaceAll("and", 'a', expressao);  // trocar "and" por 'a'
        expressao = replaceAll("or" , 'o', expressao); // trocar "or" por 'o'
        expressao = replaceAll("not", 'n', expressao);  // trocar "not" por 'n'

        // trocar 'A', 'B' e 'C' por seus respectivos bits
        for(int x = 0; x < n; x = x + 1)
        {
            char c = (char)('A' + x);
            char newC = expressao.charAt(x + 1); 
            expressao = replaceAll(c, newC, expressao);
        } // end for
        
        // pegar apenas a expressao logica
        expressao = subString(expressao, n + 1, expressao.length());

        return expressao;
    } // end algebraBooleana ( )

    /**
     *  Funcao para identificar e resolver a expressao.
     *  @param input - String.
     *  @return String: expressao resolvida.
     */
    public static String solveExp(String input)
    {
        int inputLen = input.length();
        char operation = input.charAt(0);
        boolean flag = true;
        switch(operation) 
        {
            case 'a':
                flag = true;
                for(int y = 2; y < inputLen && flag; y = y + 1)
                {
                    if(input.charAt(y) == '0')
                    {
                        flag = false;
                        return "0";
                    } // end if
                } // end for
                return "1";
            case 'o':
                flag = false;
                for(int y = 2; y < inputLen && !flag; y = y + 1)
                {
                    if(input.charAt(y) == '1')
                    {
                        flag = true;
                        return "1";
                    } // end if
                } // end for
                return "0";
            case 'n':
                if(input.charAt(2) == '0')
                {
                    return "1";
                }
                else
                {
                    return "0";
                } // end if
            default:
                return "";
        } // end switch
    } // end solveExp ( )

    /**
     *  Funcao recursiva para resolver a equacao completa.
     *  @param input - String.
     *  @return boolean: resultado da equacao.
     */
    public static boolean solveEquation(String input)
    {
        String expressao = treatment(input);
        return solveRecursive(expressao);
    } // end solveEquation ( )

    /**
     *  Funcao recursiva para resolver subexpressoes.
     *  @param expressao - String.
     *  @return boolean: resultado da subexpressao.
     */
    public static boolean solveRecursive(String expressao)
    {
        if(!expressao.contains("("))
        {
            return isEquals(expressao, "1");
        }
        else
        {
            int start = expressao.lastIndexOf('(');
            int end = expressao.indexOf(')', start);
            
            String subExp = subString(expressao, start - 1, end + 1);            
            String resultString = solveExp(subExp);
            
            expressao = subString(expressao, 0, start - 1) + 
                        resultString + 
                        subString(expressao, end + 1, expressao.length());
            
            return solveRecursive(expressao); // chamada recursiva
        } // end if
    } // end solveRecursive ( )

    /**
     *  Funcao para verificar se duas strings sao iguais.
     *  @param obj1 - String.
     *  @param obj2 - String.
     *  @return true se iguais, false caso contrario.
     */
    public static boolean isEquals(String obj1, String obj2)
    {
        return obj1.equals(obj2);
    } // end isEquals ( )

    /**
     *  Funcao para pegar parte de uma string.
     *  @param s - String toda.
     *  @param start - Int: Comeco.
     *  @param end - Int: fim.
     *  @return String
     */
    public static String subString(String s, int start, int end)
    {
        return s.substring(start, Math.min(end, s.length()));
    } // end subString ( )

    /**
     *  Funcao para remover os espacos em branco na string.
     *  @param input - String.
     *  @return String sem espacos sem branco.
     */
    public static String removeBlank(String input)
    {
        return input.replaceAll("\\s+", "");
    } // end removeBlank ( )

    /**
     *  Funcao para substituir uma ocorrencia de string por um caractere.
     *  @param base - Object: ocorrencia a ser trocada.
     *  @param newChar - Char: Novo caractere.
     *  @param input - String.
     *  @return String alterada.
     */
    public static String replaceAll(Object base, char newChar, String input) 
    {
        return input.replace(base.toString(), Character.toString(newChar));
    } // end replaceAll ( )

} // end class
