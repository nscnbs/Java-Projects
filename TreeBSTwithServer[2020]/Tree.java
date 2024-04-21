import java.util.ArrayList;

/**
 * Klasa elementów drzewa binarnego
 * @param <T> typ elementów
 */
public class Tree<T extends Comparable<T>> {

    /**
     * Korzeń drzewa
     */
    private TreeElement<T> root;

    /**
     * Tworzenie drzewa binarnego
     */
    public Tree() {
        root = null;
    }

    public static int numElements = 0;
    public static int rows = 0;

    /**
     * Tablica dla Draw
     */
    public static ArrayList<Object> elements = new ArrayList<Object>(100);



    /**
     * Dodanie elementu do drzewa
     * x - korzeń drzewa/poddrzewa
     * potrzebny dla ustalenia pustego miejsca dla elementu(wartości)
     * y - dodanie "syna" w odpowiednim dla niego miejscu
     * z - wierzchowek
     *
     * @param element element "wchodzący"
     */
    public void insert(T element) {
        TreeElement<T> z = new TreeElement<>(element);

        TreeElement<T> y = null;
        TreeElement<T> x = root;

        while (x != null) {
            y = x;
            if (element.compareTo(x.element) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if (y == null) {
            //ustawienie korzeniu
            root = z;
        } else if (element.compareTo(y.element) < 0) {
            y.left = z;
        } else {
            y.right = z;
        }
    }

    /**
     * Sprawdza, czy węzeł istnieje w drzewie
     *
     * @param element wartość węzła
     * @return zwraca 1/0 - istnieje albo nie
     */
    public boolean isElement(T element) {
        if (searchElement(element) == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Wyszukiwanie węzła
     *
     * @param element wartość węzła
     *                w - wierzchowek
     * @return zwraca szukany węzeł z podaną wartością
     */
    public TreeElement<T> searchElement(T element) {
        return search(element, this.root);
    }

    private TreeElement<T> search(T element, TreeElement<T> w) {

        if (w != null) {
            if (element.equals(w.element)) {
                return w;
            }
            if (element.compareTo(w.element) < 0) {
                return search(element, w.left);
            } else {
                return search(element, w.right);
            }
        } else {
            return null;
        }
    }

    /**
     * Zamienia jedno poddrzewo na inne
     *
     * @param u korzeń poddrzewa, który zamieniamy
     * @param v korzeń poddrzewa, na który chcemy zamienić
     */
    private void transplate(TreeElement<T> u, TreeElement<T> v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    /**
     * Wyszukiwanie wierzchowka o najmniejszej wartości
     *
     * @param x wierzchowek
     * @return zwraca szukaną wartość
     */
    private TreeElement<T> minimum(TreeElement<T> x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    /**
     * Usuwa węzeł, który ma potrzebną wartość
     *
     * @param element wartość elementu, który usuwamy
     */
    public void deleteElement(T element) {
        delete(searchElement(element));
    }

    private void delete(TreeElement<T> z) {
        if (z.left == null) {
            transplate(z, z.right);
        } else if (z.right == null) {
            transplate(z, z.left);
        } else {
            TreeElement<T> y = minimum(z.right);

            if (y.parent != z) {
                transplate(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplate(z, y);
            y.left = z.left;
            y.left.parent = y;
        }
    }

    /**
     * "Wydruk" do konsoli za pomocą toString w wersji Preorder
     *
     * @return Zwraca drzewo w postaci liniowej
     */
    public String toString() {
        return toS(root);
    }

    public String toS(TreeElement<T> w) {
        if (w != null) {
            numElements++;
            return "(<" + w.element + ">: " + "L:" + toS(w.left) + " R:" + toS(w.right) + ")";
        }
        return "NULL";
    }

    /**
     * Wypewnia tablicę wartościami w wersii Level Order
     */
    void draw() {
        rows = level(root);
        for (int i=1; i<=rows; i++)
            fill(root, i);
    }

    /**
     * Potrzebny dla tego, żeby znaleźć odpowiedni poziom drzewa
     *
     * @param w wierzchowek
     * @return poziom na ktorym są "syny"
     */
    int level(TreeElement<T> w) {
        if (w == null)
            return 0;
        else {
            //poziom lewej i prawej
            int lLevel = level(w.left);
            int rLevel = level(w.right);

            //zwraca największy
            if (lLevel > rLevel)
                return(lLevel+1);
            else return(rLevel+1);
        }
    }

    /**
     * Wypewnia tablicę synami na odpowiednim poziome
     *
     * @param w wierzchowek
     * @param lvl poziom
     */
    void fill(TreeElement<T> w, int lvl) {
        if (w == null) {
            elements.add("~");
            return;
        }
        if (lvl == 1)
            elements.add(w);
        else if (lvl > 1) {
            if(w.left == null && lvl != 2){
                fill(w.left, lvl-1);
            }
            fill(w.left, lvl-1);
            if(w.right == null && lvl != 2){
                fill(w.right, lvl-1);
            }
            fill(w.right, lvl-1);
        }
    }
}