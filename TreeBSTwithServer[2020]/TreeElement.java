/**
 * Klasa elementu drzewa binarnego
 * @param <T> typ elementu w klasie
 */
public class TreeElement<T extends Comparable<T>> {

    /**
     * Wartośc węzła
     */
    T element;

    /**
     * Wskaźnik na korzeń lewej poddrzewa
     */
    TreeElement<T> left;

    /**
     * Wskaźnik na korzeń prawej poddrzewa
     */
    TreeElement<T> right;

    /**
     * Wskaźnik na wierzchowek
     */
    TreeElement<T> parent;

    /**
     * Węzeł
     * @param element wartość wchodząca węzła
     */
    TreeElement(T element) {
        this.element = element;
        left = null;
        right = null;
        parent = null;
    }

    /**
     * Zwraca wartość węzła dla odczytu
     * @return wartość węzła w postaci liniowej
     */
    public String toString() {
        return element.toString();
    }
}