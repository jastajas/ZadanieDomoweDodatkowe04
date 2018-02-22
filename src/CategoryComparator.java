import java.util.Comparator;

public class CategoryComparator implements Comparator<Expenditure>{

    @Override
    public int compare(Expenditure o1, Expenditure o2) {
        return o1.getCategory().compareTo(o2.getCategory());
    }
}
