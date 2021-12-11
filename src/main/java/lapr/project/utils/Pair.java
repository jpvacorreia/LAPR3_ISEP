package lapr.project.utils;
/**
 * Pair class, groups two informations on one object
 * @param <T> generic value
 * @param <T1> generic value
 */
public class Pair<T, T1> {

    private final T element0;
    private final T1 element1;

    /**
     * Creates a new pair
     * @param element0 first element
     * @param element1 second element
     * @param <T> generic
     * @param <T1> generic
     * @return created pair
     */
    public static <T, T1> Pair<T, T1> createPair(T element0, T1 element1){
        return new Pair<T, T1>(element0, element1);
    }

    /**
     * Builds the new Pair object
     * @param element0 first element
     * @param element1 second element
     */
    public Pair(T element0, T1 element1){
        this.element0 = element0;
        this.element1 = element1;
    }

    /**
     * Returns the first element
     * @return first element
     */
    public T getElement0() {
        return element0;
    }

    /**
     * Returns the second element
     * @return second element
     */
    public T1 getElement1() {
        return element1;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "element0=" + element0 +
                ", element1=" + element1 +
                '}';
    }

    @Override
    public boolean equals(Object o){
        // self check
        if (this == o)
            return true;
        // null check
        if(o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        @SuppressWarnings("unchecked")
        Pair<T, T1> p = (Pair<T, T1>) o;
        // field comparison
        return getElement0().equals(p.getElement0()) && getElement1().equals(p.getElement1());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
