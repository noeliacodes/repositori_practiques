package debugMethods;



import java.util.Locale;
import java.util.Scanner;
public class debugmetodes {


        public static void main(String[] args) {
            Scanner in = new Scanner(System.in).useLocale(Locale.US);
            System.out.println("Introdueix dos nombres enters: ");
            int a = in.nextInt();
            int b = in.nextInt();

            suma(a, b);
            resta(a, b);
            multiplicacio(a, b);
            divisio(a, b);
        }

        public static void suma(int a, int b) {
            int result = a + b;
            System.out.printf("%d + %d = %d\n", a, b, result);
        }

        public static void resta(int a, int b) {
            int result = a - b;
            System.out.printf("%d - %d = %d\n", a, b, result);
        }

        public static void multiplicacio(int a, int b) {
            int result = a * b;
            System.out.printf("%d * %d = %d\n", a, b, result);
        }

        public static void divisio(int a, int b) {
            int result = a / b;
            System.out.printf("%d / %d = %d\n", a, b, result);
        }
    }

