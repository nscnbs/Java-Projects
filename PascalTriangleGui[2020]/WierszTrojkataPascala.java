public class WierszTrojkataPascala {
    public int[][] trojkat;

    public int getElement(int n, int m){
        return trojkat[n][m];
    }

    WierszTrojkataPascala(int n) {
        trojkat = new int[n + 1][];

        for (int v = 0; v < n+1; v++) {
            trojkat[v] = new int[v+1];
            trojkat[v][0] = 1;
            for (int i = 1; i <= v; i++) {
                int i_copy = i;
                while (i > 0) {
                    trojkat[v][i] = trojkat[v][i] + trojkat[v][i - 1];
                    i--;
                }
                i = i_copy;
            }
        }
    }
}