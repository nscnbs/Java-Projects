// Rekursja
public class LibraryR implements Library {

    @Override
    public int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    @Override
    public int nwd(int a, int b) {
        if (b == 0)
            return a;
        else
            return nwd(b, a % b);
    }

    @Override
    public DiofanticResult diofantic(int a, int b, int c) {
        DiofanticResult result = new DiofanticResult(0, 0);

        if (a == 0 && b == 0) {
            result.x = -1;
            result.y = -1;
            return result;
        }

        if (a == 0) {
            if (c % b == 0) {
                result.x = 0;
                result.y = c / b;
                return result;
            } else {
                result.x = -1;
                result.y = -1;
                return result;
            }
        }

        if (b == 0) {
            if (c % a == 0) {
                result.x = c / a;
                result.y = 0;
                return result;
            } else {
                result.x = -1;
                result.y = -1;
                return result;
            }
        }

        int gcd = nwd(a, b);
        if (c % gcd != 0) {
            result.x = -1;
            result.y = -1;
            return result;
        }

        DiofanticResult tempResult = diofantic(b, a % b, c);
        if (tempResult.x == -1 && tempResult.y == -1) {
            result.x = -1;
            result.y = -1;
            return result;
        }

        result.x = tempResult.y;
        result.y = tempResult.x - (a / b) * tempResult.y;

        return result;
    }
}