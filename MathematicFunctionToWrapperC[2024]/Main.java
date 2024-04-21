public class Main {
    public static void main(String[] args) {
        Library Loop = new LibraryP();
        Library Recursion = new LibraryR();

        // Factorial
        int n = 5;
        System.out.println("Petle\n" + n + "! = " + Loop.factorial(n));
        System.out.println("Rekursja\n" + n + "! = " + Recursion.factorial(n));

        // NWD
        int num1 = 36, num2 = 48;
        System.out.println("NWD (Petle): " + num1 + " i " + num2 + " = " + Loop.nwd(num1, num2));
        System.out.println("NWD (Rekurcja): " + num1 + " i " + num2 + " = " + Recursion.nwd(num1, num2));

        // Diofantic
        int a = 120, b = 144, c = 72; // x = -3, y = 3
        // int a = 18, b = 24, c = 30; // x = -5, y = 5
        // int a = 120, b = 144, c = 71; // brak
        Library.DiofanticResult result = Loop.diofantic(a, b, c);
        if (result.x != -1 && result.y != -1) {
            System.out.println("Rozwiazanie rownania (Petle)" + a + "x + " + b + "y = " + c + " to x = " + result.x
                    + ", y = " + result.y);
        } else {
            System.out.println("Brak rozwiazania dla rownania " + a + "x + " + b + "y = " + c);
        }

        Library.DiofanticResult result1 = Recursion.diofantic(a, b, c);
        if (result1.x != -1 && result1.y != -1) {
            System.out.println("Rozwiazanie rownania (Rekursja)" + a + "x + " + b + "y = " + c + " to x = " + result1.x
                    + ", y = " + result1.y);
        } else {
            System.out.println("Brak rozwiazania dla rownania " + a + "x + " + b + "y = " + c);
        }

    }
}
