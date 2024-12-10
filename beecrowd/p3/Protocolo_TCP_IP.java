import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Protocolo_TCP_IP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
            String x = sc.nextLine();
            ArrayList <String> array = new ArrayList<>();
            while(!(x.equals("0"))) {
                if(!(x.equals("1"))) {
                    array.add(x);
                }
                x = sc.nextLine();
            }
            Collections.sort(array);
            for(String s : array) {
                System.out.println(s);
            }
        }
        sc.close();
    }
}