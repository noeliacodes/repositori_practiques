package debugPractica;


 import java.util.Locale;
import java.util.Scanner;

public class ejercicioDebug1 {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in).useLocale(Locale.US);

            System.out.print("Introdueix quants números vols llegir: ");
            int total = in.nextInt();

            double sum = 0;

            for (int i = 0; i < total; i++) {
                System.out.print("Introdueix un número: ");
                double num = in.nextDouble();
                sum = sum + num;
            }

            double average = sum / total;
            System.out.println("La mitjana és: " + average);
        }
    }





