// Library.java
public interface Library {
    // Metoda obliczająca silnię n!
    int factorial(int n);

    // Metoda obliczająca największy wspólny dzielnik dwóch liczb naturalnych
    int nwd(int a, int b);

    // Struktura do przechowywania wyników równania diofantycznego
    class DiofanticResult {
        public int x;
        public int y;

        DiofanticResult(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Metoda rozwiązująca równanie diofantyczne ax + by = c
    DiofanticResult diofantic(int a, int b, int c);
}
