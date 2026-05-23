package debugPprofe;





import java.util.Locale;
import java.util.Scanner;

public class debug1 {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in).useLocale(Locale.US);
            System.out.print("Introdueix el primer número: ");
            int x = in.nextInt();
            System.out.print("Introdueix el segon número: ");
            int y = in.nextInt();

            int s = x + y;
            int r = x - y;
            int m = x * y;
            int d = x / y;
            int mod = x % y;

            System.out.printf("%d + %d = %d\n", x, y, s);
            System.out.printf("%d - %d = %d\n", x, y, r);
            System.out.printf("%d * %d = %d\n", x, y, m);
            System.out.printf("%d / %d = %d\n", x, y, d);
            System.out.printf("%d %% %d = %d\n", x, y, mod);

            boolean b1 = x > y;
            boolean b2 = y % 2 == 0;

            System.out.printf("%d > %d = %s\n", x, y, b1);
            System.out.printf("%d %% 2 == 0 = %s\n", y, b2);

            boolean n = !b1;
            boolean a = b1 && b2;
            boolean o = b1 || b2;
            boolean xo = b1 ^ b2;

            System.out.printf("!%s = %s\n", b1, n);
            System.out.printf("%s && %s = %s\n", b1, b2, a);
            System.out.printf("%s || %s = %s\n", b1, b2, o);
            System.out.printf("%s ^ %s = %s\n", b1, b2, xo);
        }
    }





