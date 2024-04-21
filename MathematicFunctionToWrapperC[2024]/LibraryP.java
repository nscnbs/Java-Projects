// Petle
public class LibraryP implements Library {

    @Override
    public int factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; ++i) {
            result *= i;
        }
        return result;
    }

    @Override
    public int nwd(int a, int b) {
        int temp;
        if (b == 0) {
            return a;
        } else {
            while (b != 0) {
                temp = b;
                b = a % b;
                a = temp;
            }
            return a;
        }
    }

    @Override
    public DiofanticResult diofantic(int a, int b, int c) {
        DiofanticResult result = new DiofanticResult(0, 0);
        int nwd_ab = nwd(a, b);

        if (c % nwd_ab == 0) {
            result.x = 0;
            result.y = 0;

            a /= nwd_ab;
            b /= nwd_ab;
            c /= nwd_ab;

            int x0 = 1, y0 = 0, x1 = 0, y1 = 1;
            while (b != 0) {
                int q = a / b;
                int temp = b;
                b = a % b;
                a = temp;

                temp = x0;
                x0 = x1;
                x1 = temp - q * x1;

                temp = y0;
                y0 = y1;
                y1 = temp - q * y1;
            }

            result.x = x0 * c;
            result.y = y0 * c;
        } else {
            result.x = -1;
            result.y = -1;
        }

        return result;
    }
}
