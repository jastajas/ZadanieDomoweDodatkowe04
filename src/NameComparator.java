import java.util.Comparator;

public class NameComparator implements Comparator<Expenditure> {

    @Override
    public int compare(Expenditure o1, Expenditure o2) {
        return o1.getProductName().compareTo(o2.getProductName());
    }
}
